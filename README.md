# Let's have a lunch!

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/8a7ef2521d7d43d98e4f8fb1a6783853)](https://www.codacy.com/app/gml-jackson/LetsHaveALunch?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=jacksn/LetsHaveALunch&amp;utm_campaign=Badge_Grade)
[![Dependency Status](https://dependencyci.com/github/jacksn/LetsHaveALunch/badge)](https://dependencyci.com/github/jacksn/LetsHaveALunch)
[![Build Status](https://travis-ci.org/jacksn/LetsHaveALunch.svg?branch=master)](https://travis-ci.org/jacksn/LetsHaveALunch)

### <a href="http://javawebinar.ru/topjava/">TopJava</a> graduation project.

A voting system for deciding where to have lunch.

 * 2 types of users: admin and regular users
 * Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
 * Menu changes each day (admins do the updates)
 * Users can vote on which restaurant they want to have lunch at
 * Only one vote counted per user
 * If user votes again the same day:
    - If it is before 11:00 we assume that he changed his mind.
    - If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides new menu each day.

Used tools, libraries and frameworks:
 <a href="http://maven.apache.org/">Maven</a>,
 <a href="http://projects.spring.io/spring-boot/">Spring Boot</a>,
 <a href="http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html">Spring MVC</a>,
 <a href="http://projects.spring.io/spring-security/">Spring Security</a>,
 <a href="http://projects.spring.io/spring-data-jpa/">Spring Data JPA</a>,
 <a href="http://spring.io/blog/2014/05/07/preview-spring-security-test-method-security">Spring Security Test</a>,
 <a href="http://hibernate.org/orm/">Hibernate ORM</a>,
 <a href="http://hibernate.org/validator/">Hibernate Validator</a>,
 <a href="http://www.slf4j.org/">SLF4J</a>,
 <a href="https://github.com/FasterXML/jackson">Json Jackson</a>,
 <a href="http://ehcache.org">Ehcache</a>,
 <a href="http://hsqldb.org//">HSQLDB</a>,
 <a href="http://junit.org/">JUnit</a>,
 <a href="http://hamcrest.org/JavaHamcrest/">Hamcrest</a>,
 <a href="http://tomcat.apache.org/">Apache Tomcat</a>.
