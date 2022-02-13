package Servlet.Impl;

import Dao.HistoryDao;
import Dao.impl.HistoryDaoImpl;
import Entity.History;
import Entity.User;
import Entity.Video;
import Servlet.HistoryService;
import Servlet.VideoService;

import java.sql.Timestamp;
import java.util.List;

public class HistoryServiceImpl implements HistoryService {
    private HistoryDao dao;
    private VideoService videoService = new VideoServiceImpl();

    public HistoryServiceImpl() {
        this.dao = new HistoryDaoImpl();
    }

    @Override
    public List<History> findByUser(String username) {
        return dao.findByUser(username);
    }

    @Override
    public List<History> findByUserIsLiked(String username) {
        return dao.findByUserIsLiked(username);
    }

    @Override
    public History findUserIdAndVideoID(Integer userId, Integer videoId) {
        return dao.findUserIdAndVideoID(userId,videoId);
    }

    @Override
    public History create(User user, Video video) {
        History existhistory = findUserIdAndVideoID(user.getId(),video.getId());
        if (existhistory==null){
            existhistory = new History();
            existhistory.setUser(user);
            existhistory.setVideo(video);
            existhistory.setViewedDate(new Timestamp(System.currentTimeMillis()));
            existhistory.setLiked(Boolean.FALSE);
            dao.create(existhistory);
        }
        return existhistory;
    }

    @Override
    public boolean updateLikeOrUnlike(User user, String videoHref) {
        Video video = videoService.findByHref(videoHref);
        History existHistory = findUserIdAndVideoID(user.getId(),video.getId());

        if (existHistory.isLiked()==Boolean.FALSE){
            existHistory.setLiked(Boolean.TRUE);
            existHistory.setLikedDate(new Timestamp(System.currentTimeMillis()));
        }else {
            existHistory.setLiked(Boolean.FALSE);
            existHistory.setLikedDate(null);
        }
        History updateHistory = dao.update(existHistory);
        return updateHistory != null ? true:false;
    }
}
