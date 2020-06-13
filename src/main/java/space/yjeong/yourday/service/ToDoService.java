package space.yjeong.yourday.service;

import space.yjeong.yourday.domain.todo.Status;
import space.yjeong.yourday.domain.todo.ToDo;

import java.time.LocalDate;
import java.util.List;

public interface ToDoService {
    void writeToDo(ToDo todo);
    void updateToDo(ToDo updateToDo);
    void delete(Long id);
    ToDo getById(Long id);
    List<ToDo> getAllByUserId(Long userId);
    List<ToDo> getAllByUserIdAndDate(Long userId, LocalDate date);
    void changeStatus(Long id, Status status);
}
