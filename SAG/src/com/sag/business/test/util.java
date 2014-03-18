package com.sag.business.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class util {

	/**
	 * Convertir une chaine de caractère en date
	 * 
	 * @param dateString
	 *            String format ("dd/MM/yyyy")
	 * @return date : type java.util.Date;
	 */
	public static Date string2Date(String dateString) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date;
		try {
			date = formatter.parse(dateString);
			return date;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * Convertir une date en chaine de caractère
	 * 
	 * @param date
	 *            : type java.util.Date;
	 * @return une date en type String format ("dd/MM/yyyy")
	 */
	public static String date2String(Date date) {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		return format.format(date);
	}

}
