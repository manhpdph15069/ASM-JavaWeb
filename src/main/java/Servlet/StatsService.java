package Servlet;

import Dao.dto.VideoLikedInfo;

import java.util.List;

public interface StatsService {
    List<VideoLikedInfo> findVideoLikedInfo();
}
