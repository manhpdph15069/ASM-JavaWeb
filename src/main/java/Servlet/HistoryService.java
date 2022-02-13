package Servlet;

import Entity.History;
import Entity.User;
import Entity.Video;

import java.util.List;

public interface HistoryService {
    List<History> findByUser(String username);
    List<History> findByUserIsLiked(String username);
    History findUserIdAndVideoID(Integer userId,Integer videoId);
    History create(User user, Video video);
    boolean updateLikeOrUnlike(User user,String videoHref);
}
