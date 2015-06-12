package Core;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Tran Huy
 */
public class Email {
    public static void send(String from, String to, String pwd, String subject, String body) throws Exception {
        
        String host = "smtp.gmail.com";
        Properties properties = System.getProperties();
        
        // Setup mail server
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.user", from);
        properties.put("mail.smtp.password", pwd);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        
        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);
        
        try{
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO,
                                     new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("This is the Subject Line!");

            // Now set the actual message
            message.setText("This is actual message");

            // Send message
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pwd);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}