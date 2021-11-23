package ru.mirea.student_work_checker.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mirea.student_work_checker.entities.Task;
import ru.mirea.student_work_checker.repositories.TaskRep;

@Service
public class TaskService {

    @Autowired
    private TaskRep taskRep;

    public boolean saveTask(Task task) {
        taskRep.saveAndFlush(task);
        return true;
    }

    public Task findTaskByName(String name) {
        return taskRep.findTaskByName(name);
    }
}
