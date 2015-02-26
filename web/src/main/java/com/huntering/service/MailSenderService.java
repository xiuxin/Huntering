package com.huntering.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

@Service
public class MailSenderService {
	
	@Autowired
    private MessageSource messageSource;
	
	@Value(value = "${mail.smtp.host}")
	private String host;
	
	@Value(value = "${email.from}")
    private String mailAccount;
	
	@Value(value = "${email.password}")
    private String mailPassword;
	
	@Value(value = "${mail.smtp.port}")
    private String port = "25";
	
	@Value(value = "${mail.ssl.enable}")
    private Boolean sslEnable;
	
	private final static ExecutorService executorService = Executors.newFixedThreadPool(10);
	
	private static Map<String, Template> emailTemplate;
	private static final String SIGN_UP_VERIFY = "account_signup_verify";
	private static final String RECOVER_PASSWORD = "recover_password";
	private static final String NEW_ACTIVITY_INTERVIEWER = "new_activity_interviewer";
	private static final String NEW_ACTIVITY_INTERVIEWEE = "new_activity_interviewee";
	
	static {
		Configuration configuration = new Configuration();
		configuration.setObjectWrapper(new DefaultObjectWrapper());
		configuration.setOutputEncoding("UTF-8");
		String [] templates = new String[]{SIGN_UP_VERIFY, RECOVER_PASSWORD, NEW_ACTIVITY_INTERVIEWER, NEW_ACTIVITY_INTERVIEWEE};
		emailTemplate = new HashMap<String, Template>(templates.length);
		for(String t : templates) {
			InputStream inputStream = MailSenderService.class.getResourceAsStream("/template/" + t + ".ftl");
			try {
				Template template = new Template(t, new InputStreamReader(inputStream, "UTF-8"), configuration);
				emailTemplate.put(t, template);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	private HtmlEmail createHtmlEmail() throws EmailException {
		HtmlEmail email = new HtmlEmail();
		email.setHostName(host);
		email.setAuthentication(mailAccount, mailPassword);
		email.setFrom(mailAccount);
		if(Boolean.TRUE.equals(sslEnable)) {
			email.setSslSmtpPort(port);
			email.setSSL(true);
		} else {
			try {
				email.setSmtpPort(Integer.valueOf(port));
			} catch(NumberFormatException e) {
				email.setSmtpPort(25); // Default smtp port
			}
		}
		email.setCharset("UTF-8");
		//email.setSubject("test");
		//email.setHtmlMsg("test");
		//email.addTo("bell_qiu@outlook.com");
		return email;
	}
	
	private void sendMail(String subject, String body, String to) throws EmailException {
		sendMail(subject, body, Collections.singletonList(to));
	}
	
	private void sendMail(String subject, String body, List<String> toList) throws EmailException {
		final HtmlEmail email = createHtmlEmail();
		email.setSubject(subject);
		email.setHtmlMsg(body);
		for(String to : toList) {
			email.addTo(to);
		}
		executorService.submit(new Runnable() {

			@Override
			public void run() {
				try {
					email.send();
				} catch (EmailException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}});
	}
	
	private String parseMailContent(String templateName, Map<String, Object> variables) {
		Template template = emailTemplate.get(templateName);
		if(template == null) {
			throw new RuntimeException("Could not get email template for " + templateName);
		}
		try {
			StringWriter writer = new StringWriter();
			template.process(variables, writer);
			return writer.toString();
		} catch (Exception e) {
			throw new RuntimeException("Could not get email template for " + templateName, e);
		}
	}
	
	public void sendSignupVerifyMail(String email, String link) {
		Map<String, Object> variables = new HashMap<String, Object>();
		String subject = messageSource.getMessage("register.mail.subject", new Object[]{}, null);
		variables.put("email", email);
		variables.put("link", link);
		try {
			sendMail(subject, parseMailContent(SIGN_UP_VERIFY, variables), email);
		} catch (EmailException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void sendRecoverPasswordMail(String email, String newPassword) {
		Map<String, Object> variables = new HashMap<String, Object>();
		String subject = messageSource.getMessage("recover.password.subject", new Object[]{}, null);
		variables.put("newPassword", newPassword);
		try {
			sendMail(subject, parseMailContent(RECOVER_PASSWORD, variables), email);
		} catch (EmailException e) {
			throw new RuntimeException(e);
		}
	}
	
}
