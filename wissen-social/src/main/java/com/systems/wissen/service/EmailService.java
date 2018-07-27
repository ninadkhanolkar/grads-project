package com.systems.wissen.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.systems.wissen.model.Employee;

@Service
public class EmailService {
	private static final String HOST = "smtp.gmail.com";
	private static final Logger logger = Logger.getLogger(EmailService.class);
	public void sendEmail(Employee find) {
		Properties properties = setProperties();
		Employee employeeById = find;
		Authenticator authenticator = new Authenticator()
	    {
			@Override
	    protected PasswordAuthentication getPasswordAuthentication() 
	        {
	        return new PasswordAuthentication("noreply.wiseconnect@gmail.com", "qwerty@123");
	    }
	};
	Session defaultInstance = Session.getInstance(properties,authenticator);
		try {
			MimeMessage message = createMessage(employeeById, defaultInstance);
			Transport.send(message);
			logger.info("message sent successfully....");
		} catch (MessagingException mex) {
			logger.error("Exception is : ", mex);
		}
	}

	private Properties setProperties() {
		Properties properties = new Properties();
		properties.setProperty("mail.smtp.host", HOST);
		properties.setProperty("mail.smtp.port", "587");
		properties.setProperty("mail.smtp.starttls.enable","true");
		properties.setProperty("mail.smtp.auth", "true");
		return properties;
	}

	private MimeMessage createMessage(Employee employeeById, Session defaultInstance)
			throws MessagingException {
		MimeMessage message = new MimeMessage(defaultInstance);
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(employeeById.getEmailId()));
		message.setSubject("WiseConnect : Application Status");
		message.setText("Thank you for your interest in WiseConnect. After reviewing your application ,we have decided to reject your application\r\n" + 
				" due to discrepancy in information provided. We thank you again for your interest in WiseConnect .\r\n" + 
				"\r\n" + 
				"Thanks,\r\n" + 
				"WiseConnect Admin");
		return message;
	}
}