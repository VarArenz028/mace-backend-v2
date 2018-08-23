package net.incuventure.util;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
    import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.util.List;
import java.util.Properties;
//import org.apache.log4j.Logger;



/**
 * Created by angulo on 11/21/2016.
 */
public class EmailSender {


    private static Logger logger = LoggerFactory.getLogger(EmailSender.class);

        //The equivalent in properties file is "mail.username"
        private String username;
        //The equivalent in properties file is "mail.password"
        private String password;
        //The equivalent in properties file is "mail.default.from"
        private String defaultFrom;
        //The equivalent in properties file is "mail.smtp.auth"
        private String smtpAuth;
        //The equivalent in properties fie is "mail.smtp.starttls.enable"
        private String smtpStartTtlsEnable;
        //The equivalent in properties file is "mail.smtp.host"
        private String smtpHost;
        //The equivalent in properties file is "mail.smtp.port"
        private String smtpPort;

        /**
         * Method that sends mail fully configurable where the body is mime type text or html depending on @param type
         * @param username
         * @param password
         * @param defaultFrom
         * @param smtpAuth
         * @param smtpStartTtlsEnable
         * @param smtpHost
         * @param smtpPort
         * @param toEmails
         * @param ccEmails
         * @param subject
         * @param body
         * @param type
         * @throws Exception
         */
        public void send( final String username,  final String password, String defaultFrom,
                          String smtpAuth, String smtpStartTtlsEnable, String smtpHost, String smtpPort,
                          List<String> toEmails, List<String> ccEmails, String subject, String body, String type) throws Exception {
            logger.info("send");
            setUsername(username);
            setPassword(password);
            setDefaultFrom(defaultFrom);

            if(null == type || "text".equalsIgnoreCase(type) || !"text/html; charset=utf-8".equalsIgnoreCase(type)){
                logger.info("null == type || \"text\".equalsIgnoreCase(type) || !\"text/html; charset=utf-8\".equalsIgnoreCase(type)");
                this.send(username, password, defaultFrom,
                        smtpAuth, smtpStartTtlsEnable, smtpHost, smtpPort,
                        toEmails, ccEmails, subject, body);
            } else {
                logger.info("ELSE : null == type || \"text\".equalsIgnoreCase(type) || !\"text/html; charset=utf-8\".equalsIgnoreCase(type)");
                Properties mailProps = new Properties();
                mailProps.setProperty("mail.username", username);
                mailProps.setProperty("mail.password", password);
                mailProps.setProperty("mail.default.from", defaultFrom);
                mailProps.setProperty("mail.smtp.auth", smtpAuth);
                mailProps.setProperty("mail.smtp.starttls.enable", smtpStartTtlsEnable);
                mailProps.setProperty("mail.smtp.host", smtpHost);
                mailProps.setProperty("mail.smtp.port", smtpPort);


                Session session = Session.getInstance(mailProps,
                        new Authenticator() {
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(getUsername(), getPassword());
                            }
                        });

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(getDefaultFrom()));

                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmails.toString().replaceAll("^\\[", "").replaceAll("\\]$", "")));
                if (!ccEmails.isEmpty()) {
                    message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccEmails.toString().replaceAll("^\\[", "").replaceAll("\\]$", "")));
                }

                if (subject == null) {
                    subject = "";
                }
                if (body == null) {
                    body = "";
                }

                type = "text/html; charset=utf-8";
                message.setContent(body, type);
                message.setSubject(subject);

                Transport transport = session.getTransport("smtp");
                transport.connect(smtpHost, username, password);
                transport.sendMessage(message, message.getAllRecipients());
            }

        }

    /**
     * Shem Ardi San Diego - Added another sendEmail function that has email attachments
     * @param username
     * @param password
     * @param defaultFrom
     * @param smtpAuth
     * @param smtpStartTtlsEnable
     * @param smtpHost
     * @param smtpPort
     * @param toEmails
     * @param ccEmails
     * @param subject
     * @param body
     * @param emailAttachments
     * @throws MessagingException
     */
        public void send( final String username, final String password, String defaultFrom,
                          String smtpAuth, String smtpStartTtlsEnable, String smtpHost, String smtpPort,
                          List<String> toEmails, List<String> ccEmails, String subject, String body, List<EmailAttachment> emailAttachments) throws MessagingException {

            setUsername(username);
            setPassword(password);
            setDefaultFrom(defaultFrom);

            Properties mailProps = new Properties();
            mailProps.setProperty("mail.username", username);
            mailProps.setProperty("mail.password", password);
            mailProps.setProperty("mail.default.from", defaultFrom);
            mailProps.setProperty("mail.smtp.auth", smtpAuth);
            mailProps.setProperty("mail.smtp.starttls.enable", smtpStartTtlsEnable);
            mailProps.setProperty("mail.smtp.host", smtpHost);
            mailProps.setProperty("mail.smtp.port", smtpPort);


            Session session = Session.getInstance(mailProps,
                    new Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(getUsername(), getPassword());
                        }
                    });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(getDefaultFrom()));

            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmails.toString().replaceAll("^\\[", "").replaceAll("\\]$", "")));
            if (!ccEmails.isEmpty()) {
                message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccEmails.toString().replaceAll("^\\[", "").replaceAll("\\]$", "")));
            }

            if (subject == null) {
                subject = "";
            }
            if (body == null) {
                body = "";
            }

            message.setSubject(subject);

            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setContent(body,"text/html;charset=utf-8");

            // Attach files
            MimeMultipart mimeMultipart = new MimeMultipart();
            mimeMultipart.addBodyPart(textBodyPart);

            for (EmailAttachment attachment: emailAttachments) {
                DataSource dataSource = new ByteArrayDataSource(attachment.getFileContent(), attachment.getFileType());
                MimeBodyPart bodyPart = new MimeBodyPart();
                bodyPart.setDataHandler(new DataHandler(dataSource));
                bodyPart.setFileName(attachment.getFileName());

                mimeMultipart.addBodyPart(bodyPart);
            }

            message.setContent(mimeMultipart);
            Transport transport = session.getTransport("smtp");
            transport.connect(smtpHost, username, password);
            transport.sendMessage(message, message.getAllRecipients());
        }

        /**
         * Method that sends mail fully configurable where the body is assumed to be only text
         * @param username
         * @param password
         * @param defaultFrom
         * @param smtpAuth
         * @param smtpStartTtlsEnable
         * @param smtpHost
         * @param smtpPort
         * @param toEmails
         * @param ccEmails
         * @param subject
         * @param body
         * @throws Exception
         */
        public void send( final String username, final String password, String defaultFrom,
                          String smtpAuth, String smtpStartTtlsEnable, String smtpHost, String smtpPort,
                          List<String> toEmails, List<String> ccEmails, String subject, String body) throws Exception {

            setUsername(username);
            setPassword(password);
            setDefaultFrom(defaultFrom);

            Properties mailProps = new Properties();
            mailProps.setProperty("mail.username", username);
            mailProps.setProperty("mail.password", password);
            mailProps.setProperty("mail.default.from", defaultFrom);
            mailProps.setProperty("mail.smtp.auth", smtpAuth);
            mailProps.setProperty("mail.smtp.starttls.enable", smtpStartTtlsEnable);
            mailProps.setProperty("mail.smtp.host", smtpHost);
            mailProps.setProperty("mail.smtp.port", smtpPort);


            Session session = Session.getInstance(mailProps,
                    new Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(getUsername(), getPassword());
                        }
                    });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(getDefaultFrom()));

            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmails.toString().replaceAll("^\\[", "").replaceAll("\\]$", "")));
            if (!ccEmails.isEmpty()) {
                message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccEmails.toString().replaceAll("^\\[", "").replaceAll("\\]$", "")));
            }

            if (subject == null) {
                subject = "";
            }
            if (body == null) {
                body = "";
            }

            message.setSubject(subject);
            message.setText(body);

            Transport transport = session.getTransport("smtp");
            transport.connect(smtpHost, username, password);
            transport.sendMessage(message, message.getAllRecipients());

        }

        String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        String getDefaultFrom() {
            return defaultFrom;
        }

        public void setDefaultFrom(String defaultFrom) {
            this.defaultFrom = defaultFrom;
        }

        public String getSmtpAuth() {
            return smtpAuth;
        }

        public void setSmtpAuth(String smtpAuth) {
            this.smtpAuth = smtpAuth;
        }

        public String getSmtpStartTtlsEnable() {
            return smtpStartTtlsEnable;
        }

        public void setSmtpStartTtlsEnable(String smtpStartTtlsEnable) {
            this.smtpStartTtlsEnable = smtpStartTtlsEnable;
        }

        public String getSmtpHost() {
            return smtpHost;
        }

        public void setSmtpHost(String smtpHost) {
            this.smtpHost = smtpHost;
        }

        public String getSmtpPort() {
            return smtpPort;
        }

        public void setSmtpPort(String smtpPort) {
            this.smtpPort = smtpPort;
        }
}
