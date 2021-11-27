package com.example.springmvc.Dao;

import com.example.springmvc.model.StudentsGroup;

import java.util.List;

public interface StudentGroupsDao {

    /**
     * Создает новую группу студентов
     * @param group - группа студентов для создания
     */
    void save(StudentsGroup group);

    /**
     * Возвращает список всех имеющихся групп
     * @return список групп
     */
    List<StudentsGroup> getAll();

    /**
     * Возвращает группу по её ID
     * @param id - ID группы
     * @return - объект группы с заданным ID
     */
    StudentsGroup get(long id);

    /**
     * Обновляет группу с заданным ID,
     * в соответствии с переданной группой
     * @param group - группа студентов в соответсвии с которой нужно обновить данные
     * @param id - id группы которую нужно обновить
     * @return - true если данные были обновлены, иначе false
     */
    boolean update(StudentsGroup group, long id);

    /**
     * Удаляет группу студентов с заданным ID
     * @param id - id группы студентов, которую нужно удалить
     * @return - true если группа была удалена, иначе false
     */
    boolean delete(long id);
}