package net.aimeizi.tutorials.jodatime;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.DateTimeZone;
import org.joda.time.Duration;
import org.joda.time.Interval;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;

/**
 * joda-time
 * @author 冯靖
 *
 */
public class Jodatime {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		getTime();
		getTimeInfo();
		convertJDK();
		date();
	}
	
	private static void getTime(){
		
		//方法一：取系统点间
		DateTime dt1 = new DateTime();
		
		print("方法一：取系统点间",dt1);
		
		//方法二：通过java.util.Date对象生成
		DateTime dt2 = new DateTime(new Date());

		print("方法二：通过java.util.Date对象生成",dt2);
		
		//方法三：指定年月日点分秒生成(参数依次是：年,月,日,时,分,秒,毫秒)
		DateTime dt3 = new DateTime(2012, 5, 20, 13, 14, 0, 0);
		
		print("方法三：指定年月日点分秒生成(参数依次是：年,月,日,时,分,秒,毫秒)",dt3);
		
		//方法四：ISO8601形式生成
		DateTime dt4 = new DateTime("2012-05-20");
		
		print("方法四：ISO8601形式生成",dt4);
		
		DateTime dt5 = new DateTime("2012-05-20T13:14:00");

		print("方法四：ISO8601形式生成",dt5);
		
		//只需要年月日的时候
		LocalDate localDate = new LocalDate(2009, 9, 6);// September 6, 2009

		System.out.println(localDate.toString("YYYY-MM-dd"));
		
		//只需要时分秒毫秒的时候
		LocalTime localTime = new LocalTime(13, 30, 26, 0);// 1:30:26PM
		
		System.out.println(localTime.toString("hh:mm:ss.SSSa"));
	}
	
	
	private static void getTimeInfo(){
		
		System.out.println("-------------获取当前时间-----------------");
		
		String weekStr="";
		
		DateTime dt = new DateTime();
		//年
		int year = dt.getYear();
		//月
		int month = dt.getMonthOfYear();
		//日
		int day = dt.getDayOfMonth();
		//星期
		int week = dt.getDayOfWeek();
		//点
		int hour = dt.getHourOfDay();
		//分
		int min = dt.getMinuteOfHour();
		//秒
		int sec = dt.getSecondOfMinute();
		//毫秒
		int msec = dt.getMillisOfSecond();
		
		//星期
		switch(week) {
		case DateTimeConstants.SUNDAY:
			weekStr = "星期日";
			System.out.println(weekStr);
			break;
		case DateTimeConstants.MONDAY:
			weekStr = "星期一";
			System.out.println(weekStr);
			break;
		case DateTimeConstants.TUESDAY:
			weekStr = "星期二";
			System.out.println(weekStr);
			break;
		case DateTimeConstants.WEDNESDAY:
			weekStr = "星期三";
			System.out.println(weekStr);
			break;
		case DateTimeConstants.THURSDAY:
			weekStr = "星期四";
			System.out.println(weekStr);
			break;
		case DateTimeConstants.FRIDAY:
			weekStr = "星期五";
			System.out.println(weekStr);
			break;
		case DateTimeConstants.SATURDAY:
			weekStr = "星期六";
			System.out.println(weekStr);
			break;
		}
		
		System.out.println(year + "/" + month + "/" + day + " " + hour + ":" + min + ":" + sec + ":" + msec + " " + weekStr);
		
	}
	
	/**
	 * 与jdk的时间转换
	 */
	public static void convertJDK(){
		
		System.out.println("-------------与jdk的时间转换-----------------");
		
		DateTime dt = new DateTime();

		//转换成java.util.Date对象
		
		Date d1 = new Date(dt.getMillis());
		
		Date d2 = dt.toDate();
		
		//转换成java.util.Calendar对象
		Calendar c1 = Calendar.getInstance();
		c1.setTimeInMillis(dt.getMillis());
		
		Calendar c2 = dt.toCalendar(Locale.getDefault());

	}
	
	/**
	 * 日期推算
	 */
	public static void date(){
		
		System.out.println("-------------日期推算-----------------");
		
		DateTime dt = new DateTime();
		
		print("今天",dt);
		
		//昨天
		DateTime yesterday = dt.minusDays(1);
		
		print("昨天",yesterday);
		
		//明天
		DateTime tomorrow = dt.plusDays(1);		
		
		print("明天",tomorrow);
		
		//1个月前
		DateTime before1month = dt.minusMonths(1);		
		
		print("1个月前",before1month);
		
		//3个月后
		DateTime after3month = dt.plusMonths(3);		
		
		print("3个月后",after3month);
		
		//2年前
		DateTime before2year = dt.minusYears(2);		
		
		print("2年前",before2year);
		
		//5年后
		DateTime after5year = dt.plusYears(5);
		
		print("5年后",after5year);
		
		//特殊日期处理

		//月末日期	
		DateTime lastday = dt.dayOfMonth().withMaximumValue();

		print("月末日期",lastday);
		
		//90天后那周的周一
		DateTime firstday = dt.plusDays(90).dayOfWeek().withMinimumValue();
		
		print("90天后那周的周一",firstday);
		
		//时区
		System.out.println("-------------时区-----------------");
		//默认设置为日本时间
		DateTimeZone.setDefault(DateTimeZone.forID("Asia/Tokyo"));
		
		DateTime dt1 = new DateTime();
		
		print("默认设置为日本时间",dt1);
		
		//伦敦时间
		DateTime dt2 = new DateTime(DateTimeZone.forID("Europe/London"));

		print("伦敦时间",dt2);
		
		//计算区间
		
		System.out.println("-------------计算区间-----------------");
		
		DateTime begin = new DateTime("2012-02-01");
		
		DateTime end = new DateTime("2012-05-01");

		//计算区间毫秒数
		Duration d = new Duration(begin, end);
		long time = d.getMillis();
		
		System.out.println("计算区间毫秒数:"+time);
		
		//计算区间天数
		Period p = new Period(begin, end, PeriodType.days());
		int days = p.getDays();

		System.out.println("计算区间天数:"+days);
		
		//计算特定日期是否在该区间内
		Interval i = new Interval(begin, end);
		boolean contained = i.contains(new DateTime("2012-03-01"));
		
		
		//日期比较
		System.out.println("-------------日期比较-----------------");
		
		DateTime d1 = new DateTime("2012-02-01");
		DateTime d2 = new DateTime("2012-05-01");

		//和系统时间比
		boolean b1 = d1.isAfterNow();//将来 以后
		System.out.println("["+b1+"]"+d1.toString("yyyy-MM-dd")+"在"+dt.toString("yyyy-MM-dd")+"之前");
		boolean b2 = d1.isBeforeNow();//以前 过去
		System.out.println("["+b2+"]"+d1.toString("yyyy-MM-dd")+"在"+dt.toString("yyyy-MM-dd")+"之前");
		boolean b3 = d1.isEqualNow();//与现在时间相同
		System.out.println("["+b3+"]"+d1.toString("yyyy-MM-dd")+"与"+dt.toString("yyyy-MM-dd")+"相同");
		
		
		//和其他日期比
		boolean f1 = d1.isAfter(d2);
		boolean f2 = d1.isBefore(d2);
		boolean f3 = d1.isEqual(d2);
		
		
		//格式化输出
		
		System.out.println("-------------格式化输出-----------------");
		
		DateTime dateTime = new DateTime();

		String s1 = dateTime.toString("yyyy/MM/dd hh:mm:ss.SSSa");
		String s2 = dateTime.toString("yyyy-MM-dd HH:mm:ss");
		String s3 = dateTime.toString("EEEE dd MMMM, yyyy HH:mm:ssa");
		String s4 = dateTime.toString("yyyy/MM/dd HH:mm ZZZZ");
		String s5 = dateTime.toString("yyyy/MM/dd HH:mm Z");
		
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);
		System.out.println(s4);
		System.out.println(s5);
	}
	
	
	private static void print(String tag,DateTime dateTime){
		System.out.println(tag + ":" + dateTime.toString("yyyy-MM-dd HH:mm:ss"));
	}
	
}
