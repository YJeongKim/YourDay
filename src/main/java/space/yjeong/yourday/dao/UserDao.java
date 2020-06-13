package space.yjeong.yourday.dao;

import space.yjeong.yourday.domain.user.User;

import java.util.List;

public interface UserDao {
    void save(User user);
    void update(User user);
    void delete(Long id);
    User findById(Long id);
    User findByEmail(String email);
    List<User> findAll();
}