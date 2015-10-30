package com.f.commons;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class OrdersNumSequence {

	public static String builderOrdersNum(String channel){
		SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmss");
		StringBuilder sb = new StringBuilder(channel);
		sb.append(format.format(new Date()));
		sb.append((int)Math.floor(Math.random()*100000));
		return sb.toString();
	}
}
