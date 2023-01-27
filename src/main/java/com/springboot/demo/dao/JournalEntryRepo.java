package com.springboot.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.demo.model.JournalEntry;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface JournalEntryRepo extends JpaRepository<JournalEntry, Integer>{

    @Query("select t from tasks t where MONTH(task_date) = MONTH(curdate()) and YEAR(task_date) = YEAR(curdate())")
    public List<JournalEntry> getCurrentMonthEntries();

}
