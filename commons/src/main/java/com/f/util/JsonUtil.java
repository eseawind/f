package com.f.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import framework.exception.ApplicationException;

public class JsonUtil {

	private static final ObjectMapper om = new ObjectMapper();
	
	public static String marshal(Object obj){
		try {
			return om.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			throw new ApplicationException("json marshal error",e);
		}
	}
	
	public static <T>T unmarshal(String json,Class<T> clazz){
		try {
			return om.readValue(json, clazz);
		} catch (Exception e) {
			throw new ApplicationException("json unmarshal error",e);
		}
	}
	
}
