package com.ctbcbank.util;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLFilterImpl;
import org.xml.sax.helpers.XMLReaderFactory;

public class XmlUtils {
	public static final String YES = "yes";

	public static String addPrefixAndNameSpace(final String source, final String namespace, final String pref) throws Exception {
		XMLReader xmlReader = new XMLFilterImpl(XMLReaderFactory.createXMLReader()) {
			@Override
			public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
				super.startElement(namespace, localName, pref + qName, atts);
			}

			@Override
			public void endElement(String uri, String localName, String qName) throws SAXException {
				super.endElement(namespace, localName, pref + qName);
			}
		};
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer t = tf.newTransformer();
		t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, YES);
		StringWriter s = new StringWriter();
		t.transform(new SAXSource(xmlReader, new InputSource(new StringReader(source))), new StreamResult(s));
		return s.toString();
	}
}
