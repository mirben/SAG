/**
 * 
 */
package com.sag.business.service;

import java.util.ArrayList;
import java.util.Properties;
import java.util.List;

import javax.mail.*;
import javax.mail.internet.*;

import com.sag.business.model.Etudiant;
import com.sag.business.model.Offre;

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
	
	//fonction de base pour creer et envoyer un @mail, ne gére pas les images embarqués.
	public void sendMessage(String subject, String text, Address[] destinataire) { 
	    
		//Création de la session 
	    Properties properties = new Properties(); 
	    properties.setProperty("mail.transport.protocol", "smtp"); 
	    properties.setProperty("mail.smtp.host", SMTP_HOST1); 
	   	properties.setProperty("mail.smtp.user", LOGIN_SMTP1); 
	    properties.setProperty("mail.from", IMAP_ACCOUNT1); 
	    properties.setProperty("mail.smtp.starttls.enable", "true");
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
	
	//@mail inscription, il manque des détails
	public void sendInscription(Etudiant etudiant) throws AddressException{
		//création du texte
		StringBuilder builder = new StringBuilder("<html xmlns='http://www.w3.org/1999/xhtml'><head><title>"); 
				 
		builder.append("<title>Réception de votre inscription</title>"); 
		
		builder.append("<br/>"); 
		builder.append("</body></html>"); 
		 
		String text = builder.toString();
		
		//envoie du message
		List<Address> destinataires= new ArrayList<Address>();  //<Address>; new InternetAddress(etudiant.getAdresse());
		destinataires.add(new InternetAddress(etudiant.getAdresse()));
		sendMessage("Bienvenue sur le Site d'Achat Groupé (SAG)", text, (Address[]) (destinataires.toArray()));
	}
	
	
	//@mail le compte est valider, il manque des détails
	public void sendCompteValider(Etudiant etudiant) throws AddressException{
		//création du texte
		StringBuilder builder = new StringBuilder("<html xmlns='http://www.w3.org/1999/xhtml'><head><title>"); 
				 
		builder.append("<title>Venez découvrir nos offres</title>"); 
		builder.append("<p>Vous pouvez désormais visiter le site, parcourir, rechercher , participer et proposer des offres.</p>");
		builder.append("<br/>");
		builder.append("<p></p>");
		builder.append("</body></html>"); 
		 
		String text = builder.toString();
		
		//envoie du message
		List<Address> destinataires= new ArrayList<Address>();  //<Address>; new InternetAddress(etudiant.getAdresse());
		destinataires.add(new InternetAddress(etudiant.getAdresse()));
		sendMessage("SAG: Votre compte est validé", text, (Address[]) (destinataires.toArray()));
	}
	
	//@mail le compte est Rejeter, il manque des détails
	public void sendCompteRejeter(Etudiant etudiant, String motif) throws AddressException{
		//création du texte
		StringBuilder builder = new StringBuilder("<html xmlns='http://www.w3.org/1999/xhtml'><head><title>"); 
				 
		//builder.append("<title>Venez découvrir nos offres</title>"); 
		builder.append("<p>Nous avons le regret de vous informer que votre inscription est rejeté</p>");
		builder.append("<br/>");
		builder.append("<H2>Motif du rejet</H2><br/>");
		builder.append("<p>").append(motif).append("</p>");
		builder.append("</body></html>"); 
		 
		String text = builder.toString();
		
		//envoie du message
		List<Address> destinataires= new ArrayList<Address>();  //<Address>; new InternetAddress(etudiant.getAdresse());
		destinataires.add(new InternetAddress(etudiant.getAdresse()));
		sendMessage("SAG: Admission refuser", text, (Address[]) (destinataires.toArray()));
	}
	
	//@mail le compte est désactiver, il manque des détails
	public void sendCompteDésactiver(Etudiant etudiant, String motif) throws AddressException{
		//création du texte
		StringBuilder builder = new StringBuilder("<html xmlns='http://www.w3.org/1999/xhtml'><head><title>"); 
				 
		//builder.append("<title>Venez découvrir nos offres</title>"); 
		builder.append("<p>Nous avons le regret de vous informer que vous êtes bannis SAG</p>");
		builder.append("<br/>");
		builder.append("<H2>Motif du banissement</H2><br/>");
		builder.append("<p>").append(motif).append("</p>");
		builder.append("</body></html>"); 
		 
		String text = builder.toString();
		
		//envoie du message
		List<Address> destinataires= new ArrayList<Address>();  //<Address>; new InternetAddress(etudiant.getAdresse());
		destinataires.add(new InternetAddress(etudiant.getAdresse()));
		sendMessage("SAG: vous êtes banni", text, (Address[]) (destinataires.toArray()));
	}
	
	//@mail votre offre est en attente de validation, il manque des détails
	public void sendOffreAttente(Etudiant etudiant, Offre offre) throws AddressException{
		//création du texte
		StringBuilder builder = new StringBuilder("<html xmlns='http://www.w3.org/1999/xhtml'><head><title>"); 
				 
		builder.append("<title>Venez découvrir nos offres</title>"); 
		builder.append("<p>Nous avons le regret de vous informer que votre inscription est rejeté</p>");
		builder.append("<br/>");
		//affichage offre.
		builder.append("<H2></H2><br/>");
		builder.append("<p>").append("").append("</p>");
		builder.append("</body></html>"); 
		 
		String text = builder.toString();
		
		//envoie du message
		List<Address> destinataires= new ArrayList<Address>();  //<Address>; new InternetAddress(etudiant.getAdresse());
		destinataires.add(new InternetAddress(etudiant.getAdresse()));
		sendMessage("SAG: Votre offre est en attente de validation", text, (Address[]) (destinataires.toArray()));
	}
}


