package com.example.springmvc.Dao;

import com.example.springmvc.model.StudentsGroup;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class StudentGroupsDaoImpl implements StudentGroupsDao {
    // Хранилище клиентов
    private static final Map<Long, StudentsGroup> STUDENT_GROUP_REPOSITORY_MAP = new ConcurrentHashMap<>();

    // Переменная для генерации ID группы
    private static final AtomicLong STUDENT_GROUP_ID_HOLDER = new AtomicLong();

    @Override
    public void save(StudentsGroup group) {
        final long groupId = STUDENT_GROUP_ID_HOLDER.incrementAndGet();
        group.setId(groupId);
        STUDENT_GROUP_REPOSITORY_MAP.put(groupId, group);
    }

    @Override
    public List<StudentsGroup> getAll() {
        return new ArrayList<>(STUDENT_GROUP_REPOSITORY_MAP.values());
    }

    @Override
    public StudentsGroup get(long id) {
        return STUDENT_GROUP_REPOSITORY_MAP.get(id);
    }

    @Override
    public boolean update(StudentsGroup group, long id) {
        if (STUDENT_GROUP_REPOSITORY_MAP.containsKey(id)) {
            group.setId(id);
            STUDENT_GROUP_REPOSITORY_MAP.put(id, group);
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(long id) {
        return STUDENT_GROUP_REPOSITORY_MAP.remove(id) != null;
    }
}
