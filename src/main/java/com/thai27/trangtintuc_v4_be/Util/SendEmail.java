package com.thai27.trangtintuc_v4_be.Util;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendEmail {

    @Autowired
    JavaMailSender mailSender;

    public String sendNewPassword(String userMail,String username, String password) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("FIS.auto.service@gmail.com");
        message.setTo(userMail);
        message.setSubject("Tài khoản " + username + " đã reset mật khẩu thành công");
        message.setText("Mật khẩu mới là: " + password);
        mailSender.send(message);
        return "Reset mật khẩu thành công !";
    }

}
