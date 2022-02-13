package Dao;

import Dao.dto.VideoLikedInfo;
import Entity.User;

import java.util.List;

public interface StatsDao {
    List<VideoLikedInfo> findVideoLikedInfo();
}
