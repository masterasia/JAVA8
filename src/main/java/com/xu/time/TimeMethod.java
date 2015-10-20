package com.xu.time;

import java.time.*;
/**
 * Created by robert on 2015/10/19.
 * java 8 更新了时间包，使时间参数更灵活也更安全
 * 和原有date进行比较
 */
public class TimeMethod {
    //基本类  LocalDateTime 用以获得当前标准时间
    public static LocalDateTime getLocalDateTime(){
        //直接取得当前日期+时间
        LocalDateTime localDateTime = LocalDateTime.now();
        //展示格式 年-月-日T时：分：秒.毫秒
        System.out.println(localDateTime.toString());
        System.out.println(localDateTime.MAX);
        System.out.println(localDateTime.MIN);
        //基本类可以拓展至包内其他类   可以做运算和比较
        return localDateTime;
    }

    //基本类 LocalDate 用以获取当前标准日期
    public static LocalDate getLocalDate(){
        //直接获取当前日期
        LocalDate localDate = LocalDate.now();
        //展示格式  年-月-日
        System.out.println(localDate.toString());
        //可展示最大日期  999999999年12月31日
        System.out.println(localDate.MAX);
        //可展示最小日期 公元前999999999年1月1日
        System.out.println(localDate.MIN);
        //当前日期开始时间
        System.out.println(localDate.atStartOfDay());
        //闰年直接判断
        System.out.println(localDate.isLeapYear());
        //基本类可以拓展至包内其他类   可以做运算和比较
        return localDate;
    }

    //基本类 LocalTime 用以获取当前标准时间
    public static LocalTime getLocalTime(){
        //直接获取当前时间
        LocalTime localTime = LocalTime.now();
        //展示格式  时：分：秒.毫秒
        System.out.println(localTime.toString());
        //可展示最大时间  23：59：59.999999999
        System.out.println(localTime.MAX);
        //可展示最小时间  00：00
        System.out.println(localTime.MIN);
        //午夜时间  00:00
        System.out.println(localTime.MIDNIGHT);
        //中午时间  12：00
        System.out.println(localTime.NOON);
        //当前时间转化为秒数
        System.out.println(localTime.toSecondOfDay());
        //当前时间转化为毫秒数
        System.out.println(localTime.toNanoOfDay());
        //基本类可以拓展至包内其他类   可以做运算和比较
        return localTime;
    }

    //基本标准时间 Instant
    public static Instant getInstant(){
        //直接获取当前UTC时间
        Instant instant = Instant.now();
        //展示格式  年-月-日T时：分：秒.毫秒时区
        System.out.println(instant.toString());
        //偏移0小时
        System.out.println(instant.atOffset(ZoneOffset.ofHours(0)));
        //偏移 -4小时
        System.out.println(instant.atOffset(ZoneOffset.ofHours(-4)));
        //偏移 8小时（当前北京时间）
        System.out.println(instant.atOffset(ZoneOffset.ofHours(8)));
        //时区
        System.out.println(instant.atZone(ZoneId.of(ZoneId.SHORT_IDS.get("CTT"))));
        System.out.println(instant.getEpochSecond());
        System.out.println(instant.getNano());
        System.out.println(instant.toEpochMilli());
        System.out.println(Instant.EPOCH.toString());
        System.out.println(Instant.MAX);
        System.out.println(Instant.MIN);
        return instant;
    }

    //年月日的独立示例
    public static void getDate(){
        //当前年份
        Year year = Year.now();

        System.out.println(year.toString());
        //当前 年-月
        YearMonth yearMonth = YearMonth.now();

        System.out.println(yearMonth.toString());
        //当前 月-日
        MonthDay monthDay = MonthDay.now();

        System.out.println(monthDay.toString());

        Month month = Month.SEPTEMBER;

        System.out.println(month.toString());

        DayOfWeek dayOfWeek = DayOfWeek.WEDNESDAY;

        System.out.println(dayOfWeek);
        //赋值原则，直接赋予本身数据
        System.out.println(monthDay.adjustInto(LocalDateTime.of(2011, 1, 1, 0, 1, 2)));
    }

    public static void getOffset(){
        OffsetDateTime offsetDateTime = OffsetDateTime.now();

        System.out.println(offsetDateTime.toString());
        System.out.println(offsetDateTime.getOffset());
        System.out.println(offsetDateTime.toInstant().toString());

        OffsetTime offsetTime = OffsetTime.now();

        System.out.println(offsetTime.toString());
        System.out.println(offsetTime.getOffset());

        ZoneOffset zoneOffset = ZoneOffset.UTC;
//        zoneOffset = offsetDateTime.getOffset();
        System.out.println(zoneOffset.getRules());
        System.out.println(zoneOffset.toString());
        System.out.println(zoneOffset.getId());

        ZoneId zoneId = ZoneId.systemDefault();

        System.out.println(zoneId.toString());


    }

    public static void getTime(){
        LocalDateTime localDateTime = LocalDateTime.now();
        Duration duration = Duration.ZERO;
        System.out.println(localDateTime.toString());
        System.out.println(duration.addTo(localDateTime));
        System.out.println(duration.ofDays(1).addTo(localDateTime));
        System.out.println(duration.ofHours(24).addTo(localDateTime));
        System.out.println(duration.plusDays(1).addTo(localDateTime));

        Period period = Period.ZERO;
        System.out.println(period.addTo(localDateTime));
        System.out.println(period.ofDays(1).addTo(localDateTime));
        System.out.println(period.of(1, 0, 1).addTo(localDateTime));
        System.out.println(period.plusDays(1).addTo(localDateTime));


    }

    public static void getClock(){
        System.out.println(LocalDateTime.now(Clock.systemDefaultZone()));
        System.out.println(LocalDateTime.now(Clock.systemUTC()));
        System.out.println(Clock.systemUTC());
    }

    public static void main(String[] args){
        int x;

    }
}
