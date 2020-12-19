package project.five.pos.db;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Day {

	public Day() {}
	
	// 오늘 날짜
	public String TodayYmd() {
		SimpleDateFormat simple = new SimpleDateFormat("yy/MM/dd");
		Date now = new Date();
		return simple.format(now);
	}
	
	// 한달전 날짜
	public String AmonthAgoYmd() {
		LocalDate amonth_ago = LocalDate.now().minusDays(30);
		return amonth_ago.format(DateTimeFormatter.ofPattern("yy/MM/dd")).toString();
	}

	public static void main(String[] args) {
		System.out.println(new Day().AmonthAgoYmd());
	}
}
