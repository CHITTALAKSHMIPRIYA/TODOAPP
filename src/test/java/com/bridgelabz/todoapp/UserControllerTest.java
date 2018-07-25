
package com.bridgelabz.todoapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * @author LAKSHMI PRIYA
 * @since DATE:10-07-2018
 *        <p>
 *        <b>A POJO class with the user details.</b>
 *        </p>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void verifyRegistrationUser() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/user/register").contentType(MediaType.APPLICATION_JSON).content(
				"{ \"emailId\": \"lakshmichitta96@gmail.com\", \"userName\" : \"mani1234\", \"password\" : \"Mani@123\" , \"confirmPassword\": \"Mani@123\", \"firstName\" : \" Lakshmi\", \"lastName\" : \"Priya\",\"mobileNumber\": \"8142434441\"}")
				.accept(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.message").exists())
				.andExpect(jsonPath("$.status").exists())
				.andExpect(jsonPath("$.message").value("Registered successfully"))
				.andExpect(jsonPath("$.status").value(200)).andDo(print());
		//.andExpect(jsonPath("$.message").value("Registration Unsuccessful"))
		//.andExpect(jsonPath("$.status").value(404)).andDo(print());
	}

	@Test
	public void verifyLoginUser() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/user/login").contentType(MediaType.APPLICATION_JSON)
				.content("{ \"emailId\": \"lakshmichitta96@gmail.com\",\"password\" : \"Mani@123\"}")
				.accept(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.message").exists())
				.andExpect(jsonPath("$.status").exists())
				.andExpect(jsonPath("$.message").value("User logged in successfully"))
				.andExpect(jsonPath("$.status").value(200)).andDo(print());
	}

	// @Test
	public void verifyForgotPassword() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/user/forgotpassword").contentType(MediaType.APPLICATION_JSON)
				.content("lakshmichitta96@gmail.com").accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.message").exists()).andExpect(jsonPath("$.status").exists())
				.andExpect(jsonPath("$.message").value("send the user mailid to reset password"))
				.andExpect(jsonPath("$.status").value(200)).andDo(print());
	}

	// @Test
	public void verifyResetPassword() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/user/resetpassword").contentType(MediaType.APPLICATION_JSON)
				.param("token", "").content("{\"newPassword\" : \"Mani@453\" , \"confirmPassword\": \"Mani@453\"}")
				.accept(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.message").exists())
				.andExpect(jsonPath("$.status").exists())
				.andExpect(jsonPath("$.message").value("changed the password successfully"))
				.andExpect(jsonPath("$.status").value(0)).andDo(print());
	}

	// @Test
	public void verifyActivationUser() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.get("/user/activation").param("token", "").accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.message").exists()).andExpect(jsonPath("$.status").exists())
				.andExpect(jsonPath("$.message").value("User activated successfully"))
				.andExpect(jsonPath("$.status").value(0)).andDo(print());

	}
}
