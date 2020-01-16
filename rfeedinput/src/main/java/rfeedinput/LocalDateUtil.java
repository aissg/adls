package rfeedinput;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class LocalDateUtil {

	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
	private static String START_DATE = "21.03.2007";
	private static String END_DATE = "26.03.2018";
	
	public static List<String> getAllWedensdays(LocalDate startDate, LocalDate endDate)
	{
		List<LocalDate> allDates = new ArrayList<LocalDate>();
		
		LocalDate incrementingDate = startDate;
		
		while (!incrementingDate.isAfter(endDate)) 
		{
		    allDates.add(incrementingDate);
		    incrementingDate = incrementingDate.plusDays(7);
		}
		
		return allDates.stream().map(e -> e.format(formatter)).collect(Collectors.toList());
	}
	
	public static List<String> getAllWedensdaysSequentialListFrom2007To2018()
	{
		LocalDate startDate = LocalDate.parse(START_DATE, formatter);
		LocalDate endDate = LocalDate.parse(END_DATE, formatter);
		return getAllWedensdays(startDate, endDate);
	}
	
	public static Map<Integer, String> getAllWedensdaysSequentialMapFrom2007To2018()
	{
		List<String> allWedensdays = getAllWedensdaysSequentialListFrom2007To2018();
		AtomicInteger i = new AtomicInteger(0);
		Map<Integer, String> result = allWedensdays.stream().collect(Collectors.toMap(key -> i.incrementAndGet(), value -> value));
		return result;
	}
}
