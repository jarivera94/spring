package com.helio4.bancol.avaluos.servicio.util;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;



public class Mail extends Thread {
	
	private String asunto;
	private String texto;
	private String emailDestinatario;
	
	private Message message = null;
	private Session session = null;
	
	/**
	 * Configuración del cuerpo del contenido.
	 * 
	 * */
	private Multipart content=null;
	private MimeBodyPart messageBodyAtachedFile = null;
	
	public Mail(String asunto, String texto, String emailDestinatario) {
		this.asunto = asunto;
		this.texto = texto;
		this.emailDestinatario = emailDestinatario;
		this.configurar();
	}
	@Override
	public void run(){
		this.send(this.asunto, this.texto, this.emailDestinatario);
	}
	
	/**
	 * Método que se encarga de configurar el mensaje a enviar.
	 * */
	private void configurar(){
		try {
			
			final String username = "bancolsolicitudes@tinsa.co";
			final String password = "ii^kribCYf825x4A&L";
			
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.starttls.enable","true"); 
			props.put("mail.smtp.auth", "true"); 
			props.put("mail.debug","false");
			props.put("mail.smtp.port","587"); 
			props.put("mail.smtp.socketFactory.fallback", "false");
				
			this.session = Session.getInstance(props,
					  new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(username, password);
						}
					  });
			
			this.message = new MimeMessage(this.session);
			this.content = new MimeMultipart();
			this.message.setFrom(new InternetAddress(username));
			
			
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * @param texto, cuerpo del mensaje
	 * @param emailDesitantario
	 * */
	private void send(String asunto, String texto, String emailDestinatario){
		try {
			this.message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(emailDestinatario));
			this.message.setSubject(asunto);
			
			MimeBodyPart bodyPart = new MimeBodyPart();
			bodyPart.setContent( texto, "text/html; charset=utf-8");
			
			this.content.addBodyPart(bodyPart);
			if( this.messageBodyAtachedFile != null){
				this.content.addBodyPart(this.messageBodyAtachedFile);
			}
			
			this.message.setContent(this.content);
			Transport.send(this.message);
			//System.out.println("Done, mail send " + emailDestinatario);
 
		} catch (MessagingException e) {
			//throw new RuntimeException(e);
			System.err.println(e+" The message can't be sent to:"+emailDestinatario);
		}
	}
	/**
	 * Método que se encarga de adjuntar un archivo y enviarlo en el 
	 * cuerpo del mensaje. 
	 * 
	 * @param pathArchivo path del archivo a adjuntar. 
	 * @param nombreArchivo nombre del archivo con el que llega el mensaje
	 * */
	public void adjuntarArchivo(String pathArchivo,String nombreArchivo){
		this.messageBodyAtachedFile  = new MimeBodyPart();   
        DataSource source = new FileDataSource(pathArchivo);    
        try {
			this.messageBodyAtachedFile.setDataHandler(new DataHandler(source));
			this.messageBodyAtachedFile.setFileName(nombreArchivo);    
		} catch (MessagingException e) {
			e.printStackTrace();
		}    
	}

}
