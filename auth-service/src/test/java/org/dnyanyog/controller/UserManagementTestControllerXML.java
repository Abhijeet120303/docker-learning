package org.dnyanyog.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
public class UserManagementTestControllerXML extends AbstractTestNGSpringContextTests {

  @Autowired private MockMvc mockMvc;

  @Test
  @Order(1)
  public void userAddSuccessXml() throws Exception {
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post("/api/v1/auth/user/add")
            .content(
                "<AddUserRequest>\r\n"
                    + "    <userName>abhi121</userName>\r\n"
                    + "    <password>test@123</password>\r\n"
                    + "    <emailId>abhi@gmail.com</emailId>\r\n"
                    + "    <age>21</age>\r\n"
                    + "    <userId>17</userId>\r\n"
                    + "</AddUserRequest>")
            .contentType(MediaType.APPLICATION_XML_VALUE)
            .accept(MediaType.APPLICATION_XML_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(xpath("/AddUserResponse/status").string("Success"))
        .andExpect(xpath("/AddUserResponse/message").string("User added successfully!!"))
        .andReturn();
  }

  @Test
  @Order(2)
  public void userNameAlreadyExit() throws Exception {
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post("/api/v1/auth/user/add")
            .content(
                "<AddUserRequest>\r\n"
                    + "    <userName>abhi1221</userName>\r\n"
                    + "    <password>test@123</password>\r\n"
                    + "    <emailId>abhi@gmail.com</emailId>\r\n"
                    + "    <age>21</age>\r\n"
                    + "    <userId>12</userId>\r\n"
                    + "</AddUserRequest>")
            .contentType(MediaType.APPLICATION_XML_VALUE)
            .accept(MediaType.APPLICATION_XML_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(xpath("/AddUserResponse/status").string("Fail"))
        .andExpect(xpath("/AddUserResponse/message").string("Username Already Exit !!"))
        .andReturn();
  }

  @Test
  @Order(3)
  public void serachUserSuccessXml() throws Exception {
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.get("/api/v1/auth/user/search/17")
            .contentType(MediaType.APPLICATION_XML_VALUE)
            .accept(MediaType.APPLICATION_XML_VALUE);
    mockMvc
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.xpath("/AddUserResponse/status").string("Success"))
        .andExpect(MockMvcResultMatchers.xpath("/AddUserResponse/message").string("User found"))
        .andExpect(MockMvcResultMatchers.xpath("/AddUserResponse/userCode").string("402"))
        .andExpect(
            MockMvcResultMatchers.xpath("/AddUserResponse/userData/username").string("abhi1812221"))
        .andExpect(
            MockMvcResultMatchers.xpath("/AddUserResponse/userData/password").string("test@123"))
        .andExpect(
            MockMvcResultMatchers.xpath("/AddUserResponse/userData/email").string("abhi@gmail.com"))
        .andExpect(MockMvcResultMatchers.xpath("/AddUserResponse/userData/age").string("21"))
        .andExpect(MockMvcResultMatchers.xpath("/AddUserResponse/userData/userId").string("17"))
        .andReturn();
  }

  @Test
  @Order(4)
  public void userNotFoundXml() throws Exception {
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.get("/api/v1/auth/user/search/15")
            .contentType(MediaType.APPLICATION_XML_VALUE)
            .accept(MediaType.APPLICATION_XML_VALUE);
    mockMvc
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.xpath("/AddUserResponse/status").string("Fail"))
        .andExpect(MockMvcResultMatchers.xpath("/AddUserResponse/message").string("User not found"))
        .andReturn();
  }
}
