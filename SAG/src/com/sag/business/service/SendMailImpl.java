/**
 * 
 */
package com.sag.business.service;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

/**
 * @author c20602234
 *
 */
public class SendMailImpl {

	private String SMTP_HOST1 = null;
	private String LOGIN_SMTP1 = null;
	private String IMAP_ACCOUNT1 = null;
	private String PASSWORD_SMTP1 = null;
	
	/**
	 * 
	 */
	public SendMailImpl() {
		// TODO Auto-generated constructor stub
	}
	
	//fonction de base pour envoyer un @mail
	public void sendMessage(String subject, String text, Address[] destinataire, String copyDest) { 
	    
		//Création de la session 
	    Properties properties = new Properties(); 
	    properties.setProperty("mail.transport.protocol", "smtp"); 
	    properties.setProperty("mail.smtp.host", SMTP_HOST1); 
	   	properties.setProperty("mail.smtp.user", LOGIN_SMTP1); 
	    properties.setProperty("mail.from", IMAP_ACCOUNT1); 
	    Session session = Session.getInstance(properties); 
	 
	    //Création du contenu du message
	    MimeBodyPart content = new MimeBodyPart(); 
	    try { 
	        content.setText(text, "UTF-8", "html");
	    } catch (MessagingException e) { 
	        e.printStackTrace(); 
	    } 
	    
	    MimeMultipart mimeMultipart = new MimeMultipart(); 
	    try { 
	        mimeMultipart.addBodyPart(content); 
	    } catch (MessagingException e) { 
	        e.printStackTrace(); 
	    }
	    
	    //Création du message
	    MimeMessage message = new MimeMessage(session); 
	    try {
	        message.setContent(mimeMultipart); 
	        message.setSubject(subject);
	        message.addRecipients(Message.RecipientType.TO, destinataire); 
	    } catch (MessagingException e) { 
	        e.printStackTrace(); 
	    } 

	    //Envoi du message
	    Transport transport =null;
	    try { 
	        transport = session.getTransport("smtp"); 
	        transport.connect(LOGIN_SMTP1, PASSWORD_SMTP1); 
	        transport.sendMessage(message, destinataire); 
	   
	    } catch (MessagingException e) { 
	        e.printStackTrace(); 
	    } finally { 
	        try { 
	            if (transport != null) { 
	                transport.close(); 
	            } 
	        } catch (MessagingException e) { 
	            e.printStackTrace(); 
	        } 
	    } 
	    
	}

}