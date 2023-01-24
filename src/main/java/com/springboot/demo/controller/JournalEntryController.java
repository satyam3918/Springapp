package com.springboot.demo.controller;

import java.time.LocalDateTime;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.demo.dao.JournalEntryRepository;
import com.springboot.demo.model.JournalEntry;

@Controller
public class JournalEntryController {
	
	@Autowired
	JournalEntryRepository repo;
	
	@RequestMapping("/home")
	public String home() {
		return "home.jsp";
	}
	

	
	//Create
//	@RequestMapping("/addEntry")
	@RequestMapping(value = "/addEntry", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JournalEntry> addJournalEntry(JournalEntry entry) {
		
		entry.setUpdatedBy(entry.getCreatedBy());
		 LocalDateTime now = LocalDateTime.now();
		 entry.setUpdatedTs(now);
		 entry.setCreatedTs(now);
		 JournalEntry response = repo.save(entry);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	//Retrieve All Data
	@RequestMapping("/getAllEntries")
	public ModelAndView getAllJournalEntries() {
		ModelAndView mv= new ModelAndView();
		List<JournalEntry> entries= repo.findAll();
		mv.addObject("entries",entries);
		mv.setViewName("getAllEntries.jsp");
		
		return mv;
	}
	
	//Retrieve Specific Data
	@RequestMapping("/getEntry")
	public ModelAndView getJournalEntry(@RequestParam int taskId) {
		ModelAndView mv = new ModelAndView();
		JournalEntry entry = repo.findById(taskId).orElse(new JournalEntry());
		mv.addObject("entry",entry);
		mv.setViewName("getEntry.jsp");
		
		return mv;
	}
	
	//Delete Data
	@RequestMapping("/deleteEntry")
	public String deleteJournalEntry(@RequestParam int taskId) {
		
		repo.deleteById(taskId);
		return "home.jsp";
	}

	


	

	
	
}
