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
public class EventControllerTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void addEvent() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.post("/event/add")
						.param("userId", "1")
						.param("localDateTime", "2019-01-12 13:30")
						.param("type", "qwerty")
						.param("description", "qwerty")
		)
				.andExpect(MockMvcResultMatchers.status().isNotFound())
				.andExpect(MockMvcResultMatchers.content().string(StringContains.containsString("User not found")));
	}

	@Test
	public void getEvents() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.get("/event/all")
		)
				.andExpect(MockMvcResultMatchers.status().isNotFound())
				.andExpect(MockMvcResultMatchers.content().string(StringContains.containsString("There's no events")));
	}

	@Test
	public void getEventByIdAndDate() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.get("/event")
						.param("userId", "1")
						.param("localDateTime", "2019-01-12 13:30")
		)
				.andExpect(MockMvcResultMatchers.status().isNotFound())
				.andExpect(MockMvcResultMatchers.content().string(StringContains.containsString("There's no events for this date or user")));
	}

	@Test
	public void getEventByIdAndNotValidDate() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.get("/event")
						.param("userId", "1")
						.param("localDateTime", "qwerty")
		)
				.andExpect(MockMvcResultMatchers.status().isBadRequest())
				.andExpect(MockMvcResultMatchers.content().string(StringContains.containsString("Please, use right date-time format: yyyy-MM-dd HH:mm:ss")));
	}

	@Test
	public void getEventByIdAndNullValidDate() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.get("/event")
						.param("userId", "1")
						.param("localDateTime", "")
		)
				.andExpect(MockMvcResultMatchers.status().isBadRequest())
				.andExpect(MockMvcResultMatchers.content().string(StringContains.containsString("Date and time can't be null")));
	}
}