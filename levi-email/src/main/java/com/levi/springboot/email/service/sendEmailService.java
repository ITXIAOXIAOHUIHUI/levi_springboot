package com.levi.springboot.email.service;

import com.levi.springboot.email.mapper.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author jianghaihui
 * @date 2020/1/10 15:45
 */

public class sendEmailService {

    @Autowired
    private JavaMailSender javaMailSender;


    public void htmlEmail() throws MessagingException {
        // MimeMessage 本身的 API 有些笨重，我们可以使用 MimeMessageHelper
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        // 第二个参数是 true ，表明这个消息是 multipart类型的/
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom("18419512107@163.com");
        mimeMessageHelper.setTo("18549818964@163.com");
        mimeMessageHelper.setSubject("附件邮件主题");
        // 设置内嵌元素 cid，第一个参数表示 内联图片的标识符，第二个参数标识资源引用
        mimeMessageHelper.addInline("boot", new ClassPathResource("public/images/boot.png"));
        String html = "<html><body><h4>Hello,SpringBoot</h4><img src='cid:boot' /></body></html>";
        mimeMessageHelper.setText(html, true);
        // 设置内嵌元素 cid，第一个参数表示 内联图片的标识符，第二个参数标识资源引用
        mimeMessageHelper.addInline("boot", new ClassPathResource("public/images/boot.png"));
        //添加附件,第一个参数表示添加到 Email 中附件的名称，第二个参数是图片资源
        mimeMessageHelper.addAttachment("boot.png", new ClassPathResource("public/images/boot.png"));
        javaMailSender.send(mimeMessage);
    }

    public void addEmailSendtor(){

    }
}
