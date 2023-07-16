package com.lawencon.lmssanto.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

	public static LocalDateTime parseStringToDate(String date) {
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		final LocalDateTime dateInput = LocalDateTime.parse(date,formatter);
		return dateInput;
	}
	
}
