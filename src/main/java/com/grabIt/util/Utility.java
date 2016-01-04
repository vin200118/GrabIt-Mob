package com.grabIt.util;

import java.util.UUID;

public class Utility {
	
	public static String getUUID(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

}
