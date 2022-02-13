package Servlet.Impl;

import Dao.StatsDao;
import Dao.dto.VideoLikedInfo;
import Dao.impl.StatsDaoImpl;
import Servlet.StatsService;

import java.util.List;

public class StatsServiceImpl implements StatsService {
   public StatsDao statsDao;

    public StatsServiceImpl() {
        statsDao = new StatsDaoImpl();
    }

    @Override
    public List<VideoLikedInfo> findVideoLikedInfo() {
        return statsDao.findVideoLikedInfo();
    }
}
