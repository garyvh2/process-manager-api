package ac.cr.ucenfotec.process_manager.processmanagerapi.controllers;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


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


import ac.cr.ucenfotec.process_manager.entities.User;
import ac.cr.ucenfotec.process_manager.processmanagerapi.controllers.UserController;
import ac.cr.ucenfotec.process_manager.processmanagerapi.repositories.UserRepository;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class, secure = false)
public class UserControllerTest {
	@Autowired
	private MockMvc mvc;
	@MockBean
	 private UserRepository userRepository;
	User testUser;
	ObjectMapper mapper;
	String userId;
	@Before
	public   void init() throws JsonParseException, JsonMappingException, IOException {
		String url = "src/main/resources/user.json" ;
		File jsonFile = new File(url);
		mapper = new ObjectMapper();
		testUser = mapper.readValue(jsonFile, User.class);
		System.out.println(jsonFile.toString());
		userId =  testUser.getUserId();
	}
	 @Test 
	 public void RetriveAllUser() throws Exception {
		 //given
		 List<User> userList = Arrays.asList(testUser);
		 Mockito.when(
				 userRepository.findAll())
		 .thenReturn(userList);
		 //when
		 MvcResult result = mvc.perform(get("/users")
	              // .with(user("blaze").password("Q1w2e3r4"))
	               .contentType(MediaType.APPLICATION_JSON))
	               .andExpect(status().isOk())
	               .andExpect(jsonPath("$", hasSize(1)))
	               .andReturn();
		 
		 //Test
		 User[] returnedUser=  mapper.readValue(result.getResponse().getContentAsString(), User[].class);
		 assertEquals(true,testUser.equals(returnedUser[0]));
	 }
	 
	 @Test 
	 public void RetrieveSingleUser() throws Exception{
		//given
		 Optional<User> oUser =  Optional.of(testUser);
		 Mockito.when(
				 userRepository.findById(userId))
		 .thenReturn(oUser);
		 //when
		 MvcResult result = mvc.perform(get("/users/"+userId)
	              // .with(user("blaze").password("Q1w2e3r4"))
	               .contentType(MediaType.APPLICATION_JSON))
	               .andExpect(status().isOk())
	               .andReturn();

		 
		 //Test
		 User returnedUser =  mapper.readValue(result.getResponse().getContentAsString(), User.class);
		 assertEquals(true,testUser.equals(returnedUser));
	 }
	 @Test
	 public void UserNotFound() throws Exception{
			//given
		 Optional<User> oUser =  Optional.empty();
		 Mockito.when(
				 userRepository.findById(userId)
				 ).thenReturn(oUser);
		 //when
		 MvcResult result = mvc.perform(get("/users/"+userId)
	               .contentType(MediaType.APPLICATION_JSON))
	               .andReturn();
		 MockHttpServletResponse response = result.getResponse();
		 assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());

	 }
	 
	 @Test
	 public void createUser() throws Exception {
		 ObjectMapper mapper = new ObjectMapper();
		 String jsonUser =  mapper.writeValueAsString(testUser);
		 Mockito.when(
				 userRepository.save(Mockito.any(User.class))
				 ).thenReturn(testUser);
		 
		 MvcResult result = mvc.perform(post("/users")
				   .accept(MediaType.APPLICATION_JSON)
				   .content(jsonUser)
	               .contentType(MediaType.APPLICATION_JSON))
	               .andReturn();
		 
		 MockHttpServletResponse response = result.getResponse();
		 assertEquals(HttpStatus.OK.value(), response.getStatus());
		 JSONAssert.assertEquals(jsonUser, response.getContentAsString(), false);
		 
	 }
	 
	 @Test
	 public void updateUser() throws Exception {
		 ObjectMapper mapper = new ObjectMapper();
		 Optional<User> oUser =  Optional.of(testUser);
		 User updatedUser = testUser;
		 updatedUser.setUserEmail("inela@gmail.com");
		 String jsonUser =  mapper.writeValueAsString(updatedUser);
		 Mockito.when(
				 userRepository.findById(userId))
		 .thenReturn(oUser);
		 Mockito.when(
				 userRepository.save(Mockito.any(User.class))
				 ).thenReturn(updatedUser);
		 		 
		 MvcResult result = mvc.perform(put("/users/"+userId)
				   .accept(MediaType.APPLICATION_JSON)
				   .content(jsonUser)
	               .contentType(MediaType.APPLICATION_JSON))
	               .andReturn();
		
		 MockHttpServletResponse response = result.getResponse();
		 assertEquals(HttpStatus.OK.value(), response.getStatus());
		 JSONAssert.assertEquals(jsonUser, response.getContentAsString(), true);
	 }
	 
	 @Test
	 public void updateUserNotFound()throws Exception {
		 Optional<User> oUser =  Optional.empty();
		 User updatedUser = testUser;
		
		 String jsonUser =  mapper.writeValueAsString(updatedUser);
		 Mockito.when(
				 userRepository.findById(userId))
		 .thenReturn(oUser);

		 
		 
		 
		 MvcResult result = mvc.perform(put("/users/"+userId)
				   .accept(MediaType.APPLICATION_JSON)
				   .content(jsonUser)
	               .contentType(MediaType.APPLICATION_JSON))
	               .andReturn();
		
		 MockHttpServletResponse response = result.getResponse();
		 assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
	 }
	 
	 @Test 
	 public void updateInvalidUser() throws Exception {
		 ObjectMapper mapper = new ObjectMapper();
		 Optional<User> oUser =  Optional.of(testUser);
		 User updatedUser = testUser;
		 updatedUser.setUserEmail(null);
		 updatedUser.setUserName(null);
		 String jsonUser =  mapper.writeValueAsString(updatedUser);
		 Mockito.when(
				 userRepository.findById(userId))
		 .thenReturn(oUser);
		 Mockito.when(
				 userRepository.save(Mockito.any(User.class))
				 ).thenReturn(updatedUser);
		 
		 
		 
		 
		 MvcResult result = mvc.perform(put("/users/"+userId)
				   .accept(MediaType.APPLICATION_JSON)
				   .content(jsonUser)
	               .contentType(MediaType.APPLICATION_JSON))
	               .andReturn();
		 MockHttpServletResponse response = result.getResponse();
		 assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
	 }
	 
	 @Test 
	 public void deleteUser() throws Exception {
		 ObjectMapper mapper = new ObjectMapper();
		 Optional<User> oUser =  Optional.of(testUser);
		 String jsonUser =  mapper.writeValueAsString(testUser);
		 Mockito.when(
				 userRepository.findById(userId))
		 .thenReturn(oUser);

		 
		 MvcResult result = mvc.perform(put("/users/"+userId)
				   .accept(MediaType.APPLICATION_JSON)
				   .content(jsonUser)
	               .contentType(MediaType.APPLICATION_JSON))
	               .andReturn();
		 MockHttpServletResponse response = result.getResponse();
		 assertEquals(HttpStatus.OK.value(), response.getStatus());
		
		 
	 }
	 
	 @Test
	 public void deleteProcessNotFound() throws Exception {
		 ObjectMapper mapper = new ObjectMapper();
		 Optional<User> oUser =  Optional.empty();
		 String jsonUser =  mapper.writeValueAsString(testUser);
		 Mockito.when(
				 userRepository.findById(userId))
		 .thenReturn(oUser);


		 
		 MvcResult result = mvc.perform(put("/processes/"+userId)
				   .accept(MediaType.APPLICATION_JSON)
				   .content(jsonUser)
	               .contentType(MediaType.APPLICATION_JSON))
	               .andReturn();
		 MockHttpServletResponse response = result.getResponse();
		 assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
	 }
	 
	


}
