/*
 * =================================================================
 * Copyright (c) 2001-2003 TIBCO Software Inc.
 * All rights reserved.
 * For more information, please contact:
 * TIBCO Software Inc., Palo Alto, California, USA
 *
 * $RCSfile: tibjmsJNDIQueueSyncSenderTempReply.java,v $
 * $Revision: 1.22 $
 * $Date: 2003/04/14 15:57:39 $
 * =================================================================
 */
/*
 *
 * Usage:  java tibjmsJNDIQueueSyncSenderTempReply  [options]
 *                                  <message-text1>
 *                                  ...
 *                                  <message-textN>
 *
 *
 *    where options are:
 *
 *      -server     JDNI URL.
 *                  If not specified this sample assumes a
 *                  JDNIUrl of "tibjmsnaming://localhost:7222"
 *
 *      -user       User name. Default is null.
 *      -password   User password. Default is null.
 *      -queue      Queue name. Default is "queue.sample"
 *
 *
 */
package com.ctbcbank.esb;

import java.util.Hashtable;
import java.util.Vector;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class EsbInvoke implements ExceptionListener {
	private Logger logger = LoggerFactory.getLogger(EsbInvoke.class);
	static final String providerContextFactory = "com.tibco.tibjms.naming.TibjmsInitialContextFactory";
	static final String defaultProviderURL = "tibjmsnaming://172.24.17.42:17222";
	static final String defaultConnectionFactory = "ServiceAccessQueueConnectionFactory";
	String providerUrl = "tibjmsnaming://172.24.17.42:17222";
	String userName = "csdpsit01";
	String password = "csdpsit0159";
	String[] factory = null;
	String queueName = "CTCB.ESB.SIT.Public.Service.Request.C01.CH1";
	String replayQueueName = "CTCB.ESB.SIT.Public.Service.Reply.CSDP";
	Vector data = new Vector();

	public String excute(final String request) throws JAXBException, JMSException {
		TextMessage textMessage = null;
		String result = StringUtils.EMPTY;
		if (providerUrl == null)
			providerUrl = defaultProviderURL;
		/* print parameters */
		logger.info("\n------------------------------------------------------------------------");
		logger.info("tibjmsJNDIQueueSyncSenderTempReply SAMPLE");
		logger.info("------------------------------------------------------------------------");
		logger.info("Server....................... " + (providerUrl != null ? providerUrl : "localhost"));
		logger.info("User......................... " + (userName != null ? userName : "(null)"));
		logger.info("Queue........................ " + queueName);
		logger.info("------------------------------------------------------------------------\n");
		if (queueName == null) {
			logger.error("Error: must specify queue name");
			usage();
		}
		logger.error("Publishing into queue: '" + queueName + "'\n");
		try {
			// ConnectionFactory factory = new
			// com.tibco.tibjms.TibjmsConnectionFactory(providerUrl);
			// Connection connection = factory.createConnection(userName,password);
			// Session session =
			// connection.createSession(false,javax.jms.Session.AUTO_ACKNOWLEDGE);
			/*
			 * Init JNDI Context.
			 */
			Hashtable env = new Hashtable();
			env.put(Context.INITIAL_CONTEXT_FACTORY, providerContextFactory);
			env.put(Context.PROVIDER_URL, providerUrl);
			if (userName != null) {
				env.put(Context.SECURITY_PRINCIPAL, userName);
				if (password != null)
					env.put(Context.SECURITY_CREDENTIALS, password);
			}
			InitialContext jndiContext = new InitialContext(env);
			ConnectionFactory factory = (ConnectionFactory) jndiContext.lookup(defaultConnectionFactory);
			logger.info("OK - successfully did lookup " + defaultConnectionFactory);
			// �B�J�G:�إ߻P JMS Server ���s�u
			Connection connection = factory.createConnection(userName, password);
			Session session = connection.createSession(false, javax.jms.Session.AUTO_ACKNOWLEDGE);
			javax.jms.Queue queue = session.createQueue(queueName);
			MessageProducer sender = session.createProducer(queue);
			// Create Temporary Queue for reply
			javax.jms.Queue replyQueue = session.createQueue(replayQueueName);
			/* publish messages */
			String msgID = null;
			Vector data = new Vector();
			data.addElement(request);
			for (int i = 0; i < data.size(); i++) {
				javax.jms.TextMessage message = session.createTextMessage();
				String text = (String) data.elementAt(i);
				message.setText(text);
				message.setJMSReplyTo(replyQueue);
				sender.send(message, DeliveryMode.NON_PERSISTENT, 4, 10000);
				msgID = message.getJMSMessageID();
				logger.info("\nJMSMessageID: " + msgID);
			}
			// It is not necessary to use selector to receive the corresponding reply
			// message for Temporary Reply Queue
			String selectorSQL92 = null;
			selectorSQL92 = "JMSCorrelationID = " + "'" + msgID + "'";
			logger.info("Message selector: " + selectorSQL92);
			// MessageConsumer receiver = session.createConsumer(tempQueue, selectorSQL92);
			MessageConsumer receiver = session.createConsumer(replyQueue, selectorSQL92);
			connection.start();
			javax.jms.Message msg = receiver.receive(10000);
			textMessage = (TextMessage) msg;
			connection.close();
		}
		catch (NamingException e) {
			e.printStackTrace();
		}
		catch (JMSException e) {
			e.printStackTrace();
		}
		if (textMessage != null) {
			result = textMessage.getText();
		}
		return result;
	}

	/*---------------------------------------------------------------------
	 * onException
	 *---------------------------------------------------------------------*/
	public void onException(JMSException e) {
		/* print the connection exception status */
		logger.error("CONNECTION EXCEPTION: " + e.getMessage());
	}

	public static void main(String args[]) throws JAXBException, DatatypeConfigurationException {
		// TibjmsJNDIQueueSyncSenderTempReply t = new TibjmsJNDIQueueSyncSenderTempReply(args);
	}

	void usage() {
		System.err.println("\nUsage: java tibjmsJNDIQueueSyncSenderTempReply [options]");
		System.err.println("                                <message-text1 ... message-textN>");
		System.err.println("");
		System.err.println("   where options are:");
		System.err.println("");
		System.err.println(" -server    <JDNI URL> - JDNI URL, default is local server");
		System.err.println(" -user      <user name>  - user name, default is null");
		System.err.println(" -password  <password>   - password, default is null");
		System.err.println(" -queue     <queue-name> - queue name, default is \"queue.sample\"");
		System.exit(0);
	}

	void parseArgs(String[] args) {
		int i = 0;
		while (i < args.length) {
			if (args[i].compareTo("-server") == 0) {
				if ((i + 1) >= args.length)
					usage();
				providerUrl = args[i + 1];
				i += 2;
			}
			else if (args[i].compareTo("-queue") == 0) {
				if ((i + 1) >= args.length)
					usage();
				queueName = args[i + 1];
				i += 2;
			}
			else if (args[i].compareTo("-user") == 0) {
				if ((i + 1) >= args.length)
					usage();
				userName = args[i + 1];
				i += 2;
			}
			else if (args[i].compareTo("-password") == 0) {
				if ((i + 1) >= args.length)
					usage();
				password = args[i + 1];
				i += 2;
			}
			else if (args[i].compareTo("-help") == 0) {
				usage();
			}
			else if (args[i].startsWith("-ssl")) {
				i++;
			}
			else {
				data.addElement(args[i]);
				i++;
			}
		}
	}
}
