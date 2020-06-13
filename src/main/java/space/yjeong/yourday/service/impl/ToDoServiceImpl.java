package space.yjeong.yourday.service.impl;

import space.yjeong.yourday.dao.ToDoDao;
import space.yjeong.yourday.domain.todo.Status;
import space.yjeong.yourday.domain.todo.ToDo;
import space.yjeong.yourday.exception.ToDoNotFoundException;
import space.yjeong.yourday.exception.ToDoNotWrittenException;
import space.yjeong.yourday.service.ToDoService;

import java.time.LocalDate;
import java.util.List;

public class ToDoServiceImpl implements ToDoService {
    private ToDoDao todoDao;

    public void setTodoDao(ToDoDao todoDao) {
        this.todoDao = todoDao;
    }

    @Override
    public void writeToDo(ToDo todo) {
        todoDao.save(todo);
    }

    @Override
    public void updateToDo(ToDo updateToDo) {
        try {
            getById(updateToDo.getId());
            todoDao.update(updateToDo);
        } catch (ToDoNotFoundException e) {
            throw e;
        }
    }

    @Override
    public void delete(Long id) {
        if(getById(id) != null)
            todoDao.delete(id);
    }

    @Override
    public ToDo getById(Long id) {
        ToDo todo = todoDao.findById(id);
        if(todo == null) throw new ToDoNotFoundException();
        return todo;
    }

    @Override
    public List<ToDo> getAllByUserId(Long userId) {
        List<ToDo> todos = todoDao.findAllByUserId(userId);
        if(todos.isEmpty()) throw new ToDoNotWrittenException();
        return todos;
    }

    @Override
    public List<ToDo> getAllByUserIdAndDate(Long userId, LocalDate date) {
        List<ToDo> todos = todoDao.findAllByUserIdAndDate(userId, date);
        if(todos.isEmpty()) throw new ToDoNotWrittenException();
        return todos;
    }

    @Override
    public void changeStatus(Long id, Status status) {
        try {
            getById(id);
            todoDao.updateStatus(id, status);
        } catch (ToDoNotFoundException e) {
            throw e;
        }
    }
}
