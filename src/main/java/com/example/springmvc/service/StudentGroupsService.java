package com.example.springmvc.service;

import com.example.springmvc.model.StudentGroup;

import java.util.List;

public interface StudentGroupsService {

    /**
     * Создает новую группу студентов
     * @param group - группа студентов для создания
     */
    void saveStudentGroup(StudentGroup group);

    /**
     * Возвращает список всех имеющихся групп
     * @return список групп
     */
    List<StudentGroup> getAllStudentGroups();

    /**
     * Возвращает группу по её ID
     * @param id - ID группы
     * @return - объект группы с заданным ID
     */
    StudentGroup getStudentGroup(long id);

    /**
     * Обновляет группу с заданным ID,
     * в соответствии с переданной группой
     * @param group - группа студентов в соответсвии с которой нужно обновить данные
     * @return - true если данные были обновлены, иначе false
     */
    boolean updateStudentGroup(StudentGroup group);

    /**
     * Удаляет группу студентов с заданным ID
     * @param id - id группы студентов, которую нужно удалить
     * @return - true если группа была удалена, иначе false
     */
    boolean deleteStudentGroup(long id);
}