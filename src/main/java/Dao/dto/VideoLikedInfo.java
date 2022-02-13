package Dao.dto;

public class VideoLikedInfo {
    private Integer Videoid;
    private String title;
    private String href;
    private Integer totalLike;

    public VideoLikedInfo(Integer videoid, String title, String href, Integer totalLike) {
        Videoid = videoid;
        this.title = title;
        this.href = href;
        this.totalLike = totalLike;
    }

    public VideoLikedInfo() {
    }

    public Integer getVideoid() {
        return Videoid;
    }

    public void setVideoid(Integer videoid) {
        Videoid = videoid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Integer getTotalLike() {
        return totalLike;
    }

    public void setTotalLike(Integer totalLike) {
        this.totalLike = totalLike;
    }
}
