package Entity;

import Constant.NamedStored;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = NamedStored.FIND_USER_LIKE_VIDEO_BY_VIDEO_HREF,
                procedureName = "sp_selectusersLikedvideoByVideoHref",
                resultClasses = {User.class},
                parameters = @StoredProcedureParameter(name = "videoHref",type = String.class)
        )
})
@Entity
@Table(name = "`user`")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private int id;


    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "isAdmin")
    private boolean isAdmin;

    @Column(name = "isActive")
    private boolean isActive;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }


    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }


}
