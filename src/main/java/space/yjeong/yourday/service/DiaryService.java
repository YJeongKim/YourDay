package space.yjeong.yourday.service;

import space.yjeong.yourday.domain.diary.Diary;
import space.yjeong.yourday.domain.diary.DiaryDto;

import java.time.LocalDate;
import java.util.List;

public interface DiaryService {
    void writeDiary(Diary diary);
    void updateDiary(Diary updateDiary);
    void delete(Long id);
    Diary getById(Long id);
    List<Diary> getAllByUserId(Long userId);
    void checkByUserIdAndDate(Long userId, LocalDate date);
}