package com.xu.test.time;

import com.xu.time.TimeMethod;
import org.junit.Test;

/**
 * Created by robert on 2015/10/19.
 * 用新老版本时间参数进行比对
 */
public class TimeTest {

    @Test
    public void testLocalDateTime(){
        TimeMethod.getLocalDateTime();
    }

    @Test
    public void testLocalDate(){
        TimeMethod.getLocalDate();
    }

    @Test
    public void testLocalTime(){
        TimeMethod.getLocalTime();
    }

    @Test
    public void testInstant(){
        TimeMethod.getInstant();
    }
}
