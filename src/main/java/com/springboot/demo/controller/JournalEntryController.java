package com.springboot.demo.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.demo.model.CreateJournalEntryRequest;
import com.springboot.demo.model.GetMonthEntryResponse;
import com.springboot.demo.model.JournalEntryResponse;
import com.springboot.demo.service.JournalEntryService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "My Journal App", description = "This is a controller for query operations on Journal Application")
public class JournalEntryController {
	private Logger log = LoggerFactory.getLogger(JournalEntryController.class);
	// @Autowired
	// JournalEntryRepository repo;

	@Autowired
	JournalEntryService journalEntryService;

	@ResponseBody
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "500", description = "Bad Request"),
			@ApiResponse(responseCode = "404", description = "Not found") })
	@RequestMapping(value = "/addEntry", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JournalEntryResponse> addJournalEntry(
			@RequestBody final CreateJournalEntryRequest createJournalEntryRequest) throws IOException {
		JournalEntryResponse journalEntryResponse = new JournalEntryResponse();

		journalEntryResponse = journalEntryService.addJournalEntry(createJournalEntryRequest);
		if (journalEntryResponse.getErrorMessage() != null) {
			return new ResponseEntity<>(journalEntryResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(journalEntryResponse, HttpStatus.OK);

	}

	// Build Get All entriies REST API git saved 24/01 2:30
	@ResponseBody
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "500", description = "Bad Request"),
			@ApiResponse(responseCode = "404", description = "Not found") })
	@RequestMapping(value = "/getAllCurrentEntriesWithName/{taskName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GetMonthEntryResponse> getAllCurrentEntriesWithName(
			@PathVariable final String taskName) throws IOException {
		GetMonthEntryResponse getMonthEntryResponse = new GetMonthEntryResponse();

		getMonthEntryResponse = journalEntryService.getAllCurrentEntriesWithName(taskName);
		if (getMonthEntryResponse.getErrorMessage() != null) {
			return new ResponseEntity<>(getMonthEntryResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(getMonthEntryResponse, HttpStatus.OK);

	}

	@ResponseBody
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "500", description = "Bad Request"),
			@ApiResponse(responseCode = "404", description = "Not found") })
	@RequestMapping(value = "/getAllCurrentEntries", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GetMonthEntryResponse> getAllMonthEntries()
			throws IOException {
		GetMonthEntryResponse getMonthEntryResponse = new GetMonthEntryResponse();

		getMonthEntryResponse = journalEntryService.getAllMonthEntries(null);
		if (getMonthEntryResponse.getErrorMessage() != null) {
			return new ResponseEntity<>(getMonthEntryResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(getMonthEntryResponse, HttpStatus.OK);

	}

	@ResponseBody
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "500", description = "Bad Request"),
			@ApiResponse(responseCode = "404", description = "Not found") })
	@RequestMapping(value = "/getAllMonthEntries/{monthName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GetMonthEntryResponse> getAllMonthEntries(
			@PathVariable final String monthName) throws IOException {
		GetMonthEntryResponse getMonthEntryResponse = new GetMonthEntryResponse();

		getMonthEntryResponse = journalEntryService.getAllMonthEntries(monthName);
		if (getMonthEntryResponse.getErrorMessage() != null) {
			return new ResponseEntity<>(getMonthEntryResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(getMonthEntryResponse, HttpStatus.OK);

	}
}