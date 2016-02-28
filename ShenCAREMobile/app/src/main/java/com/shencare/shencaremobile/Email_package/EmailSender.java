package com.shencare.shencaremobile.Email_package;

import android.os.AsyncTask;

import java.security.Security;
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
 * Created by RWA on 14/02/16.
 */

public class EmailSender extends javax.mail.Authenticator {
    Session session ;
    String textContext;
    String thankYouText;
    String recipient;

    static {
        Security.addProvider(new JSSEProvider());
    }

    public EmailSender(String textContext, String recipient){
        Properties props = new Properties();
        props.put("mail.smtp.host", "mail.shencare.net");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        this.recipient = recipient;
        this.textContext = textContext;
        session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("gdshencareadmin", "Durian168");
            }
        });

        RetrieveFeedTask task = new RetrieveFeedTask();
        task.execute();
    }
    class RetrieveFeedTask extends AsyncTask<String, Void, String> {
        public RetrieveFeedTask(){

        }

        @Override

        protected String doInBackground(String... params) {

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("noreply@shencare.net"));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("rwalamsyah.2013@smu.edu.sg"));
                message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse("teamunition@gmail.com"));
                message.setSubject("Volunteer Sign Up");
                message.setContent(textContext, "text/html; charset=utf-8");
                Transport.send(message);

                //thank you for registering email
                thankYouText = "Thank you for Registering with Shencare, We will get in touch with you shortly.";
                Message thankYouMessage = new MimeMessage(session);
                thankYouMessage.setFrom(new InternetAddress("noreply@shencare.net"));
                thankYouMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
                thankYouMessage.setSubject("Volunteer Sign Up");
                thankYouMessage.setContent(thankYouText, "text/html; charset=utf-8");
                Transport.send(thankYouMessage);
            } catch (MessagingException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}