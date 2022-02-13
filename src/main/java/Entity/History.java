package Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "history")
public class History {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.MERGE)//nhiều đến 1
    @JoinColumn(name = "userID",referencedColumnName = "id")
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private User user;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "videoID",referencedColumnName = "id")
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Video video;

    @Column(name = "viewedDate")
    @CreationTimestamp //auto lấy thời gian hiện tại để them vào database
    private Timestamp viewedDate;

    @Column(name = "isLiked")
    private boolean isLiked;

    @Column(name = "likedDate")
    private Timestamp likedDate;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Timestamp getViewedDate() {
        return viewedDate;
    }

    public void setViewedDate(Timestamp viewedDate) {
        this.viewedDate = viewedDate;
    }


    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }


    public Timestamp getLikedDate() {
        return likedDate;
    }

    public void setLikedDate(Timestamp likedDate) {
        this.likedDate = likedDate;
    }

}
