package com.example.springmvc.Dao;

import com.example.springmvc.model.StudentGroup;

import java.util.List;

public interface StudentGroupsDao {

    /**
     * Создает новую группу студентов
     * @param group - группа студентов для создания
     */
    void save(StudentGroup group);

    /**
     * Возвращает список всех имеющихся групп
     * @return список групп
     */
    List<StudentGroup> getAll();

    /**
     * Возвращает группу по её ID
     * @param id - ID группы
     * @return - объект группы с заданным ID
     */
    StudentGroup get(long id);

    /**
     * Обновляет группу с заданным ID,
     * в соответствии с переданной группой
     * @param group - группа студентов в соответсвии с которой нужно обновить данные
     * @return - true если данные были обновлены, иначе false
     */
    boolean update(StudentGroup group);

    /**
     * Удаляет группу студентов с заданным ID
     * @param id - id группы студентов, которую нужно удалить
     * @return - true если группа была удалена, иначе false
     */
    boolean delete(long id);
}