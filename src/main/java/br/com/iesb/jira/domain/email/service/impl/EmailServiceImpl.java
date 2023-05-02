package br.com.iesb.jira.domain.email.service.impl;

import br.com.iesb.jira.domain.email.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;

@Service
public class EmailServiceImpl implements EmailService {

    @Value("${email.password}")
    private String EMAIL_PASSWORD;

    @Value("${email.url}")
    private String EMAIL_URL;

    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", 587);
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", true);
            props.put("mail.smtp.ssl.protocols", "TLSv1.2");
            props.put("mail.smtp.starttls.enable", true);


            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("viniciussantanaleao@gmail.com", EMAIL_PASSWORD);
                }
            });

            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("viniciussantanaleao@gmail.com", false));

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            msg.setSubject(subject);
            msg.setContent("Tutorials point email", "text/html");
            msg.setSentDate(new Date());

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent("Olá,\\n\\nVocê solicitou a redefinição de sua senha. " +
                    "Clique neste link para redefinir sua senha:" + EMAIL_URL +
                    "\\n\\nEste link expirará em 24 horas.\\n\\nObrigado!", "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            msg.setContent(multipart);
           Transport.send(msg);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
