package schulte.markus.seleniumstandalonechromespringboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  private static final String ENDPOINT = "/";

  @GetMapping(ENDPOINT)
  public String index() {
    return "<h1 id=\"h1-hello\">Hello world!</h1>";
  }
}
