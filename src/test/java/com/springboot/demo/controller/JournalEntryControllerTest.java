package com.springboot.demo.controller;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.demo.model.CreateJournalEntryRequest;
import com.springboot.demo.model.GetMonthEntryResponse;
import com.springboot.demo.model.JournalEntryResponse;
import com.springboot.demo.model.Tasks;
import com.springboot.demo.service.JournalEntryService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
@ContextConfiguration(classes = {JournalEntryController.class})
@ExtendWith(SpringExtension.class)
public class JournalEntryControllerTest {
    @Autowired
    private JournalEntryController journalEntryController;
    @MockBean
    private JournalEntryService journalEntryService;
    @Test
    public void testAddJournalEntryWithSuccess() throws Exception {
        Tasks tasks = new Tasks();
        tasks.setCreatedTs(LocalDateTime.of(1, 1, 1, 1, 1));
        tasks.setUpdatedTs(LocalDateTime.of(1, 1, 1, 1, 1));
        tasks.setTaskDate(LocalDate.ofEpochDay(1L));
        tasks.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        tasks.setUpdatedBy("2020-03-01");
        tasks.setTaskName("Task Name");
        tasks.setTaskDescription("Task Description");
        tasks.setTaskId(123);
        JournalEntryResponse journalEntryResponse = new JournalEntryResponse();
        journalEntryResponse.setErrorMessage(null);
        journalEntryResponse.setSuccess(true);
        journalEntryResponse.setData(tasks);
        when(this.journalEntryService.addJournalEntry((CreateJournalEntryRequest) any())).thenReturn(journalEntryResponse);
        CreateJournalEntryRequest createJournalEntryRequest = new CreateJournalEntryRequest();
        createJournalEntryRequest.setTaskDate(null);
        createJournalEntryRequest.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        createJournalEntryRequest.setTaskName("Task Name");
        createJournalEntryRequest.setTaskDescription("Task Description");
        String content = (new ObjectMapper()).writeValueAsString(createJournalEntryRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/addEntry")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.journalEntryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"success\":true,\"errorMessage\":null,\"data\":{\"taskId\":123,\"taskName\":\"Task Name\",\"taskDescription\":\"Task"
                                        + " Description\",\"taskDate\":[1970,1,2],\"createdBy\":\"Jan 1, 2020 8:00am GMT+0100\",\"updatedBy\":\"2020-03-01"
                                        + "\",\"createdTs\":[1,1,1,1,1],\"updatedTs\":[1,1,1,1,1]}}"));
    }
    @Test
    public void testAddJournalEntrywithFailure() throws Exception {
        Tasks tasks = new Tasks();
        tasks.setCreatedTs(LocalDateTime.of(1, 1, 1, 1, 1));
        tasks.setUpdatedTs(LocalDateTime.of(1, 1, 1, 1, 1));
        tasks.setTaskDate(LocalDate.ofEpochDay(1L));
        tasks.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        tasks.setUpdatedBy("2020-03-01");
        tasks.setTaskName("Task Name");
        tasks.setTaskDescription("Task Description");
        tasks.setTaskId(123);
        JournalEntryResponse journalEntryResponse = new JournalEntryResponse();
        journalEntryResponse.setErrorMessage("An error occurred");
        journalEntryResponse.setSuccess(true);
        journalEntryResponse.setData(tasks);
        when(this.journalEntryService.addJournalEntry((CreateJournalEntryRequest) any())).thenReturn(journalEntryResponse);
        CreateJournalEntryRequest createJournalEntryRequest = new CreateJournalEntryRequest();
        createJournalEntryRequest.setTaskDate(null);
        createJournalEntryRequest.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        createJournalEntryRequest.setTaskName("Task Name");
        createJournalEntryRequest.setTaskDescription("Task Description");
        String content = (new ObjectMapper()).writeValueAsString(createJournalEntryRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/addEntry")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.journalEntryController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(500))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"success\":true,\"errorMessage\":\"An error occurred\",\"data\":{\"taskId\":123,\"taskName\":\"Task Name\","
                                        + "\"taskDescription\":\"Task Description\",\"taskDate\":[1970,1,2],\"createdBy\":\"Jan 1, 2020 8:00am GMT+0100\""
                                        + ",\"updatedBy\":\"2020-03-01\",\"createdTs\":[1,1,1,1,1],\"updatedTs\":[1,1,1,1,1]}}"));
    }
    @Test
    public void testGetAllCurrentEntriesWithTaskNamewithError() throws Exception {
        GetMonthEntryResponse getMonthEntryResponse = new GetMonthEntryResponse();
        getMonthEntryResponse.setErrorMessage("An error occurred");
        getMonthEntryResponse.setmonthdata(new ArrayList<>());
        getMonthEntryResponse.setSuccess(true);
        when(this.journalEntryService.getAllCurrentEntriesWithTaskName((String) any())).thenReturn(getMonthEntryResponse);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/getAllCurrentEntriesWithTaskName/{taskName}", "Task Name");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.journalEntryController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(500))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"success\":true,\"errorMessage\":\"An error occurred\",\"monthData\":[]}"));
    }
    @Test
    public void testGetAllCurrentEntriesWithTaskNamewithSuccess() throws Exception {
        GetMonthEntryResponse getMonthEntryResponse = new GetMonthEntryResponse();
        getMonthEntryResponse.setErrorMessage(null);
        getMonthEntryResponse.setmonthdata(new ArrayList<>());
        getMonthEntryResponse.setSuccess(true);
        when(this.journalEntryService.getAllCurrentEntriesWithTaskName((String) any())).thenReturn(getMonthEntryResponse);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/getAllCurrentEntriesWithTaskName/{taskName}", "Task Name");
        MockMvcBuilders.standaloneSetup(this.journalEntryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"success\":true,\"errorMessage\":null,\"monthData\":[]}"));
    }
    @Test
    public void testGetAllMonthEntrieswithError() throws Exception {
        GetMonthEntryResponse getMonthEntryResponse = new GetMonthEntryResponse();
        getMonthEntryResponse.setErrorMessage("An error occurred");
        getMonthEntryResponse.setmonthdata(new ArrayList<>());
        getMonthEntryResponse.setSuccess(true);
        when(this.journalEntryService.getAllMonthEntries((String) any())).thenReturn(getMonthEntryResponse);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/getAllCurrentEntries");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.journalEntryController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(500))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"success\":true,\"errorMessage\":\"An error occurred\",\"monthData\":[]}"));
    }
    @Test
    public void testGetAllMonthEntrieswithNamewithError() throws Exception {
        GetMonthEntryResponse getMonthEntryResponse = new GetMonthEntryResponse();
        getMonthEntryResponse.setErrorMessage("An error occurred");
        getMonthEntryResponse.setmonthdata(new ArrayList<>());
        getMonthEntryResponse.setSuccess(true);
        when(this.journalEntryService.getAllMonthEntries((String) any())).thenReturn(getMonthEntryResponse);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/getAllMonthEntries/{monthName}",
                "Month Name");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.journalEntryController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(500))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"success\":true,\"errorMessage\":\"An error occurred\",\"monthData\":[]}"));
    }
    @Test
    public void testGetAllMonthEntrieswithNamewithSuccess() throws Exception {
        GetMonthEntryResponse getMonthEntryResponse = new GetMonthEntryResponse();
        getMonthEntryResponse.setErrorMessage(null);
        getMonthEntryResponse.setmonthdata(new ArrayList<>());
        getMonthEntryResponse.setSuccess(true);
        when(this.journalEntryService.getAllMonthEntries((String) any())).thenReturn(getMonthEntryResponse);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/getAllMonthEntries/{monthName}",
                "Month Name");
        MockMvcBuilders.standaloneSetup(this.journalEntryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"success\":true,\"errorMessage\":null,\"monthData\":[]}"));
    }
    @Test
    public void testGetAllMonthEntrieswithSuccess() throws Exception {
        GetMonthEntryResponse getMonthEntryResponse = new GetMonthEntryResponse();
        getMonthEntryResponse.setErrorMessage(null);
        getMonthEntryResponse.setmonthdata(new ArrayList<>());
        getMonthEntryResponse.setSuccess(true);
        when(this.journalEntryService.getAllMonthEntries((String) any())).thenReturn(getMonthEntryResponse);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/getAllCurrentEntries");
        MockMvcBuilders.standaloneSetup(this.journalEntryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"success\":true,\"errorMessage\":null,\"monthData\":[]}"));
    }
    @Test
    public void testAddJournalEntrywithSuccess() throws Exception {
        CreateJournalEntryRequest createJournalEntryRequest = new CreateJournalEntryRequest();
        createJournalEntryRequest.setTaskDate(LocalDate.ofEpochDay(1L));
        createJournalEntryRequest.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        createJournalEntryRequest.setTaskName("Task Name");
        createJournalEntryRequest.setTaskDescription("Task Description");
        String content = (new ObjectMapper()).writeValueAsString(createJournalEntryRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/addEntry", "Uri Vars")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.journalEntryController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }
}