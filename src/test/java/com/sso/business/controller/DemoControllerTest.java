package com.sso.business.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSON;
import com.sso.business.vo.DemoVO;

/**
 * Created by yt on 2017-7-6.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({
		@ContextConfiguration(name = "parent", locations = "classpath:config/spring.xml"),
		@ContextConfiguration(name = "child", locations = "classpath:config/spring-mvc.xml")
})
@WebAppConfiguration(value = "src/main/webapp")
@Profile("dev")
public class DemoControllerTest {


	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;
	private MockHttpSession session;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		this.session = new MockHttpSession();
	}

	@Test
	public void queryById() throws Exception {
		MvcResult result =mockMvc.perform(MockMvcRequestBuilders.get("/demo/{id}",10)
				.accept(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
		Assert.assertTrue(true);
	}

	@Test
	public void updatePut() throws Exception {
		DemoVO demoVO=new DemoVO();
		demoVO.setName("demo");
		demoVO.setPrice(11);
		MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.put("/demo")
				.contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8")
				.content(JSON.toJSONString(demoVO))
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError())
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
		Assert.assertTrue(true);
	}



}