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
@RequestMapping("/api/v1")
@Tag(name = "My Journal App", description = "This is a controller for query operations on Journal Application")
public class JournalEntryController {
	private Logger log = LoggerFactory.getLogger(JournalEntryController.class);

	@Autowired
	JournalEntryService journalEntryService;

	@ResponseBody
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "500", description = "Bad Request"),
			@ApiResponse(responseCode = "404", description = "Not found") })
	@RequestMapping(value = "/addEntry", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JournalEntryResponse> addJournalEntry(
			@RequestBody final CreateJournalEntryRequest createJournalEntryRequest) throws IOException {
		log.info("Starting of Adding Journal Entry ");

		JournalEntryResponse journalEntryResponse = new JournalEntryResponse();

		journalEntryResponse = journalEntryService.addJournalEntry(createJournalEntryRequest);
		if (journalEntryResponse.getErrorMessage() != null) {
			log.info("Error while Adding Journal Entry");
			return new ResponseEntity<>(journalEntryResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		log.info("End of adding Journal Entry");
		return new ResponseEntity<>(journalEntryResponse, HttpStatus.OK);

	}

	@ResponseBody
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "500", description = "Bad Request"),
			@ApiResponse(responseCode = "404", description = "Not found") })
	@RequestMapping(value = "/getAllCurrentEntriesWithTaskName/{taskName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GetMonthEntryResponse> getAllCurrentEntriesWithTaskName(
			@PathVariable final String taskName) throws IOException {
		log.info("Starting of All Current month task entries with name");
		GetMonthEntryResponse getMonthEntryResponse = new GetMonthEntryResponse();

		getMonthEntryResponse = journalEntryService.getAllCurrentEntriesWithTaskName(taskName);
		if (getMonthEntryResponse.getErrorMessage() != null) {
			log.info("Error While getting all month task entries with name");
			return new ResponseEntity<>(getMonthEntryResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		log.info("End of All Current month task entries with name");
		return new ResponseEntity<>(getMonthEntryResponse, HttpStatus.OK);

	}

	@ResponseBody
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "500", description = "Bad Request"),
			@ApiResponse(responseCode = "404", description = "Not found") })
	@RequestMapping(value = "/getAllCurrentEntries", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GetMonthEntryResponse> getAllMonthEntries()
			throws IOException {
		log.info("Starting of All Current task Entries");
		GetMonthEntryResponse getMonthEntryResponse = new GetMonthEntryResponse();

		getMonthEntryResponse = journalEntryService.getAllMonthEntries(null);
		if (getMonthEntryResponse.getErrorMessage() != null) {
			log.info("Error While getting all task entries");
			return new ResponseEntity<>(getMonthEntryResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		log.info("End Of All Current task Entries");
		return new ResponseEntity<>(getMonthEntryResponse, HttpStatus.OK);

	}

	@ResponseBody
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "500", description = "Bad Request"),
			@ApiResponse(responseCode = "404", description = "Not found") })
	@RequestMapping(value = "/getAllMonthEntries/{monthName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GetMonthEntryResponse> getAllMonthEntriesWithMonthName(
			@PathVariable final String monthName) throws IOException {
		log.info("Starting of All month task Entries with Name");
		GetMonthEntryResponse getMonthEntryResponse = new GetMonthEntryResponse();

		getMonthEntryResponse = journalEntryService.getAllMonthEntries(monthName);
		if (getMonthEntryResponse.getErrorMessage() != null) {
			log.info("Error while getting all month task entries with name");
			return new ResponseEntity<>(getMonthEntryResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		log.info("End of All month task Entries With Name");
		return new ResponseEntity<>(getMonthEntryResponse, HttpStatus.OK);

	}
}