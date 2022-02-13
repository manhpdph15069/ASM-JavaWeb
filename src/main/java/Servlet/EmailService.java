package Servlet;

import Entity.User;

import javax.servlet.ServletContext;

public interface EmailService {
    void sendEmail(ServletContext context, User recipient,String type);

}
