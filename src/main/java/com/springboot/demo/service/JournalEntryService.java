package com.springboot.demo.service;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.springboot.demo.model.CreateJournalEntryRequest;
import com.springboot.demo.model.GetMonthEntryResponse;
import com.springboot.demo.model.JournalEntryResponse;
@Component
public interface JournalEntryService {

    JournalEntryResponse addJournalEntry(final CreateJournalEntryRequest createJournalEntryRequest) throws IOException;

    GetMonthEntryResponse getAllCurrentEntriesWithTaskName(final String taskName);

    GetMonthEntryResponse getAllMonthEntries(final String monthName);


}