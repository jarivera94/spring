package com.helio4.bancol.avaluos.dominio;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
	
	public static int getDiferenciaEnAnos(Date primera, Date ultima) {
		Calendar a = getCalendar(primera);
		Calendar b = getCalendar(ultima);
		int diff = b.get(Calendar.YEAR) - a.get(Calendar.YEAR);
		if (a.get(Calendar.MONTH) > b.get(Calendar.MONTH) || 
			(a.get(Calendar.MONTH) == b.get(Calendar.MONTH) && a.get(Calendar.DATE) > b.get(Calendar.DATE))) {
			diff--;
		}
		return diff;
	}
	
	public static int getDiferenciaEnMeses(Date primera, Date ultima) {
		Calendar a = getCalendar(primera);
		Calendar b = getCalendar(ultima);
		int diffYears = b.get(Calendar.YEAR) - a.get(Calendar.YEAR);
		int diff = diffYears * 12 + b.get(Calendar.MONTH) - a.get(Calendar.MONTH);
		if (a.get(Calendar.DAY_OF_MONTH) > b.get(Calendar.DAY_OF_MONTH) || 
			(a.get(Calendar.DAY_OF_MONTH) == b.get(Calendar.DAY_OF_MONTH) && a.get(Calendar.DATE) > b.get(Calendar.DATE))) {
			diff--;
		}
		return diff;
	}

	public static Calendar getCalendar(Date date) {
		Calendar cal = Calendar.getInstance(Locale.US);
		cal.setTime(date);
		return cal;
	}

}
