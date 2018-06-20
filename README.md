# [Selenium standalone Chrome / Spring Boot demo](https://github.com/SchulteMarkus/selenium-standalone-chrome-spring-boot-demo)
[![Gitter](https://badges.gitter.im/selenium-standalone-chrome-spring-boot-demo.svg)](https://gitter.im/selenium-standalone-chrome-spring-boot-demo/Lobby)
[![Build Status](https://travis-ci.org/SchulteMarkus/selenium-standalone-chrome-spring-boot-demo.svg?branch=master)](https://travis-ci.org/SchulteMarkus/selenium-standalone-chrome-spring-boot-demo)
[![Checkstyle count](https://s3.amazonaws.com/schultedev-xml-metrics-to-html/github/SchulteMarkus/selenium-standalone-chrome-spring-boot-demo/master/checkstyle/count.svg)](https://s3.amazonaws.com/schultedev-xml-metrics-to-html/github/SchulteMarkus/selenium-standalone-chrome-spring-boot-demo/master/checkstyle/index.html)

Demonstrating [Selenium standalone Chrome](https://github.com/SeleniumHQ/docker-selenium/tree/master/StandaloneChromeDebug) in a 
[Spring Boot](https://projects.spring.io/spring-boot/) project.

## Challenge

You got a [Spring Boot](https://projects.spring.io/spring-boot/) application. You want to 
integration-test this application with [Selenium](http://www.seleniumhq.org/) using a real browser 
like [Google Chrome](https://www.google.de/chrome/browser/desktop/index.html). You are using 
[Docker](https://www.docker.com/)([-compose](https://docs.docker.com/compose/)), but you 
**don't have an OperatingSystem-GUI available**.

## Demo setup

For this demo, [Spring Boot](https://projects.spring.io/spring-boot/) is used, which ships with a lot
of [build-in integration-test features](https://docs.spring.io/spring/docs/current/spring-framework-reference/html/integration-testing.html).
The application is very simple, it serves *http://localhost:8080/* as a "Hello World"-endpoint, see 
[HelloController.java](src/main/java/schulte/markus/seleniumstandalonechromespringboot/controller/HelloController.java).

## Demo usage

**Required**
- [Maven](https://maven.apache.org/)
- [Docker](https://www.docker.com/), including [Docker-compose](https://docs.docker.com/compose/)

```bash
selenium-standalone-chrome-spring-boot-demo $ mvn verify
...
Running schulte.markus.seleniumstandalonechromespringboot.controller.HelloControllerIT
...
[INFO] BUILD SUCCESS
```

```bash
$ docker ps # While "mvn verify" runs
CONTAINER ID        IMAGE                              ...    PORTS                                              NAMES
2ec2cd60f14a        selenium/standalone-chrome-debug   ...    0.0.0.0:32779->4444/tcp, 0.0.0.0:32778->5900/tcp   a5fc9c37_selenium-standalone-chrome_1
```

## Solution

I am describing basic setup here. For details, have a look at [HelloControllerIT.java](src/test/java/schulte/markus/seleniumstandalonechromespringboot/controller/HelloControllerIT.java),
which is straight-forward.

Selenium provides a docker-image [selenium/standalone-chrome](https://hub.docker.com/r/selenium/standalone-chrome-debug/).
You can use this docker-image within your test by using [Docker Compose JUnit Rule](https://github.com/palantir/docker-compose-rule).
You have to use [selenium-java](http://central.maven.org/maven2/org/seleniumhq/selenium/selenium-java/), of course. 
By using [WebDriverManager](https://github.com/bonigarcia/webdrivermanager), there is no need to care for downloading
a [Selenium WebDriver](http://www.seleniumhq.org/docs/03_webdriver.jsp) yourself.

When running [HelloControllerIT.java](src/test/java/schulte/markus/seleniumstandalonechromespringboot/controller/HelloControllerIT.java),
a selenium/standalone-chrome -container is started, while this Spring Boot application is 
launched with a random port on localhost. Now in the test, you can use Selenium-RemoteWebDriver to 
connect to the running Selenium-Hub within the selenium/standalone-chrome -container. 
[Selenium/standalone-chrome-debug](https://hub.docker.com/r/selenium/standalone-chrome-debug/) 
docker-images is used here, so you can access VNC on the running container by accessing the published
port 5900.
