package com.example.springmvc.service;

import com.example.springmvc.model.StudentsGroup;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class StudentGroupServiceImpl implements StudentGroupService {
    // Хранилище клиентов
    private static final Map<Integer, StudentsGroup> STUDENT_GROUP_REPOSITORY_MAP = new HashMap<>();

    // Переменная для генерации ID группы
    private static final AtomicInteger STUDENT_GROUP_ID_HOLDER = new AtomicInteger();

    @Override
    public void create(StudentsGroup group) {
        final int groupId = STUDENT_GROUP_ID_HOLDER.incrementAndGet();
        group.setId(groupId);
        STUDENT_GROUP_REPOSITORY_MAP.put(groupId, group);
    }

    @Override
    public List<StudentsGroup> readAll() {
        return new ArrayList<>(STUDENT_GROUP_REPOSITORY_MAP.values());
    }

    @Override
    public StudentsGroup read(int id) {
        return STUDENT_GROUP_REPOSITORY_MAP.get(id);
    }

    @Override
    public boolean update(StudentsGroup group, int id) {
        if (STUDENT_GROUP_REPOSITORY_MAP.containsKey(id)) {
            group.setId(id);
            STUDENT_GROUP_REPOSITORY_MAP.put(id, group);
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        return STUDENT_GROUP_REPOSITORY_MAP.remove(id) != null;
    }
}
