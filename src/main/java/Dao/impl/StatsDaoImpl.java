package Dao.impl;

import Dao.AbstractDao;
import Dao.StatsDao;
import Dao.dto.VideoLikedInfo;

import java.util.ArrayList;
import java.util.List;

public class StatsDaoImpl extends AbstractDao<Object[]> implements StatsDao {

    @Override
    public List<VideoLikedInfo> findVideoLikedInfo() {
        String sql ="select v.id,v.title,v.href,SUM((h.isLiked)) as totalLike\n" +
                "                 from Video v left join History h on v.id = h.videoID\n" +
                "                 where v.isActive =1\n" +
                "                 group by v.id, v.title, v.href";
        List<Object[]> objects = super.findManyByNativeQuery(sql);
        List<VideoLikedInfo> result = new ArrayList<>();
        objects.forEach(object -> {
            VideoLikedInfo videoLikedInfo = new VideoLikedInfo();
            videoLikedInfo.setVideoid((Integer) object[0]);
            videoLikedInfo.setTitle((String) object[1]);
            videoLikedInfo.setHref((String) object[2]);
            videoLikedInfo.setTotalLike(String.valueOf(object[3])==null?0: Integer.valueOf(String.valueOf(object[3])));
            result.add(videoLikedInfo);
        });
        return result;
    }

    private VideoLikedInfo setDataVideoLikeInfo(Object[] object){
        VideoLikedInfo videoLikedInfo = new VideoLikedInfo();
        videoLikedInfo.setVideoid((Integer) object[0]);
        videoLikedInfo.setTitle((String) object[1]);
        videoLikedInfo.setHref((String) object[2]);
        videoLikedInfo.setTotalLike((Integer) object[3]);
        return videoLikedInfo;
    }
}
