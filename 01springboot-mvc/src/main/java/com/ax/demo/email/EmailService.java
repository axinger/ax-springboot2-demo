package com.ax.demo.email;

import com.ax.demo.error.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailService {
    /// http://hutool.mydoc.io/#text_319499
    @Value(value = "${spring.mail.username}")
    private String username;

    ///群发邮件，可选HTML或普通文本，可选多个附件：
    /**
     * ArrayList<String> tos = CollUtil.newArrayList(
     * "person1@bbb.com",
     * "person2@bbb.com",
     * "person3@bbb.com",
     * "person4@bbb.com");
     * <p>
     * MailUtil.send(tos, "测试", "邮件来自Hutool群发测试", false);
     */
    @Autowired
    private JavaMailSenderImpl javaMailSender;

    //简单邮件测试
    public Object sendTextEmail(String email) throws ServiceException {

        System.out.println("username = " + username);
///发送普通文本邮件，最后一个参数可选是否添加多个附件：
///        MailUtil.send("hutool@foxmail.com", "测试", "邮件来自Hutool测试", false);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("email测试");
        message.setText("邮件测试内容");
        message.setTo(email);
        /*必须有发送方*/
        message.setFrom(username);
        try {
            javaMailSender.send(message);
            System.out.println("发送成功");
            return "发送成功";
        } catch (MailException e) {
            System.out.println("e = " + e);
            String msg = "邮件发送失败: " + e.getMessage();
            throw new ServiceException(msg);

//            return "发送失败" + e.getMessage();
        }

    }


    //复杂邮件测试
    public void sendComplicated() throws MessagingException {
//        发送HTML格式的邮件并附带附件，最后一个参数可选是否添加多个附件：
        /// MailUtil.send("hutool@foxmail.com", "测试", "<h1>邮件来自Hutool测试</h1>", true, FileUtil.file("d:/aaa.xml"));
        //创建一个复杂的消息邮件
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        //用MimeMessageHelper来包装MimeMessage
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setSubject("email测试");
        mimeMessageHelper.setText("邮件测试内容");
        mimeMessageHelper.setTo("fanqixxxx@vip.qq.com");
        mimeMessageHelper.setFrom("fanqixxxx@163.com");
        mimeMessageHelper.addAttachment("meinv.jpg", new File("D:\\meinv.jpg"));
        javaMailSender.send(mimeMessage);

    }
}