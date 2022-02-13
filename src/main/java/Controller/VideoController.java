package Controller;

import Constant.SessionAttr;
import Entity.History;
import Entity.User;
import Entity.Video;
import Servlet.HistoryService;
import Servlet.Impl.HistoryServiceImpl;
import Servlet.Impl.VideoServiceImpl;
import Servlet.VideoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/video")
public class VideoController extends HttpServlet {

private VideoService videoService = new VideoServiceImpl();
private HistoryService historyService = new HistoryServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String actionParam = req.getParameter("action");
        String href = req.getParameter("id");
        HttpSession session = req.getSession();

        switch (actionParam){
            case "watch":
                doGetWatch(session,href,req,resp);
                break;
                case "like":
                doGetLike(session,href,req,resp);
                break;
        }
    }
        //localhost:8080/asm_java4/video?action=watch&id={href}
        private void doGetWatch(HttpSession session,String href,HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
            Video video = videoService.findByHref(href);
            req.setAttribute("video",video);

            User currentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);

            if (currentUser!=null){
                History history = historyService.create(currentUser,video);
                req.setAttribute("flagLikeBtn",history.isLiked());//flagLikeBtn cắm cờ
            }

            req.getRequestDispatcher("/Views/Users/video-detail.jsp").forward(req,resp);
        }
        private void doGetLike(HttpSession session,String href,HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
                resp.setContentType("application/json");
            User currentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);
            boolean result = historyService.updateLikeOrUnlike(currentUser,href);
            if (result==true){
                resp.setStatus(204);//204 có nghĩ là thành công nhưng k trả data
            }else {
                resp.setStatus(400);
            }
        }
}
