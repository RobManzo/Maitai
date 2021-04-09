package com.manzo.misc;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

/**
 * Classe per l'invio delle mail.
 * Implementa l'interfaccia Runnable per permettere l'invio delle mail tramite un thread
 * durante lo svolgimento di altre operazioni.
 * @author Manzo Roberto
 */
public class MailService implements Runnable {

    private final String host;
    private final String password;
    private final String indirizzoDestinazione;
    private final String oggetto;
    private final String messaggio;
    private final String attachment_title;
    private static final String address="http://localhost:8080";
    private static final String indirizzoEmail="lidomaitai@gmail.com";
    private static final String cellulare="3331122344";


    public MailService (String indirizzoDestinazione, String oggetto, String messaggio) {
        this.host = "smtp.gmail.com";
        this.password = "maitai999";
        this.indirizzoDestinazione = indirizzoDestinazione;
        this.oggetto = oggetto;
        this.messaggio = messaggio;
        this.attachment_title = null;
    }


    /**
     * Invia una mail dall'indirizzo email sorgente all'indirizzo email di destinazione.
     */
    public void sendMail() throws MessagingException {
        // Imposta le proprietà del server SMTP.
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Crea una nuova sessione con un autenticatore.
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(indirizzoEmail, password);
            }
        });

        Message msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress("Lido Maitai <"+indirizzoEmail+">"));
        msg.addRecipient(Message.RecipientType.TO, new InternetAddress(indirizzoDestinazione));
        msg.setSubject(oggetto);
        msg.setContent(messaggio, "text/html");

        // creates message part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(messaggio, "text/html");

        // creates multi-part
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        // Invia il messaggio al destinatario
        Transport.send(msg);
        System.out.println("Email inviata con successo a " + indirizzoDestinazione);
    }

    /**
     * Override del metodo run() della classe Thread. Serve per permettere alle servlet di
     * inviare le mail con thread separati mentre svolgono altre operazioni.
     */
    @Override
    public void run() {
        try {
            this.sendMail();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static String getAddress() {
        return address;
    }

    public static String getCellulare() {
        return cellulare;
    }

    public static String getIndirizzoEmail() {
        return indirizzoEmail;
    }
}