package project.five.pos.db;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Day {

	public Day() {}
	
	// 오늘 날짜
	public String TodayYmdD() {
		SimpleDateFormat simple = new SimpleDateFormat("yy/MM/dd");
		Date now = new Date();
		return simple.format(now);
	}
	
	public String TodayYmdT(LocalDateTime today) {
		return today.format(DateTimeFormatter.ofPattern("yy/MM/dd kk:mm:ss")).toString();
	}
	
	
	// 한달전 날짜
	public String AmonthAgoYmdD() {
		LocalDate amonth_ago = LocalDate.now().minusDays(30);
		return amonth_ago.format(DateTimeFormatter.ofPattern("yy/MM/dd")).toString();
	}

	public static void main(String[] args) {
		LocalDateTime today2 =LocalDateTime.now(ZoneId.of("Asia/Seoul"));
		System.out.println(new Day().TodayYmdT(today2));
	}
}
