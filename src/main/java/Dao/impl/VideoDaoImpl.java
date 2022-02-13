package Dao.impl;

import Dao.AbstractDao;
import Dao.VideoDao;
import Entity.Video;

import java.util.List;

public class VideoDaoImpl extends AbstractDao<Video> implements VideoDao {
    @Override
    public Video findById(Integer id) {
        return super.findById(Video.class,id);
    }

    @Override
    public Video findByHref(String href) {
        String sql = "SELECT o FROM Video o where href = ?0";
        return super.findOne(Video.class,sql,href);
    }

    @Override
    public List<Video> findAll() {
        return super.findAll(Video.class,true);
    }

    @Override
    public List<Video> findAll(int pageNumber, int pageSize) {
        return super.findAll(Video.class,true,pageNumber,pageSize);
    }
}
