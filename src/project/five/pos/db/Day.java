package project.five.pos.db;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Day {

	public Day() {}
	
	// ���� ��¥
	public String TodayYmd() {
		SimpleDateFormat simple = new SimpleDateFormat("yy/MM/dd");
		Date now = new Date();
		return simple.format(now);
	}
	
	// �Ѵ��� ��¥
	public String AmonthAgoYmd() {
		LocalDate amonth_ago = LocalDate.now().minusDays(30);
		return amonth_ago.format(DateTimeFormatter.ofPattern("yy/MM/dd")).toString();
	}

	public static void main(String[] args) {
		System.out.println(new Day().AmonthAgoYmd());
	}
}
