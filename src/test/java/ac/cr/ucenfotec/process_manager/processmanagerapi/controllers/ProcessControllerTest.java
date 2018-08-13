package ac.cr.ucenfotec.process_manager.processmanagerapi.controllers;


import org.junit.Before;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ac.cr.ucenfotec.process_manager.entities.ProcessTemplate;
import ac.cr.ucenfotec.process_manager.processmanagerapi.controllers.ProcessController;
import ac.cr.ucenfotec.process_manager.processmanagerapi.repositories.ProcessRepository;


import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ProcessController.class, secure = false)
public class ProcessControllerTest {
	
	@Autowired
	private MockMvc mvc;
	 String processId;
	 ProcessTemplate process;
	 ObjectMapper mapper;
	 
	@MockBean
	 private ProcessRepository processRepository;
	
	@Before
	public   void init() throws JsonParseException, JsonMappingException, IOException {
		String url = "src/main/resources/process.json" ;
		File jsonFile = new File(url);
		mapper = new ObjectMapper();
		process = mapper.readValue(jsonFile, ProcessTemplate.class);
		System.out.println(jsonFile.toString());
		processId =  process.getNumeroTramite();
	}
		 
	 @Test 
	 public void RetriveAllProcess() throws Exception {
		 //given
		 List<ProcessTemplate> processList = Arrays.asList(process);
		 Mockito.when(
				 processRepository.findAll())
		 .thenReturn(processList);
		 //when
		 MvcResult result = mvc.perform(get("/processes")
	              // .with(user("blaze").password("Q1w2e3r4"))
	               .contentType(MediaType.APPLICATION_JSON))
	               .andExpect(status().isOk())
	               .andExpect(jsonPath("$", hasSize(1)))
	               .andReturn();
		 
		 //Test
		 ProcessTemplate[] returnedProcess =  mapper.readValue(result.getResponse().getContentAsString(), ProcessTemplate[].class);
		 assertEquals(true,process.equals(returnedProcess[0]));
	 }
 
	 @Test 
	 public void RetrieveSingleProcess() throws Exception{
		//given
		 Optional<ProcessTemplate> oProcess =  Optional.of(process);
		 Mockito.when(
				 processRepository.findById(processId))
		 .thenReturn(oProcess);
		 //when
		 MvcResult result = mvc.perform(get("/processes/"+processId)
	              // .with(user("blaze").password("Q1w2e3r4"))
	               .contentType(MediaType.APPLICATION_JSON))
	               .andExpect(status().isOk())
	               .andReturn();

		 
		 //Test
		 ProcessTemplate returnedProcess =  mapper.readValue(result.getResponse().getContentAsString(), ProcessTemplate.class);
		 assertEquals(true,process.equals(returnedProcess));
	 }
	 
	 @Test
	 public void ProcessNotFound() throws Exception{
			//given
		 Optional<ProcessTemplate> oProcess =  Optional.empty();
		 Mockito.when(
				 processRepository.findById(processId)
				 ).thenReturn(oProcess);
		 //when
		 MvcResult result = mvc.perform(get("/processes/"+processId)
	               .contentType(MediaType.APPLICATION_JSON))
	               .andReturn();
		 MockHttpServletResponse response = result.getResponse();
		 assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());

	 }
	 
	 @Test
	 public void createProcess() throws Exception {
		 ObjectMapper mapper = new ObjectMapper();
		 String jsonUserType =  mapper.writeValueAsString(process);
		 Mockito.when(
				 processRepository.save(Mockito.any(ProcessTemplate.class))
				 ).thenReturn(process);
		 
		 MvcResult result = mvc.perform(post("/processes")
				   .accept(MediaType.APPLICATION_JSON)
				   .content(jsonUserType)
	               .contentType(MediaType.APPLICATION_JSON))
	               .andReturn();
		 
		 MockHttpServletResponse response = result.getResponse();
		 assertEquals(HttpStatus.OK.value(), response.getStatus());
		 JSONAssert.assertEquals(jsonUserType, response.getContentAsString(), false);
		 
	 }
	 
	 @Test
	 public void updateProcess() throws Exception {
		 ObjectMapper mapper = new ObjectMapper();
		 Optional<ProcessTemplate> oProcess =  Optional.of(process);
		 ProcessTemplate updatedProcess = process;
		 updatedProcess.getTasks().add(process.getTasks().get(0));
		 String jsonProcess =  mapper.writeValueAsString(updatedProcess);
		 Mockito.when(
				 processRepository.findById(processId))
		 .thenReturn(oProcess);
		 Mockito.when(
				 processRepository.save(Mockito.any(ProcessTemplate.class))
				 ).thenReturn(updatedProcess);
		 		 
		 MvcResult result = mvc.perform(put("/processes/"+processId)
				   .accept(MediaType.APPLICATION_JSON)
				   .content(jsonProcess)
	               .contentType(MediaType.APPLICATION_JSON))
	               .andReturn();
		
		 MockHttpServletResponse response = result.getResponse();
		 assertEquals(HttpStatus.OK.value(), response.getStatus());
		 JSONAssert.assertEquals(jsonProcess, response.getContentAsString(), true);
	 }
	 
	 @Test
	 public void updateProcessNotFound()throws Exception {
		 Optional<ProcessTemplate> oProcess =  Optional.empty();
		 ProcessTemplate updatedProcess = process;
		 updatedProcess.getTasks().add(process.getTasks().get(0));
		 String jsonProcess =  mapper.writeValueAsString(updatedProcess);
		 Mockito.when(
				 processRepository.findById(processId))
		 .thenReturn(oProcess);

		 
		 
		 
		 MvcResult result = mvc.perform(put("/processes/"+processId)
				   .accept(MediaType.APPLICATION_JSON)
				   .content(jsonProcess)
	               .contentType(MediaType.APPLICATION_JSON))
	               .andReturn();
		
		 MockHttpServletResponse response = result.getResponse();
		 assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
	 }
	 
	 @Test 
	 public void updateInvalidProcess () throws Exception {
		 ObjectMapper mapper = new ObjectMapper();
		 Optional<ProcessTemplate> oProcess =  Optional.of(process);
		 ProcessTemplate updatedProcess = process;
		 updatedProcess.getTasks().add(process.getTasks().get(0));
		 updatedProcess.setDescription(null);
		 updatedProcess.setNumeroTramite(null);
		 String jsonProcess =  mapper.writeValueAsString(updatedProcess);
		 Mockito.when(
				 processRepository.findById(processId))
		 .thenReturn(oProcess);
		 Mockito.when(
				 processRepository.save(Mockito.any(ProcessTemplate.class))
				 ).thenReturn(updatedProcess);
		 
		 
		 
		 
		 MvcResult result = mvc.perform(put("/processes/"+processId)
				   .accept(MediaType.APPLICATION_JSON)
				   .content(jsonProcess)
	               .contentType(MediaType.APPLICATION_JSON))
	               .andReturn();
		 MockHttpServletResponse response = result.getResponse();
		 assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
	 }
	 
	 @Test 
	 public void deleteProcess() throws Exception {

		 Optional<ProcessTemplate> oProcess =  Optional.of(process);
		 String jsonUserType =  mapper.writeValueAsString(process);
		 Mockito.when(
				 processRepository.findById(processId))
		 .thenReturn(oProcess);

		 
		 MvcResult result = mvc.perform(put("/processes/"+processId)
				   .accept(MediaType.APPLICATION_JSON)
				   .content(jsonUserType)
	               .contentType(MediaType.APPLICATION_JSON))
	               .andReturn();
		 MockHttpServletResponse response = result.getResponse();
		 assertEquals(HttpStatus.OK.value(), response.getStatus());
		
		 
	 }
	 
	 @Test
	 public void deleteProcessNotFound() throws Exception {
		 ObjectMapper mapper = new ObjectMapper();
		 Optional<ProcessTemplate> oProcess =  Optional.empty();
		 String jsonUserType =  mapper.writeValueAsString(process);
		 Mockito.when(
				 processRepository.findById(processId))
		 .thenReturn(oProcess);

		 
		 MvcResult result = mvc.perform(put("/processes/"+processId)
				   .accept(MediaType.APPLICATION_JSON)
				   .content(jsonUserType)
	               .contentType(MediaType.APPLICATION_JSON))
	               .andReturn();
		 MockHttpServletResponse response = result.getResponse();
		 assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
	 }
}
