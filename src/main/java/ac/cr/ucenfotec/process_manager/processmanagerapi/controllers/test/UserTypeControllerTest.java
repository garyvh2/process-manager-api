//package ac.cr.ucenfotec.process_manager.processmanagerapi.controllers.test;
//
//import static org.junit.Assert.*;
//
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.boot.test.json.JacksonTester;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import ac.cr.ucenfotec.process_manager.processmanagerapi.controllers.UserController;
//import ac.cr.ucenfotec.process_manager.processmanagerapi.repositories.UserTypeRepository;
//
//@RunWith(MockitoJUnitRunner.class)
//public class UserTypeControllerTest {
//	private MockMvc mvc;
//	
//	 @Mock
//	 private UserTypeRepository userTypeRepository;
//	 
//	 @InjectMocks
//	 private UserController userController;
//	 @Before
//	    public void setup() {
//	        // We would need this line if we would not use MockitoJUnitRunner
//	        // MockitoAnnotations.initMocks(this);
//	        // Initializes the JacksonTester
//	        JacksonTester.initFields(this, new ObjectMapper());
//	        // MockMvc standalone approach
//	        mvc = MockMvcBuilders.standaloneSetup(userController)
//	                .setControllerAdvice(new SuperHeroExceptionHandler())
//	                .addFilters(new SuperHeroFilter())
//	                .build();
//	    }
//
//	@BeforeClass
//	public static void setUpBeforeClass() throws Exception {
//	}
//
//	@Test
//	public void test() {
//		fail("Not yet implemented");
//	}
//
//}
