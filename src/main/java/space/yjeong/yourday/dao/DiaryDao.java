package space.yjeong.yourday.dao;

import org.apache.ibatis.annotations.Param;
import space.yjeong.yourday.domain.diary.Diary;

import java.time.LocalDate;
import java.util.List;

public interface DiaryDao {
    void save(Diary diary);
    void update(Diary diary);
    void delete(Long id);
    Diary findById(Long id);
    List<Diary> findAllByUserId(Long userId);
    Diary findByUserIdAndDate(@Param("userId") Long userId, @Param("date") LocalDate date);
}
