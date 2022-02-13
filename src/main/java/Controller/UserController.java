package Controller;

import Constant.SessionAttr;
import Entity.User;
import Entity.Video;
import Servlet.EmailService;
import Servlet.Impl.EmailServiceImpl;
import Servlet.Impl.UserServiceImpl;
import Servlet.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/login", "/logout", "/register", "/forgotPass", "/changePass"})
public class UserController extends HttpServlet {
    private static final long serialVersionUID = -5860351843059541642L;
    private UserService userService = new UserServiceImpl();
    private EmailService emailService = new EmailServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String path = req.getServletPath();//lấy path ví dụ localhost:8080/asm_java4/login --> path="/login"
        switch (path) {
            case "/login":
                doGetLogin(req, resp);
                break;
            case "/register":
                doGetRegister(req, resp);
                break;
            case "/logout":
                doGetlogout(session, req, resp);
                break;
            case "/forgotPass":
                doGetForgotPass(req, resp);
                break;
        }
    }

    private void doGetLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/Views/Users/login.jsp").forward(req, resp);
    }

    private void doGetRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/Views/Users/register.jsp").forward(req, resp);
    }

    private void doGetForgotPass(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/Views/Users/forgot-pass.jsp").forward(req, resp);
    }

    private void doGetlogout(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        session.removeAttribute(SessionAttr.CURRENT_USER);
        resp.sendRedirect("index");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String path = req.getServletPath();//lấy path ví dụ localhost:8080/asm_java4/login --> path="/login"
        switch (path) {
            case "/login":
                doPostLogin(session, req, resp);
                break;
            case "/register":
                doPostRegister(session, req, resp);
                break;
            case "/forgotPass":
                doPostForgotPass(req, resp);
                break;
            case "/changePass":
                doPostChangePass(session, req, resp);
                break;
        }
    }

    private void doPostLogin(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        System.out.println(username + password);
        User user = userService.login(username, password);

        if (user != null) {
            session.setAttribute(SessionAttr.CURRENT_USER, user);
            resp.sendRedirect("index");
        } else {
            resp.sendRedirect("login");
        }
    }

    private void doPostRegister(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        System.out.println(username + password);
        User user = userService.create(username, password, email);

        if (user != null) {
            emailService.sendEmail(getServletContext(), user, "welcome");
            session.setAttribute(SessionAttr.CURRENT_USER, user);
            resp.sendRedirect("index");
        } else {
            resp.sendRedirect("register");
        }
    }

    private void doPostForgotPass(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        String email = req.getParameter("email");
        User userWithNewPass = userService.resetPassword(email);

        if (userWithNewPass != null) {
            emailService.sendEmail(getServletContext(), userWithNewPass, "forgot");
            resp.setStatus(204);
        } else {
            resp.setStatus(400);
        }
    }

    private void doPostChangePass(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        String currentPass = req.getParameter("currentPass");
        String newPass = req.getParameter("newPass");

        User currentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);

        if (currentUser.getPassword().equals(currentPass)) {
            currentUser.setPassword(newPass);
            User updatedUser = userService.update(currentUser);
            if (updatedUser != null) {
                session.setAttribute(SessionAttr.CURRENT_USER, updatedUser);
                resp.setStatus(204);
            } else {
                resp.setStatus(400);
            }
        } else {
            resp.setStatus(400);
        }
    }
}

