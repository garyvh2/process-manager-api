package ac.cr.ucenfotec.process_manager.processmanagerapi.controllers.test;
import org.bson.types.ObjectId;
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

import com.fasterxml.jackson.databind.ObjectMapper;

import ac.cr.ucenfotec.process_manager.entities.UserType;
import ac.cr.ucenfotec.process_manager.processmanagerapi.controllers.UserTypeController;
import ac.cr.ucenfotec.process_manager.processmanagerapi.repositories.UserTypeRepository;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserTypeController.class, secure = false)
public class UserTypeControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	String userTypeId = new ObjectId().toString();
	
	UserType userType = new UserType(userTypeId, "test");
	
	@MockBean
	 private UserTypeRepository userTypeRepository;
	 
	 @Test 
	 public void RetriveAllUserTypes() throws Exception {
		 //given
		 List<UserType> userTList = Arrays.asList(userType);
		 Mockito.when(
				 userTypeRepository.findAll())
		 .thenReturn(userTList);
		 //when
		 MvcResult result = mvc.perform(get("/usertypes")
	              // .with(user("blaze").password("Q1w2e3r4"))
	               .contentType(MediaType.APPLICATION_JSON))
	               .andExpect(status().isOk())
	               .andExpect(jsonPath("$", hasSize(1)))
	               .andReturn();
		 
		 //Test
		 String expected = "[{userTypeId:"+ userTypeId + ",userTypeName:" + userType.getUserTypeName() + "}]";
		 JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	 }
	 
	 @Test 
	 public void RetrieveSingleUser() throws Exception{
		//given
		 Optional<UserType> oUserType =  Optional.of(userType);
		 Mockito.when(
				 userTypeRepository.findById(userTypeId))
		 .thenReturn(oUserType);
		 //when
		 MvcResult result = mvc.perform(get("/usertypes/"+userTypeId)
	              // .with(user("blaze").password("Q1w2e3r4"))
	               .contentType(MediaType.APPLICATION_JSON))
	               .andExpect(status().isOk())
	               .andReturn();
		 //Test
		 String expected = "{userTypeId:"+ userTypeId + ",userTypeName:" + userType.getUserTypeName() + "}";
		 JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
	 }
	 
	 @Test
	 public void UserTypeNotFound() throws Exception{
			//given
		 Optional<UserType> oUserType =  Optional.empty();
		 Mockito.when(
				 userTypeRepository.findById(userTypeId)
				 ).thenReturn(oUserType);
		 //when
		 MvcResult result = mvc.perform(get("/usertypes/"+userTypeId)
	               .contentType(MediaType.APPLICATION_JSON))
	               .andReturn();
		 MockHttpServletResponse response = result.getResponse();
		 assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
	 }
	 
	 @Test
	 public void createUserType() throws Exception {
		 ObjectMapper mapper = new ObjectMapper();
		 String jsonUserType =  mapper.writeValueAsString(userType);
		 Mockito.when(
				 userTypeRepository.save(Mockito.any(UserType.class))
				 ).thenReturn(userType);
		 
		 MvcResult result = mvc.perform(post("/usertypes")
				   .accept(MediaType.APPLICATION_JSON)
				   .content(jsonUserType)
	               .contentType(MediaType.APPLICATION_JSON))
	               .andReturn();
		 
		 MockHttpServletResponse response = result.getResponse();
		 assertEquals(HttpStatus.OK.value(), response.getStatus());
		 JSONAssert.assertEquals(jsonUserType, response.getContentAsString(), true);		 
	 }
	 
	 @Test
	 public void updateUserType() throws Exception {
		 ObjectMapper mapper = new ObjectMapper();
		 Optional<UserType> oUserType =  Optional.of(userType);
		 UserType updateUserType = new UserType(userTypeId, "testUpdated");
		 String jsonUserType =  mapper.writeValueAsString(updateUserType);
		 Mockito.when(
				 userTypeRepository.findById(userTypeId))
		 .thenReturn(oUserType);
		 Mockito.when(
				 userTypeRepository.save(Mockito.any(UserType.class))
				 ).thenReturn(updateUserType);
		 		 
		 MvcResult result = mvc.perform(put("/usertypes/"+userTypeId)
				   .accept(MediaType.APPLICATION_JSON)
				   .content(jsonUserType)
	               .contentType(MediaType.APPLICATION_JSON))
	               .andReturn();
		
		 MockHttpServletResponse response = result.getResponse();
		 assertEquals(HttpStatus.OK.value(), response.getStatus());
		 JSONAssert.assertEquals(jsonUserType, response.getContentAsString(), true);
	 }
	 
	 @Test
	 public void updateUserTypeNotFound()throws Exception {
		 ObjectMapper mapper = new ObjectMapper();
		 Optional<UserType> oUserType =  Optional.empty();
		 UserType updateUserType = new UserType(userTypeId, "testUpdated");
		 String jsonUserType =  mapper.writeValueAsString(updateUserType);
		 Mockito.when(
				 userTypeRepository.findById(userTypeId))
		 .thenReturn(oUserType);
		 		 
		 MvcResult result = mvc.perform(put("/usertypes/"+userTypeId)
				   .accept(MediaType.APPLICATION_JSON)
				   .content(jsonUserType)
	               .contentType(MediaType.APPLICATION_JSON))
	               .andReturn();
		 MockHttpServletResponse response = result.getResponse();
		 assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
	 }
	 
	 @Test 
	 public void updateInvalidUserType () throws Exception {
		 ObjectMapper mapper = new ObjectMapper();
		 UserType updateUserType = new UserType(userTypeId, null);
		 String jsonUserType =  mapper.writeValueAsString(updateUserType);
		 		 
		 MvcResult result = mvc.perform(put("/usertypes/"+userTypeId)
				   .accept(MediaType.APPLICATION_JSON)
				   .content(jsonUserType)
	               .contentType(MediaType.APPLICATION_JSON))
	               .andReturn();
		 MockHttpServletResponse response = result.getResponse();
		 assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
	 }
	 
	 @Test 
	 public void deleteUserType() throws Exception {
		 ObjectMapper mapper = new ObjectMapper();
		 Optional<UserType> oUserType =  Optional.of(userType);
		 String jsonUserType =  mapper.writeValueAsString(userType);
		 Mockito.when(
				 userTypeRepository.findById(userTypeId))
		 .thenReturn(oUserType);

		 
		 MvcResult result = mvc.perform(put("/usertypes/"+userTypeId)
				   .accept(MediaType.APPLICATION_JSON)
				   .content(jsonUserType)
	               .contentType(MediaType.APPLICATION_JSON))
	               .andReturn();
		 MockHttpServletResponse response = result.getResponse();
		 assertEquals(HttpStatus.OK.value(), response.getStatus());
	 }
	 
	 @Test
	 public void deleteUserTypeNotFound() throws Exception {
		 ObjectMapper mapper = new ObjectMapper();
		 Optional<UserType> oUserType =  Optional.empty();
		 String jsonUserType =  mapper.writeValueAsString(userType);
		 Mockito.when(
				 userTypeRepository.findById(userTypeId))
		 .thenReturn(oUserType);
		 
		 MvcResult result = mvc.perform(put("/usertypes/"+userTypeId)
				   .accept(MediaType.APPLICATION_JSON)
				   .content(jsonUserType)
	               .contentType(MediaType.APPLICATION_JSON))
	               .andReturn();
		 MockHttpServletResponse response = result.getResponse();
		 assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
	 }
}
