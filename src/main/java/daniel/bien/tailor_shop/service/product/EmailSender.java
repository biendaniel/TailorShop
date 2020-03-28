package daniel.bien.tailor_shop.service.product;

import daniel.bien.tailor_shop.model.order.EmailParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(EmailParameters param) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(param.getRecipient());
        msg.setSubject(param.getSubject());
        msg.setText(param.getContent());
        javaMailSender.send(msg);

    }
}