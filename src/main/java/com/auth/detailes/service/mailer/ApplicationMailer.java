package com.auth.detailes.service.mailer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Map;

/**
 * @author 	: Yassin OUICHOU
 * Email	: Ouichou.IT@gmail.com
 */

@Service
public class ApplicationMailer {

    @Autowired JavaMailSender mailSender;

    public void sendMailWithAttachment(MailData mailData)
    {
        try {
            MimeMessagePreparator preparator = new MimeMessagePreparator()
            {
                public void prepare(MimeMessage message) throws Exception
                {

                    message.setRecipient(Message.RecipientType.TO, new InternetAddress(mailData.getTo()));
                    message.setFrom(new InternetAddress(mailData.getFrom()));
                    message.setSubject(mailData.getSubject());

                    Multipart multipart = new MimeMultipart();

                    if(mailData.getContent() != null){
                        MimeBodyPart contentPart = new MimeBodyPart();
                        contentPart.setContent(mailData.getContent(),"text/html");
                        multipart.addBodyPart(contentPart);
                    }

                    if(mailData.getAttachments() != null && ! mailData.getAttachments().isEmpty()){
                        for (Map.Entry<String, DataHandler> entry: mailData.getAttachments().entrySet()) {
                            MimeBodyPart attachmentPart = new MimeBodyPart();
                            if(entry.getKey() != null){
                                attachmentPart.setFileName(entry.getKey());
                            }
                            attachmentPart.setDataHandler(entry.getValue());
                            multipart.addBodyPart(attachmentPart);
                        }
                    }
                    message.setContent(multipart);
                }
            };

            mailSender.send(preparator);
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
