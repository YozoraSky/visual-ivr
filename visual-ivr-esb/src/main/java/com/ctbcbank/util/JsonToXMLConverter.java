package com.ctbcbank.util;

import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@Component
public class JsonToXMLConverter {
	private static final Pattern XML_TAG = Pattern.compile("(?m)(?s)(?i)(?<first><(/)?)(?<nonXml>.+?)(?<last>(/)?>)");
	private static final Pattern REMOVE_ILLEGAL_CHARS = Pattern.compile("(i?)([^\\s=\"'a-zA-Z0-9._-])|(xmlns=\"[^\"]*\")");
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private XmlMapper xmlMapper;

	public String convertToXml(final String rootName, Object obj) throws IOException {
		String s = xmlMapper.writer().withDefaultPrettyPrinter().withRootName(rootName).writeValueAsString(obj);
		return removeIllegalXmlChars(s);
	}

	private String removeIllegalXmlChars(String s) {
		final Matcher matcher = XML_TAG.matcher(s);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			String elementName = REMOVE_ILLEGAL_CHARS.matcher(matcher.group("nonXml")).replaceAll("").trim();
			matcher.appendReplacement(sb, "${first}" + elementName + "${last}");
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

	public String convertJson(Map<String, Object> map) throws IOException {
		return objectMapper.writeValueAsString(map);
	}
}
