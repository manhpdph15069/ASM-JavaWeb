package Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "video")
public class Video {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "href")
    private String href;

    @Column(name = "poster")
    private String poster;

    @Column(name = "`views`")
    private int views;

    @Column(name = "share")
    private int share;

    @Column(name = "description")
    private String description;

    @Column(name = "isActive")
    private Boolean isActive;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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


    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }


    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }


    public int getShare() {
        return share;
    }

    public void setShare(int share) {
        this.share = share;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }


}
