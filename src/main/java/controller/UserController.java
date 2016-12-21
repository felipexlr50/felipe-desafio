package controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Properties;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import dao.UserDAO;
import model.User;


@ManagedBean
@ViewScoped
public class UserController  {


	private User user = new User();

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void addNewUser(){
		UserDAO dao;
		System.out.println(user.getEmail());
		
		
		try{
			dao = new UserDAO();
			dao.addUser(user);
			sendMail();
		} catch (ClassNotFoundException | InstantiationException | SQLException
				| IllegalAccessException e) {
			
			e.printStackTrace();
		}
	}
	
	private void sendMail(){
		String senderMail = "felipexlr50@hotmail.com";
		String recipientMail = user.getEmail();
		String link = "http://localhost:8080/felipe-desafio/survey.xhtml?id="+toMD5(user.getEmail());
		Properties props = new Properties();
		
		props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", "smtp-mail.outlook.com");
        props.put("mail.smtp.socketFactory.port", "587");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
		
		 Session session = Session.getDefaultInstance(props,
                 new javax.mail.Authenticator() {
                      protected PasswordAuthentication getPasswordAuthentication() 
                      {
                            return new PasswordAuthentication("felipe.desafio@outlook.com", "Desafio 123");
                      }
                 });
		 session.setDebug(true);
		
		try{
			 MimeMessage message = new MimeMessage(session);
			 message.setFrom(new InternetAddress(senderMail));
			 message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientMail));
			 message.setSubject("Bem vindo ao MedeConsumo!");
			 message.setText("Obrigado pelo interesse nesse projeto. Ele está previsto para ser lançado no segundo semestre de 2017. \n"
			 		+ "Por favor responda nosso questionário sobre o aplicativo através desse link: "+link);
			 Transport.send(message);
	         System.out.println("OK");
		}catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
		
	}
	
	private String toMD5(String text){
		MessageDigest algorithm;
		try {
			algorithm = MessageDigest.getInstance("MD5");
			byte messageDigest[] = algorithm.digest("senha".getBytes("UTF-8"));
			StringBuilder hexString = new StringBuilder();
			for (byte b : messageDigest) {
			  hexString.append(String.format("%02X", 0xFF & b));
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
