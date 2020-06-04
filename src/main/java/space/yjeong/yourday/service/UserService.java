package space.yjeong.yourday.service;


import space.yjeong.yourday.model.user.User;
import space.yjeong.yourday.util.FileHelper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class UserService {
    private FileHelper fileHelper = new FileHelper();

    public boolean updateUser(String email, String password, LocalDateTime birthday, String phone){
        User user = loadUser(email);
        if(user == null){
            return false;
        }
        user.update(password,birthday,phone);
        signUp(user);

        return true;
    }

    public boolean signUp(User user){
        String currentPath = "\\" + user.getEmail();
        fileHelper.createFolder(currentPath);
        File file = fileHelper.createFile(currentPath+"\\info.txt");
        FileWriter fw = null;
        try {
            fw = new FileWriter(file, false);
            fw.write(user.toFileData());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean signIn(String email, String password){
        User user = loadUser(email);
        if(user == null){
            return false;
        }
        if(!(password.equals(user.getPassword()))){
            System.out.println("비밀번호가 일치하지 않습니다.");
            return false;
        }
        return true;
    }

    private User loadUser(String email){
        List<String> data = fileHelper.loadFile("\\" + email + "\\info.txt");
        if(data == null){
            System.out.println("존재하지 않는 유저입니다.");
            return null;
        }

        return new User(
                null,
                data.get(0),
                data.get(1),
                data.get(2),
                LocalDateTime.of(LocalDate.parse(data.get(3)), LocalTime.now())                ,
                data.get(4)
        );
    }

}