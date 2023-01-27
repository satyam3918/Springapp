package com.springboot.demo.service;

import java.io.IOException;
import java.util.List;

import com.springboot.demo.model.GetCurrentMonthEntryResponse;
import com.springboot.demo.model.JournalEntry;
import org.springframework.stereotype.Component;

import com.springboot.demo.model.CreateJournalEntryRequest;
import com.springboot.demo.model.JournalEntryResponse;
@Component
public interface JournalEntryService {

    JournalEntryResponse addJournalEntry(final CreateJournalEntryRequest createJournalEntryRequest) throws IOException;

    GetCurrentMonthEntryResponse getAllEntries();
}
