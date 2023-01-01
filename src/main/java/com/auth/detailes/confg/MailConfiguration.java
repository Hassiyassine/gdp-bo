package com.auth.detailes.confg;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import java.util.Properties;

@org.springframework.context.annotation.Configuration
public class MailConfiguration {

    @Value("${shost}")
    private String mailServerHost;

    @Value("${sport}")
    private Integer mailServerPort;

    @Value("${susername}")
    private String mailServerUsername;

    @Value("${spassword}")
    private String mailServerPassword;

    @Value("${sauth}")
    private String mailServerAuth;

    @Value("${senable}")
    private String mailServerStartTls;

    @Value("${spath}")
    private String mailTemplatesPath;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(mailServerHost);
        mailSender.setPort(mailServerPort);

        mailSender.setUsername(mailServerUsername);
        mailSender.setPassword(mailServerPassword);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", mailServerAuth);
        props.put("mail.smtp.starttls.enable", mailServerStartTls);
        props.put("mail.debug", "true");

        return mailSender;
    }

    @Primary
    @Bean
    public FreeMarkerConfigurer freemarkerClassLoaderConfig() {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_27);
        TemplateLoader templateLoader = new ClassTemplateLoader(this.getClass(), "/" + mailTemplatesPath);
        configuration.setTemplateLoader(templateLoader);
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setConfiguration(configuration);
        return freeMarkerConfigurer;
    }
}
