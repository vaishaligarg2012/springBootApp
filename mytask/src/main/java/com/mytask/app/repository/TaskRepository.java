package com.mytask.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytask.app.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer> {

}
