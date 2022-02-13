package Controller;


//import Servlet.VideoService;

import Constant.SessionAttr;
import Entity.History;
import Entity.User;
import Entity.Video;
import Servlet.Impl.HistoryServiceImpl;
import Servlet.Impl.VideoServiceImpl;
import Servlet.VideoService;
import Servlet.HistoryService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns ={ "/index", "/favorites","/history"})
public class HomeController extends HttpServlet {
    private VideoService videoService = new VideoServiceImpl();
    private HistoryService historyService = new HistoryServiceImpl();
    private static final int VIDEO_MAX_SIZE =2;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String path = request.getServletPath();//lấy path ví dụ localhost:8080/asm_java4/login --> path="/login"
        switch (path) {
            case "/index":
                doGetIndex(request, response);
                break;
            case "/favorites":
                doGetFavorites(session,request, response);
                break;
            case "/history":
                doGetHistory(session,request, response);
                break;
        }
    }
    private void doGetIndex(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Video> countVideo =  videoService.findAll();
        int maxPage =(int) Math.ceil(countVideo.size()/ (double)VIDEO_MAX_SIZE);//Math.ceil lamf tronf so
        req.setAttribute("maxPage",maxPage);

        List<Video> videos;
        String pageNumber = req.getParameter("page");
        if (pageNumber==null || Integer.valueOf(pageNumber)>maxPage){
            videos= videoService.findAll(1,VIDEO_MAX_SIZE);
            req.setAttribute("currentPage",1);
        }else {
            videos = videoService.findAll(Integer.valueOf(pageNumber),VIDEO_MAX_SIZE);
            req.setAttribute("currentPage",Integer.valueOf(pageNumber));

        }

        req.setAttribute("videos", videos);
        RequestDispatcher fomDispatcher = req.getRequestDispatcher("/Views/Users/index.jsp");
        fomDispatcher.forward(req, resp);
        }

        private void doGetFavorites(HttpSession session,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            User user = (User) session.getAttribute(SessionAttr.CURRENT_USER);

            List<History> histories = historyService.findByUserIsLiked(user.getUsername());
            List<Video> videos = new ArrayList<>();

            histories.forEach(item -> videos.add(item.getVideo()));
            /*
             * @author: Mạnh Phạm
             *@since: 12/31/2021 3:52 PM
             *Mô tả: forEach
             * for(int i=0;i<history.size();i++){
             *  videos.add(history.get(i).getVideo());
             * }
             *update:item là history.get(i)
             *
            **/

        req.setAttribute("videos", videos);
        RequestDispatcher fomDispatcher = req.getRequestDispatcher("/Views/Users/favorites.jsp");
        fomDispatcher.forward(req, resp);
        }
        private void doGetHistory(HttpSession session,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            User user = (User) session.getAttribute(SessionAttr.CURRENT_USER);

            List<History> histories = historyService.findByUser(user.getUsername());
            List<Video> videos = new ArrayList<>();

            histories.forEach(item -> videos.add(item.getVideo()));
            /*
             * @author: Mạnh Phạm
             *@since: 12/31/2021 3:52 PM
             *Mô tả: forEach
             * for(int i=0;i<history.size();i++){
             *  videos.add(history.get(i).getVideo());
             * }
             *update:item là history.get(i)
             *
            **/

        req.setAttribute("videos", videos);
        RequestDispatcher fomDispatcher = req.getRequestDispatcher("/Views/Users/history.jsp");
        fomDispatcher.forward(req, resp);
        }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
