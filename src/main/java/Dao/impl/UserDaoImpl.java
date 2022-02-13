package Dao.impl;

import Constant.NamedStored;
import Dao.AbstractDao;
import Dao.UserDao;
import Entity.User;

import java.util.List;
import java.util.Map;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {
    @Override
    public User findById(Integer id) {
        return super.findById(User.class,id);
    }

    @Override
    public User findByEmail(String email) {
        String sql = "SELECT o FROM User o where o.email = ?0";
        return super.findOne(User.class,sql,email);
    }

    @Override
    public User findByUsername(String username) {
        String sql = "SELECT o FROM User o where username = ?0";
        return super.findOne(User.class,sql,username);
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        String sql = "SELECT o FROM User o where o.username= ?0 AND o.password= ?1 AND isActive = 1";
        return super.findOne(User.class,sql,username,password);
    }

    @Override
    public List<User> findAll() {
        return super.findAll(User.class,true);
    }

    @Override
    public List<User> findAll(int pageNumber, int pageSize) {
        return super.findAll(User.class,true,pageNumber,pageSize);
    }

    @Override
    public List<User> findusersLikedVideoByVideoHref(Map<String, Object> params) {
        return super.callStored(NamedStored.FIND_USER_LIKE_VIDEO_BY_VIDEO_HREF,params);
    }
}
