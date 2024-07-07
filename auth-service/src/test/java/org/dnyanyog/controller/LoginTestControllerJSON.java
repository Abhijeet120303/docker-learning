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
public class LoginTestControllerJSON extends AbstractTestNGSpringContextTests {

  @Autowired private MockMvc mockMvc;

  @Test
  @Order(1)
  public void loginSuccessJson() throws Exception {
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post("/public/auth/validate")
            .content(
                "{\r\n"
                    + "	\"userName\":\"abhi\",\r\n"
                    + "    \"password\":\"test@123\"\r\n"
                    + "}")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Success"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Login Success"))
        .andReturn();
  }

  @Test
  @Order(2)
  public void loginUsernameNotFoundJson() throws Exception {
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post("/public/auth/validate")
            .content(
                "{\r\n"
                    + "	\"userName\":\"abhi3\",\r\n"
                    + "    \"password\":\"test@123\"\r\n"
                    + "}")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Fail"))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.message").value("Username not present in database"))
        .andReturn();
  }

  @Test
  @Order(3)
  public void loginPasswordDoesNotMatchFoundJson() throws Exception {
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post("/public/auth/validate")
            .content(
                "{\r\n"
                    + "	\"userName\":\"abhi\",\r\n"
                    + "    \"password\":\"test@1234\"\r\n"
                    + "}")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Fail"))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.message")
                .value("Username and password does not match"))
        .andReturn();
  }
}
