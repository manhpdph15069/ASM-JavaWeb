package Servlet;

import Dao.VideoDao;
import Dao.impl.VideoDaoImpl;
import Entity.Video;

import java.util.List;

public interface VideoService {



        public Video findById(Integer id);

        public Video findByHref(String href);

        public List<Video> findAll(int pageNumber, int pageSize);
        public List<Video> findAll();

        public Video create(Video entity);

        public Video update(Video entity) ;


        public Video delete(String href) ;



}
