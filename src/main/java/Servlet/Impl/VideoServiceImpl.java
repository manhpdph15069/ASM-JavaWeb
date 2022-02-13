package Servlet.Impl;

import Dao.VideoDao;
import Dao.impl.VideoDaoImpl;
import Entity.Video;
import Servlet.VideoService;

import java.util.List;

public class VideoServiceImpl implements VideoService {
    VideoDao dao;

    public VideoServiceImpl() {
        this.dao = new VideoDaoImpl();
    }

    @Override
    public Video findById(Integer id) {
        return dao.findById(id);
    }

    @Override
    public Video findByHref(String href) {
        return dao.findByHref(href);
    }

    @Override
    public List<Video> findAll() {
        return dao.findAll();
    }

    public List<Video> findAll(int pageNumber, int pageSize) {
        return dao.findAll(pageNumber, pageSize);
    }

    @Override
    public Video create(Video entity) {
        entity.setIsActive(Boolean.TRUE);
        entity.setViews(0);
        entity.setShare(0);
        return dao.create(entity);
    }

    @Override
    public Video update(Video entity) {
        return dao.update(entity);
    }

    @Override
    public Video delete(String href) {
        Video entity = findByHref(href);
        entity.setIsActive(Boolean.FALSE);
        return dao.update(entity);
    }
}
