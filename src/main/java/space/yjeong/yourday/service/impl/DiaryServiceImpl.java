package space.yjeong.yourday.service.impl;

import space.yjeong.yourday.dao.DiaryDao;
import space.yjeong.yourday.domain.diary.Diary;
import space.yjeong.yourday.exception.DiaryNotFoundException;
import space.yjeong.yourday.exception.DiaryNotWrittenException;
import space.yjeong.yourday.exception.ExistDiaryThatDateException;
import space.yjeong.yourday.service.DiaryService;

import java.time.LocalDate;
import java.util.List;

public class DiaryServiceImpl implements DiaryService {
    private DiaryDao diaryDao;

    public void setDiaryDao(DiaryDao diaryDao) {
        this.diaryDao = diaryDao;
    }

    @Override
    public void writeDiary(Diary diary) {
        checkByUserIdAndDate(diary.getUserId(), diary.getDate());
        diaryDao.save(diary);
    }

    @Override
    public void updateDiary(Diary updateDiary) {
        Diary diary = getById(updateDiary.getId());
        if (!diary.getDate().equals(updateDiary.getDate()))
            checkByUserIdAndDate(updateDiary.getUserId(), updateDiary.getDate());
        diaryDao.update(updateDiary);
    }

    @Override
    public void delete(Long id) {
        if (getById(id) != null)
            diaryDao.delete(id);
    }

    @Override
    public Diary getById(Long id) {
        Diary diary = diaryDao.findById(id);
        if (diary == null) throw new DiaryNotFoundException();
        return diary;
    }

    @Override
    public List<Diary> getAllByUserId(Long userId) {
        List<Diary> diaries = diaryDao.findAllByUserId(userId);
        if(diaries.isEmpty()) throw new DiaryNotWrittenException();
        return diaries;
    }

    @Override
    public void checkByUserIdAndDate(Long userId, LocalDate date) {
        Diary diary = diaryDao.findByUserIdAndDate(userId, date);
        if (diary != null) throw new ExistDiaryThatDateException();
    }
}