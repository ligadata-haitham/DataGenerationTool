package com.ligadata.dataGenerationTool;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class GenerateRecord {

	public static String GenerateHit(HashMap<String, String> record, String Delimiter) {
		String hit = "";
		// Get a set of the entries
		Set set = record.entrySet();
		// Get an iterator
		Iterator i = set.iterator();
		// Display elements
		while (i.hasNext()) {
			Map.Entry line = (Map.Entry) i.next();
			hit = hit + line.getKey() + Delimiter + line.getValue() + Delimiter;
			// System.out.print(line.getKey() + ": ");
			// System.out.println(line.getValue());
		}
		return hit;

	}

	// don't forget try catch
	public static String GenerateRandomDateBetween(String startDate1,
			String endDate1) throws ParseException {

		DateFormat format = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ss.SSSSSSz", Locale.ENGLISH);
		Date startDate = format.parse(startDate1);
		Date endDate = format.parse(endDate1);

		long startTimeStamp = startDate.getTime();
		long endTimeStamp = endDate.getTime();

		long range = endTimeStamp - startTimeStamp;
		double result = (Math.random() * range) + startTimeStamp; // startDateCalendar.getTimeInMillis(),
																	// endDateCalendar.getTimeInMillis());

		Date d = new Date((long) result);

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSz");
		String resultDateString = df.format(d);

		return resultDateString;

	}

}
