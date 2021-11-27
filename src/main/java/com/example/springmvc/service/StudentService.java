package com.example.springmvc.service;

import com.example.springmvc.model.Student;

import java.util.List;

public interface StudentService {

    /**
     * Создает нового студента
     * @param student - студент для создания
     */
    void save(Student student);

    /**
     * Возвращает список всех имеющихся студентов
     * @return список студентов
     */
    List<Student> getAll();

    /**
     * Возвращает студента по его ID
     * @param id - ID студента
     * @return - объект студента с заданным ID
     */
    Student get(long id);

    /**
     * Обновляет или добавляет студента с заданным ID,
     * в соответствии с переданным студентом
     * @param student - студент в соответсвии с которым нужно обновить данные
     * @param id - id студента которого нужно обновить
     * @return - true если студент был обновлён, иначе false
     */
    boolean update(Student student, long id);

    /**
     * Удаляет студента с заданным ID
     * @param id - id студента, которого нужно удалить
     * @return - true если студент был удален, иначе false
     */
    boolean delete(long id);
}