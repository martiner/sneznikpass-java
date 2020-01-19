package cz.geek.sneznikpass;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import lombok.NonNull;

class JodaSupport {

	private static final DateTimeFormatter FMT = DateTimeFormat.forPattern("ddMMyyyy");

	static String format(@NonNull LocalDate date) {
		return FMT.print(date);
	}
}
