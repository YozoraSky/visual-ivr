package com.ctbcbank.util;

import java.util.UUID;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.MessagePostProcessor;


public class EsbMessagePostProcessor implements MessagePostProcessor {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private Message sendMessage;
	private Destination replyTo;

	public EsbMessagePostProcessor(Destination replyTo) {
		this.replyTo = replyTo;
	}

	public Message postProcessMessage(Message message) throws JMSException {
		this.sendMessage = message;
		message.setBooleanProperty("JMS_TIBCO_PRESERVE_UNDELIVERED", true);
		message.setJMSReplyTo(this.replyTo);
		message.setJMSCorrelationID(UUID.randomUUID().toString());
		if (logger.isDebugEnabled()) {
			logger.debug("SEND MESSAGE : \n" + message);
		}
		return message;
	}

	public String getMessageSelector() {
		String correlationID = null;
		if (this.sendMessage != null) {
			try {
				correlationID = this.sendMessage.getJMSCorrelationID();
			}
			catch (JMSException e) {
				correlationID = null;
			}
		}
		if (correlationID != null) {
			return "JMSCorrelationID = '" + correlationID + "'";
		}
		return null;
	}
}
