/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.common;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Mail {

    private static String from = "ntd0021995@gmail.com";
    private static String pwd = "daihockt95";

    public static void send(String to, String subject, String body) throws Exception {
        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");
        Authenticator pa = null;
        if (from != null && pwd != null) {
            props.put("mail.smtp.auth", "true");
            pa = new Authenticator() {
                @Override
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, pwd);
                }
            };
        }
        Session session = Session.getInstance(props, pa);
        MimeMessage mimeMsg = new MimeMessage(session);
        mimeMsg.setSubject(subject, "utf8");
        mimeMsg.setFrom(new InternetAddress(from));
        mimeMsg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(
                to, false));
        mimeMsg.setContent(body, "text/html; charset=UTF-8");
        mimeMsg.setHeader("Content-Type", "text/html; charset=UTF-8");
        mimeMsg.setSentDate(new Date());
        mimeMsg.saveChanges();
        Transport.send(mimeMsg);
        System.out.println("Mail da duoc gui");

    }
}
