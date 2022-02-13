package Dao;

import Entity.History;

import java.util.List;

public interface HistoryDao {
    List<History> findByUser(String username);
    List<History> findByUserIsLiked(String username);
    History findUserIdAndVideoID(Integer userId,Integer videoId);
    History create(History entity);
    History update(History entity);

}
