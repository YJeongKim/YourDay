package space.yjeong.yourday.service.impl;

import space.yjeong.yourday.dao.UserDao;
import space.yjeong.yourday.domain.user.User;
import space.yjeong.yourday.exception.EmailDuplicateException;
import space.yjeong.yourday.exception.PasswordNotMatchingException;
import space.yjeong.yourday.exception.UserNotFoundException;
import space.yjeong.yourday.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void signUp(User user) {
        try {
            if(getByEmail(user.getEmail()) != null)
                throw new EmailDuplicateException();
        } catch (UserNotFoundException e) {
            userDao.save(user);
        }
    }

    @Override
    public User signIn(String email, String password) {
        User user = getByEmail(email);
        if(!user.getPassword().equals(password)) throw new PasswordNotMatchingException();
        return user;
    }

    @Override
    public void updateUserInfo(User newInfoUser) {
        getById(newInfoUser.getId());
        userDao.update(newInfoUser);
    }

    @Override
    public void delete(Long id) {
        if(getById(id)!=null)
            userDao.delete(id);
    }

    @Override
    public User getById(Long id) {
        User user = userDao.findById(id);
        if(user == null) throw new UserNotFoundException();
        return user;
    }

    @Override
    public User getByEmail(String email) {
        User user = userDao.findByEmail(email);
        if(user == null) throw new UserNotFoundException();
        return user;
    }

    @Override
    public List<User> getAll() {
        return userDao.findAll();
    }
}