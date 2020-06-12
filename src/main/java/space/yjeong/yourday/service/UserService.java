package space.yjeong.yourday.service;

import space.yjeong.yourday.domain.user.User;

import java.util.List;

public interface UserService {
    void signUp(User user);
    User signIn(String email, String password);
    void updateUserInfo(User newInfoUser);
    void delete(Long id);
    User getById(Long id);
    User getByEmail(String email);
    List<User> getAll();
}