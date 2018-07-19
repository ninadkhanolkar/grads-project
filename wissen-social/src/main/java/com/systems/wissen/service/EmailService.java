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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systems.wissen.model.Employee;
import com.systems.wissen.repo.EmployeeRepository;

@Service
public class EmailService {
	private final static String HOST = "smtp.gmail.com";

	@Autowired
	private EmployeeRepository employeeRepository;
	
	public void sendEmail(String employeeId) {
		Properties properties = new Properties();
		properties.setProperty("mail.smtp.host", HOST);
		properties.setProperty("mail.smtp.port", "587");
		properties.setProperty("mail.smtp.starttls.enable","true");
		properties.setProperty("mail.smtp.auth", "true");
		Employee employeeById = employeeRepository.getEmployeeById(employeeId);
		Authenticator authenticator = new Authenticator()
	    {
	    protected PasswordAuthentication getPasswordAuthentication() 
	        {
	        return new PasswordAuthentication("noreply.wiseconnect@gmail.com", "qwerty@123");
	    }
	};
	Session defaultInstance = Session.getInstance(properties,authenticator);
		try {
			MimeMessage message = new MimeMessage(defaultInstance);
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(employeeById.getEmailId()));
			message.setSubject("WiseConnect : Application Status");
			message.setText("Thank you for your interest in WiseConnect. We received your application, We have reviewed your application and, unfortunately,\r\n" + 
					"we have decided to reject your application. We thank you again for your interest in WiseConnect and wish you success in your job search.\r\n" + 
					"\r\n" + 
					"Thanks,\r\n" + 
					"WiseConnect Admin");
			Transport.send(message);
			System.out.println("message sent successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
}