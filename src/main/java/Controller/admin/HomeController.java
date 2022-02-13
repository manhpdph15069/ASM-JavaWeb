package Controller.admin;

import Constant.SessionAttr;
import Dao.dto.UserDto;
import Dao.dto.VideoLikedInfo;
import Entity.User;
import Servlet.Impl.StatsServiceImpl;
import Servlet.Impl.UserServiceImpl;
import Servlet.StatsService;
import Servlet.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = {"/admin","/admin/favorites"}, name = "HomeControllerOfAdmin")
public class HomeController extends HttpServlet {

    private StatsService statsService = new StatsServiceImpl();
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);
        if (currentUser != null && currentUser.isAdmin() == Boolean.TRUE) {
            String path = req.getServletPath();//lấy path ví dụ localhost:8080/asm_java4/login --> path="/login"
            switch (path) {
                case "/admin":
                    doGetHome(req, resp);
                    break;
                case "/admin/favorites":
                    doGetFavorites(req, resp);
                    break;
            }
           } else {
            resp.sendRedirect("index");
        }
    }

    private void doGetHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<VideoLikedInfo> videos = statsService.findVideoLikedInfo();
        req.setAttribute("videos", videos);
        req.getRequestDispatcher("/Views/Admin/home.jsp").forward(req, resp);
    }
    private void doGetFavorites(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        String videoHref = req.getParameter("href");
        List<UserDto> users = userService.findUsersLikedVideoByVideoHref(videoHref);
        if (users.isEmpty()){
            resp.setStatus(400);
        }else {
            ObjectMapper objectMapper = new ObjectMapper();
            String dataResponse = objectMapper.writeValueAsString(users);
            resp.setStatus(200);
            out.print(dataResponse);
            out.flush();
        }
    }
}
