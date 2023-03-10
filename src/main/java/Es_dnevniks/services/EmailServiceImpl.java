package Es_dnevniks.services;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import Es_dnevniks.entities.EmailObject;

@Service
public class EmailServiceImpl implements EmailService {
	
	@Autowired
	public JavaMailSender emailSender;
	
	@Override
    public void sendSimpleMessage(EmailObject object) throws Exception {
        
        MimeMessage mail = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mail, true);
        
        helper.setTo(object.getTo());
        helper.setSubject(object.getSubject());
        helper.setText(object.getText(), true);
        
        emailSender.send(mail);
    }

}
