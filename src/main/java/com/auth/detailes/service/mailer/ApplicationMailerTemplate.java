package com.auth.detailes.service.mailer;

import com.auth.detailes.business.entites.auth.User;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class ApplicationMailerTemplate {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationMailerTemplate.class);

    @Autowired ApplicationMailer mailer;
    @Autowired
    private FreeMarkerConfigurer freemarkerConfigurer;

    public void notifyPasswordChanged(String name, String login, String email, String password,String subject){
        logger.info(String.format("Sending Mail to %s Password %s", email, password));
        try {
            MailData mailData = new MailData();
            mailData.setTo(email);
            mailData.setSubject("SADI-TECH -  Notification changement de mot de passe");
            mailData.setFrom("saditech.center@gmail.com");
            Map<String, Object> templateModel = new HashMap<>();
            templateModel.put("fullname", name);
            templateModel.put("login", login);
            templateModel.put("password", password);
            templateModel.put("subject", subject);
            Template freemarkerTemplate = freemarkerConfigurer.getConfiguration().getTemplate("notification-change-password.template.ftl");
            String htmlBody = FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerTemplate, templateModel);
            mailData.setContent(htmlBody);
            mailer.sendMailWithAttachment(mailData);
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public void notifyPatrimone(String name, String email,String subject){
        logger.info(String.format("Sending Mail to %s", email));
        try {
            MailData mailData = new MailData();
            mailData.setTo(email);
            mailData.setSubject("SIGMA-TOP -  Notification PATRIMOINE BIENTÔT EXPIRE");
            mailData.setFrom("saditech.center@gmail.com");
            Map<String, Object> templateModel = new HashMap<>();
            templateModel.put("fullname", name);
            templateModel.put("subject", subject);
            Template freemarkerTemplate = freemarkerConfigurer.getConfiguration().getTemplate("notification-patrimine.template.ftl");
            String htmlBody = FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerTemplate, templateModel);
            mailData.setContent(htmlBody);
            mailer.sendMailWithAttachment(mailData);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void notifyValidationProduct(String subject, Objects produit, User user){
        logger.info(String.format("Sending Mail to %s ", user.getEmail()));
        try {
            MailData mailData = new MailData();
            mailData.setTo(user.getEmail());
            mailData.setSubject("SADI-TECH -  Notification validation de produit "+ "");
            mailData.setFrom("saditech.center@gmail.com");
            Map<String, Object> templateModel = new HashMap<>();
            templateModel.put("fullname", user.getFirstName() + " "+ user.getLastName());
            templateModel.put("nomProduit", "");
            templateModel.put("subject", subject);
            Template freemarkerTemplate = freemarkerConfigurer.getConfiguration().getTemplate("notification-validation-product.template.ftl");
            String htmlBody = FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerTemplate, templateModel);
            mailData.setContent(htmlBody);
            mailer.sendMailWithAttachment(mailData);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void sendExcelExportMailProtectionPassword(User user, String protectionPassword){
        logger.info(String.format("Sending Mail to %s Password %s", user.getEmail(), protectionPassword));
    }
}
