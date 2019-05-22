package com.ctbcbank.visual.ivr.configuration;

import java.util.Properties;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.naming.Context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.connection.UserCredentialsConnectionFactoryAdapter;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.jndi.JndiTemplate;

import com.ctbcbank.visual.ivr.encrypt.DES;
import com.ctbcbank.visual.ivr.properties.EsbProperties;
import com.ctbcbank.visual.ivr.properties.KeyProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@Configuration
public class EsbConfig {
	@Autowired
	private EsbProperties esbProperties;
	@Autowired
	private KeyProperties keyProperties;
	
	@Bean(name = "esbConnectionFactory")
	public JndiObjectFactoryBean esbConnectionFactory(JndiTemplate esbJndiTemplate) {
		JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
		jndiObjectFactoryBean.setJndiName(esbProperties.getConnectionFactory());
		jndiObjectFactoryBean.setJndiTemplate(esbJndiTemplate);
		return jndiObjectFactoryBean;
	}

	@Primary
	@Bean
	public UserCredentialsConnectionFactoryAdapter esbAuthJmsQueueConnectionFactory(@Qualifier("esbConnectionFactory") JndiObjectFactoryBean esbConnectionFactory) {
		final UserCredentialsConnectionFactoryAdapter adapter = new UserCredentialsConnectionFactoryAdapter();
		String username=null, esbP1=null;
		try {
			username = DES._DecryptByDES(esbProperties.getUsername(), keyProperties.getKey());
			esbP1 = DES._DecryptByDES(esbProperties.getEsbP1(), keyProperties.getKey());
		} catch (Exception e) {
//			e.printStackTrace();
		}
		adapter.setTargetConnectionFactory((ConnectionFactory) esbConnectionFactory.getObject());
		adapter.setUsername(username);
		adapter.setPassword(esbP1);
		return adapter;
	}

	@Bean
	public JndiTemplate esbJndiTemplate() {
		final JndiTemplate jndiTemplate = new JndiTemplate();
		Properties properties = new Properties();
		String username=null,esbP1=null;
		try {
			username = DES._DecryptByDES(esbProperties.getUsername(), keyProperties.getKey());
			esbP1 = DES._DecryptByDES(esbProperties.getEsbP1(), keyProperties.getKey());
		} catch (Exception e) {
//			e.printStackTrace();
		}
		properties.setProperty(Context.PROVIDER_URL, esbProperties.getProviderUrl());
		properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, esbProperties.getProviderContextFactory());
		properties.setProperty(Context.SECURITY_PRINCIPAL, username);
		properties.setProperty(Context.SECURITY_CREDENTIALS, esbP1);
		jndiTemplate.setEnvironment(properties);
		return jndiTemplate;
	}

	@Bean(name = "queueDestination")
	public JndiObjectFactoryBean queueDestination() {
		final JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
		jndiObjectFactoryBean.setJndiTemplate(esbJndiTemplate());
		jndiObjectFactoryBean.setJndiName(esbProperties.getQueueName());
		return jndiObjectFactoryBean;
	}

	@Bean(name = "replyDestination")
	public JndiObjectFactoryBean replyDestination() {
		final JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
		jndiObjectFactoryBean.setJndiTemplate(esbJndiTemplate());
		jndiObjectFactoryBean.setJndiName(esbProperties.getReplayQueueName());
		return jndiObjectFactoryBean;
	}

	@Bean
	public JmsTemplate esbJmsSender(ConnectionFactory esbAuthJmsQueueConnectionFactory) {
		final JmsTemplate jmsTemplate = new JmsTemplate();
		jmsTemplate.setConnectionFactory(esbAuthJmsQueueConnectionFactory);
		jmsTemplate.setExplicitQosEnabled(true);
		jmsTemplate.setReceiveTimeout(esbProperties.getTimeout());
		jmsTemplate.setPriority(7);
		jmsTemplate.setTimeToLive(3000);
		jmsTemplate.setDefaultDestination((Destination) queueDestination().getObject());
		return jmsTemplate;
	}

	@Bean
	public JmsTemplate esbJmsReceiver(ConnectionFactory esbAuthJmsQueueConnectionFactory) {
		final JmsTemplate jmsTemplate = new JmsTemplate();
		jmsTemplate.setConnectionFactory(esbAuthJmsQueueConnectionFactory);
		jmsTemplate.setExplicitQosEnabled(true);
		jmsTemplate.setReceiveTimeout(esbProperties.getTimeout());
		jmsTemplate.setPriority(7);
		jmsTemplate.setTimeToLive(3000);
		jmsTemplate.setDefaultDestination((Destination) replyDestination().getObject());
		return jmsTemplate;
	}

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}

	@Bean
	public XmlMapper xmlMapper() {
		return new XmlMapper();
	}
}
