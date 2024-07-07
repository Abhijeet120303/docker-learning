package org.dnyanyog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @GetMapping(path = "api/v1/get")
  public String greetingAbhi() {

    System.out.println("Hello Abhi !!");
    return "Hello Abhi!!";
  }

  @GetMapping(path = "/")
  public String greeting() {

    System.out.println("Hello!!");
    return "Hello!!";
  }
}
