package com.springboot.demo.service;

import java.time.LocalDateTime;

import com.springboot.demo.model.GetCurrentMonthEntryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springboot.demo.dao.JournalEntryRepo;
import com.springboot.demo.model.CreateJournalEntryRequest;
import com.springboot.demo.model.JournalEntry;
import com.springboot.demo.model.JournalEntryResponse;

@Component
public class JournalEntryServiceImpl implements JournalEntryService{

    @Autowired
    JournalEntryRepo repo;

    @Override
    public JournalEntryResponse addJournalEntry(final CreateJournalEntryRequest createJournalEntryRequest) {
        JournalEntry entry = new JournalEntry();
        LocalDateTime now = LocalDateTime.now();
        JournalEntry response = new JournalEntry();
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
    public GetCurrentMonthEntryResponse getAllEntries() {
        GetCurrentMonthEntryResponse getCurrentMonthEntryResponse = new GetCurrentMonthEntryResponse();
        try {
            getCurrentMonthEntryResponse.getCurrentmonthdata().addAll(repo.getCurrentMonthEntries());
            getCurrentMonthEntryResponse.setSuccess(true);
        } catch (final Exception e) {
            getCurrentMonthEntryResponse.setSuccess(false);
            getCurrentMonthEntryResponse.setErrorMessage("Error occured in "+e.getMessage());
            return getCurrentMonthEntryResponse;
        }

        return getCurrentMonthEntryResponse;
    }
    }


