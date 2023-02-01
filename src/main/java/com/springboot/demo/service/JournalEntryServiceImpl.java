package com.springboot.demo.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springboot.demo.dao.JournalEntryRepo;
import com.springboot.demo.model.CreateJournalEntryRequest;
import com.springboot.demo.model.GetMonthEntryResponse;
import com.springboot.demo.model.JournalEntryResponse;
import com.springboot.demo.model.Tasks;

@Component
public class JournalEntryServiceImpl implements JournalEntryService{

    @Autowired
    JournalEntryRepo repo;

    @Override
    public JournalEntryResponse addJournalEntry(final CreateJournalEntryRequest createJournalEntryRequest) {
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
        } catch (final Exception e) {
            journalEntryResponse.setSuccess(false);
            journalEntryResponse.setErrorMessage("Error occured in "+e.getMessage());
            return journalEntryResponse;
        }
        journalEntryResponse.setData(response);
        return journalEntryResponse;
    }

    @Override
    public GetMonthEntryResponse getAllCurrentEntriesWithTaskName(final String taskName) {
        GetMonthEntryResponse getMonthEntryResponse = new GetMonthEntryResponse();
        try {
            getMonthEntryResponse.getMonthData().addAll(repo.getAllCurrentEntriesWithTaskName(taskName));
            getMonthEntryResponse.setSuccess(true);
        } catch (final Exception e) {
            getMonthEntryResponse.setSuccess(false);
            getMonthEntryResponse.setErrorMessage("Error occured in "+e.getMessage());
            return getMonthEntryResponse;
        }

        return getMonthEntryResponse;
    }

    @Override
    public GetMonthEntryResponse getAllMonthEntries(final String monthName) {
        GetMonthEntryResponse getMonthEntryResponse = new GetMonthEntryResponse();
        try {
            int month,year;
            if(monthName==null){
                LocalDate now = LocalDate.now();
                month=now.getMonthValue();
                year=now.getYear();
            }
            else{
                final String monthStr = monthName.substring(0, 3);
                year = Integer.parseInt(monthName.substring(3));
                final Calendar cal = Calendar.getInstance();
                cal.setTime(new SimpleDateFormat("MMM").parse(monthStr));
                month = cal.get(Calendar.MONTH) + 1;

            }

            getMonthEntryResponse.getMonthData().addAll(repo.getAllMonthEntries(month,year));
            getMonthEntryResponse.setSuccess(true);

        } catch (final Exception e) {
            getMonthEntryResponse.setSuccess(false);
            getMonthEntryResponse.setErrorMessage("Error occured in "+e.getMessage());
            return getMonthEntryResponse;
        }

        return getMonthEntryResponse;
    }

}