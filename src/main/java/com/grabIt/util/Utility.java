package com.grabIt.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;

public class Utility {
	
	public static String getUUID(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	public static String getRandomAlphNumberic(){
		return RandomStringUtils.random(8,true,true);
	}
	public static void main(String args[]){
		System.out.println(getSqlTimeStampDate("19-04-2015"));
		
		Timestamp timestamp = getSqlTimeStampDate("19-04-2015");
		System.out.println(timestamp);
	}
	
	public static Timestamp getSqlTimeStampDate(String date){
		Date simpleDate = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		try {
			simpleDate = dateFormat.parse(date);
		} catch (ParseException e) {
			simpleDate = new Date();
		}
		return new Timestamp(simpleDate.getTime());
	}

}
