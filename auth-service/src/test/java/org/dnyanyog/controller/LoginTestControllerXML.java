package org.dnyanyog.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

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

@SpringBootTest
@AutoConfigureMockMvc
public class LoginTestControllerXML extends AbstractTestNGSpringContextTests {

  @Autowired private MockMvc mockMvc;

  @Test
  @Order(1)
  public void loginSuccessXml() throws Exception {
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post("/public/auth/validate")
            .content(
                "<LoginRequest>\r\n"
                    + "    <userName>abhi</userName>\r\n"
                    + "    <password>test@123</password>\r\n"
                    + "</LoginRequest>")
            .contentType(MediaType.APPLICATION_XML_VALUE)
            .accept(MediaType.APPLICATION_XML_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(xpath("/LoginResponse/status").string("Success"))
        .andExpect(xpath("/LoginResponse/message").string("Login Success"))
        .andReturn();
  }

  @Test
  @Order(2)
  public void loginUsernameNotFoundXml() throws Exception {
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post("/public/auth/validate")
            .content(
                "<LoginRequest>\r\n"
                    + "    <userName>abhi12</userName>\r\n"
                    + "    <password>test@123</password>\r\n"
                    + "</LoginRequest>")
            .contentType(MediaType.APPLICATION_XML_VALUE)
            .accept(MediaType.APPLICATION_XML_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(xpath("/LoginResponse/status").string("Fail"))
        .andExpect(xpath("/LoginResponse/message").string("Username not present in database"))
        .andReturn();
  }

  @Test
  @Order(3)
  public void loginPasswordDoesNotMatchXml() throws Exception {
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post("/public/auth/validate")
            .content(
                "<LoginRequest>\r\n"
                    + "    <userName>abhi</userName>\r\n"
                    + "    <password>test@1234</password>\r\n"
                    + "</LoginRequest>")
            .contentType(MediaType.APPLICATION_XML_VALUE)
            .accept(MediaType.APPLICATION_XML_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(xpath("/LoginResponse/status").string("Fail"))
        .andExpect(xpath("/LoginResponse/message").string("Username and password does not match"))
        .andReturn();
  }
}
