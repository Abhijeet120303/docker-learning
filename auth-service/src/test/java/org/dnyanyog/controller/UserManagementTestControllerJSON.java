package org.dnyanyog.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class UserManagementTestControllerJSON extends AbstractTestNGSpringContextTests {

  @Autowired private MockMvc mockMvc;

  @Test
  @Order(1)
  public void addUserSuccessJson() throws Exception {
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post("/api/v1/auth/user/add")
            .content(
                " {\r\n"
                    + "    \"userName\":\"abhi611"
                    + "1211\",\r\n"
                    + "    \"password\":\"test@1232\",\r\n"
                    + "    \"emailId\":\"abhi@gmail.com\",\r\n"
                    + "    \"age\":\"21\",\r\n"
                    + "    \"userId\":\"13\"\r\n"
                    + "}")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Success"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("User added successfully!!"))
        .andReturn();
  }

  @Test
  @Order(2)
  public void emailAlreadyExitJson() throws Exception {
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post("/api/v1/auth/user/add")
            .content(
                " {\r\n"
                    + "    \"userName\":\"abhi\",\r\n"
                    + "    \"password\":\"test@1232\",\r\n"
                    + "    \"emailId\":\"abhi@gmail.com\",\r\n"
                    + "    \"age\":\"21\",\r\n"
                    + "    \"userId\":\"12\"\r\n"
                    + "}")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Fail"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Username Already Exit !!"))
        .andReturn();
  }

  @Test
  @Order(3)
  public void sercahUserSuccessJson() throws Exception {
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.get("/api/v1/auth/user/search/13")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Success"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("User found"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.userCode").value("152"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.userData.username").value("abhi1121"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.userData.password").value("test@1232"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.userData.email").value("abhi@gmail.com"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.userData.age").value("21"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.userData.userId").value("13"))
        .andReturn();
  }

  @Test
  @Order(4)
  public void sercahUserNotFoundJson() throws Exception {
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.get("/api/v1/auth/user/search/11")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Fail"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("User not found"))
        .andReturn();
  }
}
