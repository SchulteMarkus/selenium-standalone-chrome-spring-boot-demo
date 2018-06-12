package schulte.markus.seleniumstandalonechromespringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

  @GetMapping
  @ResponseBody
  public String index() {
    return "<!DOCTYPE html><html lang=\"en\">"
      + "<head><title>Selenium standalone Chrome / Spring Boot demo</title></head>"
      + "<body><h1 id=\"h1-hello\">Hello world!</h1>";
  }
}
