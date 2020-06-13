package space.yjeong.yourday.dao;

import org.apache.ibatis.annotations.Param;
import space.yjeong.yourday.domain.todo.Status;
import space.yjeong.yourday.domain.todo.ToDo;

import java.time.LocalDate;
import java.util.List;

public interface ToDoDao {
    void save(ToDo todo);
    void update(ToDo todo);
    void updateStatus(@Param("id") Long id, @Param("status") Status status);
    void delete(Long id);
    ToDo findById(Long id);
    List<ToDo> findAllByUserId(Long userId);
    List<ToDo> findAllByUserIdAndDate(@Param("userId") Long userId, @Param("date") LocalDate date);
}
