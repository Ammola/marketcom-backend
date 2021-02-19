package com.ga.marketcom.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ga.marketcom.dao.UserDao;
import com.ga.marketcom.model.User;
import com.ga.marketcom.model.passwordReq;

import net.bytebuddy.utility.RandomString;

@RestController
public class ForgotPasswordController {
	@Autowired
    private JavaMailSender mailmessage;
	
	@Autowired
	private UserDao dao;
	@Autowired 
	HttpServletRequest request;
	
	@PutMapping("/user/forgotpassword")
	public User forgotPassword(@RequestBody User user) throws UnsupportedEncodingException, MessagingException{
		HashMap<String,String> response=new HashMap<String,String>();
        String random = RandomString.make(35);
        
       
        user=dao.findByEmailAddress(user.getEmailAddress());
        
        if (user != null) {
        	user.setResetPassword(random);
        	 response.put("message", "Email Found"+user);
            dao.save(user);
           
        } 
        String PasswordLink = Utility.getSiteURL(request) + "/user/resetpassword?random=" + random;
		sendEmail(user.getEmailAddress(), PasswordLink);
		return user;
	}
	private void sendEmail(String emailAddress, String PasswordLink) throws UnsupportedEncodingException, MessagingException {
		
	    MimeMessage email = mailmessage.createMimeMessage();              
	    MimeMessageHelper helper = new MimeMessageHelper(email);
	     
	    helper.setFrom("contact@MARKETCOM.com", "MARKETCOM Support");
	    helper.setTo(emailAddress);
	     
	    String subject = "Password Reset";
	     
	    String content = "<p>Hello, </p>"
	            + "<p>Please click following link to reset you password. This link can be used only once. .</p>"
	            + "<p><a href=\"" + PasswordLink + "\">Change my password</a></p>"
	            + "<br>"
	            + "<p>Best Regards,"
	            + "The Support Team</p>";
	     
	    helper.setSubject(subject);
	     
	    helper.setText(content, true);
	     
	    mailmessage.send(email);
	}
	
	@PutMapping("/user/resetpassword")
	public User processResetPassword( @RequestParam String random,@RequestBody passwordReq req) {
		User user= new User();
		 user=dao.findByResetPassword(random);
		 if (user != null) {
			 String pass=req.getPassword();
			 BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	        String encodedPassword = passwordEncoder.encode(pass);
	        user.setPassword(encodedPassword);
	        user.setResetPassword(null);
	        dao.save(user);
		 
		 }

		return user;
	}

}
