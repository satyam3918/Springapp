package com.springboot.demo.controller;

import com.springboot.demo.model.CreateJournalEntryRequest;
import com.springboot.demo.model.GetCurrentMonthEntryResponse;
import com.springboot.demo.model.JournalEntryResponse;
import com.springboot.demo.service.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
public class JournalEntryController {

//	@Autowired
//	JournalEntryRepository repo;

	@Autowired
	JournalEntryService journalEntryService;

	@RequestMapping("/home")
	public String home() {
		return "home.jsp";
	}


	@ResponseBody
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
	@GetMapping(value = "/getAllEntries")
	public ResponseEntity<GetCurrentMonthEntryResponse> getCurrentMonthJournalEntry(
			@RequestBody final CreateJournalEntryRequest createJournalEntryRequest) throws IOException {
		GetCurrentMonthEntryResponse getCurrentMonthEntryResponse = new GetCurrentMonthEntryResponse();


		getCurrentMonthEntryResponse = journalEntryService.getAllEntries();
		if (getCurrentMonthEntryResponse.getErrorMessage() != null) {
			return new ResponseEntity<>(getCurrentMonthEntryResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(getCurrentMonthEntryResponse, HttpStatus.OK);

	}

//	@GetMapping("{id}")
//	public ResponseEntity<> getUserById(@PathVariable("id") Long userId){
//		User user = userService.getUserById(userId);
//		return new ResponseEntity<>(user, HttpStatus.OK);
//	}


}

//	@GetMapping("{TaskId}")
//     public ResponseEntity<AddTasks> getTaskById(@PathVariable("TaskId") Long TaskId){
//        AddTasks addTasks = addTasksService.getTaskById(TaskId);
//        return new ResponseEntity<>(addTasks, HttpStatus.OK);

	
//	//Retrieve All Data
//	@RequestMapping("/getAllEntries")
//	public ModelAndView getAllJournalEntries() {
//		ModelAndView mv= new ModelAndView();
//		List<JournalEntry> entries= repo.findAll();
//		mv.addObject("entries",entries);
//		mv.setViewName("getAllEntries.jsp");
//
//		return mv;
//	}
//
//	//Retrieve Specific Data
//	@RequestMapping("/getEntry")
//	public ModelAndView getJournalEntry(@RequestParam int taskId) {
//		ModelAndView mv = new ModelAndView();
//		JournalEntry entry = repo.findById(taskId).orElse(new JournalEntry());
//		mv.addObject("entry",entry);
//		mv.setViewName("getEntry.jsp");
//
//		return mv;
//	}
//
//	//Delete Data
//	@RequestMapping("/deleteEntry")
//	public String deleteJournalEntry(@RequestParam int taskId) {
//
//		repo.deleteById(taskId);
//		return "home.jsp";
//	}

	


	

	
	

