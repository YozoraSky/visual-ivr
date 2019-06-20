package com.ctbcbank.visual.ivr.service.impl;

import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.DOMReader;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.ctbcbank.util.EsbMessagePostProcessor;
import com.ctbcbank.util.JsonToXMLConverter;
import com.ctbcbank.util.XmlUtils;
import com.ctbcbank.visual.ivr.encrypt.Log;
import com.ctbcbank.visual.ivr.esb.enumeraion.ProcessResultEnum;
import com.ctbcbank.visual.ivr.esb.enumeraion.ProcessStatus;
import com.ctbcbank.visual.ivr.esb.model.EsbCommandOut;
import com.ctbcbank.visual.ivr.esb.model.EsbIn;
import com.ctbcbank.visual.ivr.esb.model.ProcessResult;
import com.ctbcbank.visual.ivr.service.EsbCommandService;
import com.ctbcbank.visual.ivr.service.EsbCommonService;

import net.sf.json.JSON;
import net.sf.json.xml.XMLSerializer;
import tw.com.chinatrust.ns.xsd.ctcb.esb.message.emf.common.AnyContainer;
import tw.com.chinatrust.ns.xsd.ctcb.esb.message.emf.common.Error;
import tw.com.chinatrust.ns.xsd.ctcb.esb.message.emf.serviceenvelope.ServiceEnvelope;
import tw.com.chinatrust.ns.xsd.ctcb.esb.message.emf.serviceerror.ServiceError;
import tw.com.chinatrust.ns.xsd.ctcb.esb.message.emf.serviceheader.ServiceHeader;

@Primary
@Service
public class EsbCommandServiceImpl extends EsbCommonService implements EsbCommandService {
	public final static String RQ_SUFFIX = "Rq";
	public final static String XML_PREFIX = "ns99:";
	public final static String NAMESPACE_URL = "http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/BSMF/{}/01";
	public final static Map<String, String> NAMESPACE_MAP = new HashMap<>();
	
	@Autowired
	private JmsTemplate esbJmsSender;
	@Autowired
	private JmsTemplate esbJmsReceiver;
	@Autowired
	private JsonToXMLConverter jsonToXMLConverter;
	@Autowired
	private Log log;

	public EsbCommandServiceImpl() {
		NAMESPACE_MAP.put("ivBillInq", "http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/BSMF/ivBillInqRq/01");
	}

	@SuppressWarnings("unchecked")
	@Override
	public EsbCommandOut excute(final EsbIn esbIn, String UUID) throws Exception {
		final EsbCommandOut esbCommandOut = new EsbCommandOut();
		esbCommandOut.setServiceName(esbIn.getServiceName());
		final ProcessResult processResult = esbCommandOut.getProcessResult();
		processResult.setProcessResultEnum(ProcessResultEnum.QUERY_SUCCESS);
		final String serviceName = esbIn.getServiceName();
		final String serviceNameRq = serviceName + RQ_SUFFIX;
		String namespaceUrl = MessageFormatter.format(NAMESPACE_URL, serviceNameRq).getMessage();
		Map<String, Object> reqhdrMap = (Map<String, Object>) esbIn.getData().get("REQHDR");
//		========Test=======(if esbIn.getData().first != "REQHDR")
		Format.check(esbIn);
//		========Test=======
		//呼叫這行的時候，就已經set過serviceEnvelope的serviceHeader了
		//this.getServiceHeader和代表的是EsbCommandService的function
		//serviceEnvelope.getServiceHeader則代表serviceEnvelope的function
		final ServiceEnvelope serviceEnvelope = this.getServiceEnvelope();
		if(((String)reqhdrMap.get("TrnCode")).indexOf("3260") != -1) {
			String transactionID = this.getGSTransactionID();
			serviceEnvelope.getServiceHeader().setTransactionID(transactionID);
			Map<String, Object> reqBdyMap = (Map<String, Object>) esbIn.getData().get("REQBDY");
			if(reqBdyMap.containsKey("TranNo"))
				reqBdyMap.put("TranNo", transactionID);
		}
		else {
			serviceEnvelope.getServiceHeader().setTransactionID(this.getTransactionID());
		}
		if(esbIn.getServiceName().equals("amOTPAuthntcnAud") || esbIn.getServiceName().equals("csCmpgnApplyAdd") || esbIn.getServiceName().equals("csCmpgnListInq")) {
			serviceEnvelope.getServiceHeader().setSourceID("TWIVR");
			if(esbIn.getServiceName().equals("amOTPAuthntcnAud") && ((Map<String, String>)reqhdrMap.get("SecurityContext")).get("SecurityMethod").equals("verifyOTP")) {
				String transactionID = (String) reqhdrMap.get("TrnNum");
				serviceEnvelope.getServiceHeader().setTransactionID(transactionID);
			}
		}
		reqhdrMap.put("TrnNum", serviceEnvelope.getServiceHeader().getTransactionID());
		String rqXml = jsonToXMLConverter.convertToXml(serviceNameRq, esbIn.getData());
		rqXml = XmlUtils.addPrefixAndNameSpace(rqXml, namespaceUrl, XML_PREFIX);
		serviceEnvelope.getServiceHeader().setServiceName(esbIn.getServiceName());
		serviceEnvelope.setServiceBody(new AnyContainer());
		JAXBContext jaxbContext = JAXBContext.newInstance(ServiceEnvelope.class);
		Marshaller marshaller = jaxbContext.createMarshaller();
		StringWriter StringWriter = new StringWriter();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		marshaller.marshal(serviceEnvelope, StringWriter);
		Document serviceEnvelopeDocument = DocumentHelper.parseText(StringWriter.toString());
		Document rqDocument = DocumentHelper.parseText(rqXml);
		Element element = (Element) serviceEnvelopeDocument.getRootElement();
		element.element("ServiceBody").add(rqDocument.getRootElement());
		String xml = serviceEnvelopeDocument.asXML();
		log.writeEsbInputInfo(esbIn, xml, esbIn.getServiceName(), Log.IVRGATEWAY);
		long ESBinTime = System.currentTimeMillis();
		Object jmsObjResult = this.sendAndReceive(xml);
		long ESBoutTime = System.currentTimeMillis();
		log.writeTimeLog(esbIn.getConnID(), UUID, "IVRESB", ESBinTime, ESBoutTime);
		String jmsResult = StringUtils.EMPTY;
		if(jmsObjResult != null) {
			jmsResult = jmsObjResult.toString();
		}
		log.writeEsbOutputInfo(esbIn, jmsResult, esbIn.getServiceName(), Log.IVRGATEWAY);
		if (StringUtils.isNotBlank(jmsResult)) {
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			ServiceEnvelope result = (ServiceEnvelope) unmarshaller.unmarshal(new StringReader(jmsResult));
			if (result != null) {
				final ServiceHeader serviceHeader = result.getServiceHeader();
				if (serviceHeader != null) {
					BigInteger statusCode = serviceHeader.getStatusCode();
					processResult.setTrackingID(serviceHeader.getTrackingID());
					if (statusCode != null) {
						if (statusCode.intValue() == 0) {
							if (result.getServiceBody() != null && result.getServiceBody().getAny() != null) {
								DOMReader reader = new DOMReader();
								org.w3c.dom.Element anyElement = (org.w3c.dom.Element) result.getServiceBody().getAny();
								if (anyElement != null) {
									Document anyDocument = reader.read(anyElement.getOwnerDocument());
									XMLSerializer xmlSerializer = new XMLSerializer();
									xmlSerializer.setSkipNamespaces(true);
									xmlSerializer.setRemoveNamespacePrefixFromElements(true);
									String anyXml = anyDocument.asXML();
									JSON json = xmlSerializer.read(anyXml);
									esbCommandOut.setData(json.toString());
								}
							}
							else {
									processResult.setProcessResultEnum(ProcessResultEnum.DATA_NOT_FOUND);
							}
						}
						else {
							ServiceError serviceError = result.getServiceError();
							if (serviceError != null && serviceError.getError() != null) {
								Error error = serviceError.getError();
								processResult.setReturnCode(error.getErrorCode());
								processResult.setStatus(ProcessStatus.FAIL.getStatus());
								processResult.setReturnMessage(error.getErrorMessage());
							}
						}
					}
				}
			}
		}
		else {
				processResult.setProcessResultEnum(ProcessResultEnum.NO_RECEIVE_DATA);
		}
		return esbCommandOut;
		
	}

	protected Object sendAndReceive(Object value) {
		Object response = null;
		EsbMessagePostProcessor message = new EsbMessagePostProcessor(this.esbJmsReceiver.getDefaultDestination());
		this.esbJmsSender.convertAndSend(value, message);
		response = this.esbJmsReceiver.receiveSelectedAndConvert(message.getMessageSelector());
		return response;
	}

}
