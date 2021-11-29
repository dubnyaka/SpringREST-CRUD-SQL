package com.example.springmvc.Dao;

import com.example.springmvc.model.StudentGroup;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class StudentGroupsDaoImpl implements StudentGroupsDao {
    // Хранилище групп студентов
    private final Map<Long, StudentGroup> STUDENT_GROUP_REPOSITORY_MAP = new ConcurrentHashMap<>();

    // Переменная для генерации ID группы
    private final AtomicLong STUDENT_GROUP_ID_HOLDER = new AtomicLong();

    @Override
    public void save(StudentGroup group) {
        final long groupId = STUDENT_GROUP_ID_HOLDER.incrementAndGet();
        group.setId(groupId);
        STUDENT_GROUP_REPOSITORY_MAP.put(groupId, group);
    }

    @Override
    public List<StudentGroup> getAll() {
        return new ArrayList<>(STUDENT_GROUP_REPOSITORY_MAP.values());
    }

    @Override
    public StudentGroup get(long id) {
        return STUDENT_GROUP_REPOSITORY_MAP.get(id);
    }

    @Override
    public boolean update(StudentGroup group) {
        if (STUDENT_GROUP_REPOSITORY_MAP.containsKey(group.getId())) {
            STUDENT_GROUP_REPOSITORY_MAP.put(group.getId(), group);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(long id) {
        return STUDENT_GROUP_REPOSITORY_MAP.remove(id) != null;
    }
}
