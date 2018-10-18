package com.boot.contract;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RunWith(SpringRunner.class)
public class LocalDateTimeFormatterTest {
    @Test
    public void test(){
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        LocalDateTime.parse("2018-10-18 13:32:00",formatter);
        log.info("성공");
    }
}
