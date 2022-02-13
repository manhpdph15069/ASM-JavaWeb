package Servlet.Impl;

import Entity.User;
import Servlet.EmailService;
import Utils.SendEmailUtils;

import javax.servlet.ServletContext;

public class EmailServiceImpl implements EmailService {
    private static final String EMAIL_WELCOME_SUBJECT = "Welcome to You tu be của Mạnh Không Pro";
    private static final String EMAIL_WELCOME_PASSWORD  = "You tu be của Mạnh Không Pro - New password";
    @Override
    public void sendEmail(ServletContext context, User recipient, String type) {
        String host = context.getInitParameter("host");
        String port = context.getInitParameter("port");
        String user = context.getInitParameter("user");
        String pass = context.getInitParameter("pass");
        try {
            String content=null;
            String subject =null;
            switch (type){
                case "welcome":
                    subject=EMAIL_WELCOME_SUBJECT;
                    content = "Dear "+ recipient.getUsername();
                    break;
                case "forgot":
                    subject= EMAIL_WELCOME_PASSWORD;
                    content="Dear " + recipient.getUsername() + " , your new password: " + recipient.getPassword();
                    break;
                default:
                    subject = "Welcome to You tu be của Mạnh Không Pro";
                    content="Không thể sảy ra =))";
            }
            SendEmailUtils.sendEmail(host,port,user,pass,recipient.getEmail(),subject,content);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
