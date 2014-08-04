package net.aimeizi.tutorials;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 显示友好的时间
 * 
 * @author 冯靖
 * 
 */
public class RelativeTime {
	
	
	public static String timeAgo(String timeStr) {
		Date date = null;
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			date = format.parse(timeStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}

		long timeStamp = date.getTime();

		Date currentTime = new Date();
		long currentTimeStamp = currentTime.getTime();
		long seconds = (currentTimeStamp - timeStamp) / 1000;

		long minutes = Math.abs(seconds / 60);
		long hours = Math.abs(minutes / 60);
		long days = Math.abs(hours / 24);

		if (seconds <= 15) {
			return "刚刚";
		} else if (seconds < 60) {
			return seconds + "秒前";
		} else if (seconds < 120) {
			return "1分钟前";
		} else if (minutes < 60) {
			return minutes + "分钟前";
		} else if (minutes < 120) {
			return "1小时前";
		} else if (hours < 24) {
			return hours + "小时前";
		} else if (hours < 24 * 2) {
			return "1天前";
		} else if (days < 7) {//一周以内
			return days + "天前";
		} else {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
			String dateString = formatter.format(date);
			return dateString;
		}

	}

	public static void main(String[] args) throws Exception {
		System.out.println(timeAgo("2014/07/31 22:35:59"));
	}

}
