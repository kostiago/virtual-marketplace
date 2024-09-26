package com.kostiago.backend.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Configuration fmConfiguration;

    @Value("${spring.mail.username}")
    private String sender;

    public String sendEmailText(String receiver, String title, String message) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(sender);
            simpleMailMessage.setTo(receiver);
            simpleMailMessage.setSubject(title);
            simpleMailMessage.setText(message);
            javaMailSender.send(simpleMailMessage);

            System.err.println("ATÉ AQYU");
            return "Email enviado com sucesso";
        } catch (Exception e) {
            System.err.println("DEU MERDA BINA");
            return "Erro ao enviar email";

        }
    }

    public void sendEmailTemplate(String receiver, String title, Map<String, Object> properties) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setSubject(title);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(receiver);

            mimeMessageHelper.setText(getContentFromTemplate(properties), true);

            javaMailSender.send(mimeMessageHelper.getMimeMessage());

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public String getContentFromTemplate(Map<String, Object> model) {
        StringBuffer content = new StringBuffer();

        try {
            content.append(FreeMarkerTemplateUtils
                    .processTemplateIntoString(fmConfiguration.getTemplate("email-recovery-code.flth"), model));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return content.toString();
    }
}
