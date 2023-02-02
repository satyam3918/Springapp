package com.springboot.demo.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springboot.demo.controller.JournalEntryController;
import com.springboot.demo.dao.JournalEntryRepo;
import com.springboot.demo.model.CreateJournalEntryRequest;
import com.springboot.demo.model.GetMonthEntryResponse;
import com.springboot.demo.model.JournalEntryResponse;
import com.springboot.demo.model.Tasks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class JournalEntryServiceImpl implements JournalEntryService{
    private Logger log = LoggerFactory.getLogger(JournalEntryController.class);

    @Autowired
    JournalEntryRepo repo;

    @Override
    public JournalEntryResponse addJournalEntry(final CreateJournalEntryRequest createJournalEntryRequest) {
        log.info("creating the tasks");
        Tasks entry = new Tasks();
        LocalDateTime now = LocalDateTime.now();
        Tasks response = new Tasks();
        JournalEntryResponse journalEntryResponse = new JournalEntryResponse();
        entry.setCreatedBy(createJournalEntryRequest.getCreatedBy());
        entry.setTaskDate(createJournalEntryRequest.getTaskDate());
        entry.setTaskDescription(createJournalEntryRequest.getTaskDescription());
        entry.setTaskName(createJournalEntryRequest.getTaskName());
        entry.setUpdatedBy(createJournalEntryRequest.getCreatedBy());
        entry.setUpdatedTs(now);
        entry.setCreatedTs(now);
        try {
            response = repo.save(entry);
            journalEntryResponse.setSuccess(true);
            log.info("Adding/creating the tasks successfully");
        } catch (final Exception e) {
            journalEntryResponse.setSuccess(false);
            journalEntryResponse.setErrorMessage("Error occured in "+e.getMessage());
            log.info("Error occured while creating the task");
            return journalEntryResponse;
        }
        journalEntryResponse.setData(response);
        log.info("task creation is completed");
        return journalEntryResponse;
    }

    @Override
    public GetMonthEntryResponse getAllCurrentEntriesWithTaskName(final String taskName) {
        log.info("Fetching all the tasks with the name");
        GetMonthEntryResponse getMonthEntryResponse = new GetMonthEntryResponse();
        try {
            getMonthEntryResponse.getMonthData().addAll(repo.getAllCurrentEntriesWithTaskName(taskName));
            getMonthEntryResponse.setSuccess(true);
            log.info(" successfully Fetch all the tasks with the name");
        } catch (final Exception e) {
            getMonthEntryResponse.setSuccess(false);
            getMonthEntryResponse.setErrorMessage("Error occured in "+e.getMessage());
            log.info(" Error occurred while Fetching all the tasks with the name");
            return getMonthEntryResponse;
        }

        return getMonthEntryResponse;
    }

    @Override
    public GetMonthEntryResponse getAllMonthEntries(final String monthName) {
        log.info("Fetching the tasks with the month name");
        GetMonthEntryResponse getMonthEntryResponse = new GetMonthEntryResponse();
        try {
            int month,year;
            if(monthName==null){
                LocalDate now = LocalDate.now();
                month=now.getMonthValue();
                year=now.getYear();
                log.info("Getting the current month data");
            }
            else{
                // String monthStr = monthName.split("-")[0];
                // String yearStr = monthName.split("-")[1];
                // month=1;
                // year=2023;
                final String monthStr = monthName.substring(0, 3);
                year = Integer.parseInt(monthName.substring(3));
                final Calendar cal = Calendar.getInstance();
                cal.setTime(new SimpleDateFormat("MMM").parse(monthStr));
                month = cal.get(Calendar.MONTH) + 1;
                log.info("Getting the data for :"+monthName);

            }
            log.info("month: "+month+" year: "+year);
            getMonthEntryResponse.getMonthData().addAll(repo.getAllMonthEntries(month,year));
            getMonthEntryResponse.setSuccess(true);
            log.info(" successfully fetched the tasks with the month name");

        } catch (final Exception e) {
            getMonthEntryResponse.setSuccess(false);
            getMonthEntryResponse.setErrorMessage("Error occured in "+e.getMessage());
            log.info("Error occurred while fetching the tasks with the month name");
            return getMonthEntryResponse;
        }

        return getMonthEntryResponse;
    }

}