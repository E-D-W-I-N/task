package com.vsk.controller;

import com.vsk.config.WebConfig;
import org.hamcrest.core.StringContains;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class})
public class UserControllerTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void addUser() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.post("/user/add")
						.param("username", "1")
						.param("email", "www@mail.ru")
						.param("phone", "8-951-607-8956")
		)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(StringContains.containsString("1")));
	}

	@Test
	public void getUsers() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.get("/user/all")
		)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(StringContains.containsString("username")))
				.andExpect(MockMvcResultMatchers.content().string(StringContains.containsString("email")))
				.andExpect(MockMvcResultMatchers.content().string(StringContains.containsString("phone")));
	}

	@Test
	public void addUserWithNotValidEmail() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.post("/user/add")
						.param("username", "www")
						.param("email", "www")
						.param("phone", "8-951-607-8956")
		)
				.andExpect(MockMvcResultMatchers.status().isBadRequest())
				.andExpect(MockMvcResultMatchers.content().string(StringContains.containsString("Email must be valid")));
	}

	@Test
	public void addUserWithNotValidPhone() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.post("/user/add")
						.param("username", "www")
						.param("email", "www@mail.ru")
						.param("phone", "222")
		)
				.andExpect(MockMvcResultMatchers.status().isBadRequest())
				.andExpect(MockMvcResultMatchers.content().string(
						StringContains.containsString("Please, use right phone number format. For example: 8-903-503-9832")));
	}

	@Test
	public void addUserWithNullEmail() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.post("/user/add")
						.param("username", "www")
						.param("email", "")
						.param("phone", "222")
		)
				.andExpect(MockMvcResultMatchers.status().isBadRequest())
				.andExpect(MockMvcResultMatchers.content().string(
						StringContains.containsString("Email can't be null")));
	}

	@Test
	public void addUserWithNullPhone() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.post("/user/add")
						.param("username", "www")
						.param("email", "www@mail.ru")
						.param("phone", "")
		)
				.andExpect(MockMvcResultMatchers.status().isBadRequest())
				.andExpect(MockMvcResultMatchers.content().string(
						StringContains.containsString("Phone can't be null")));
	}
}