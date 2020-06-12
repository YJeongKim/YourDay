package space.yjeong.yourday.service;

import space.yjeong.yourday.domain.diary.Diary;
import space.yjeong.yourday.domain.diary.DiaryDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DiaryService {

    public boolean writeDiary(Diary diary) {
//        String currentPath = "\\" + diary.getUserEmail() + PATH + "\\" + diary.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//        fileHelper.createFolder(currentPath);
//        File file = fileHelper.createFile(currentPath + "\\data.txt");
//        FileWriter fw = null;
//        try {
//            fw = new FileWriter(file, false);
//            fw.write(diary.toFileData());
//            fw.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        }

        return true;
    }

    public DiaryDto readDiary(String email, LocalDateTime date) {
//        List<String> data = fileHelper.loadFile("\\" + email + PATH + "\\" + date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "\\data.txt");
//        if (data == null) return null;
//        else return new DiaryDto(data.get(1), data.get(2));
        return null;
    }

    public List<String> readDiaries(String email) {
        return null;
    }

    public int getDiaryCount(String email) {
//        List<String> diaries = fileHelper.loadDirectories("\\" + email + PATH);
//        if (diaries == null) return 0;
        return 0;
    }

    public boolean deleteDiary(String email, LocalDateTime date) {
        return false;
    }
}