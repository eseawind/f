package com.f.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@SuppressWarnings("rawtypes")
public class MapUtil {

	private Map map;
	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public MapUtil(Map map){
		this.map = map;
	}
	
	public String getString(Object key,String def){
		Object value = map.get(key);
		if(value == null){
			return def;
		}
		return value.toString();
	}
	
	public String getString(Object key){
		return getString(key,null);
	}
	
	public Long getLong(Object key,Long def){
		Object value = map.get(key);
		if(value instanceof Long){
			return (Long) value;
		}
		return def;
	}
	
	public Long getLong(Object key){
		return getLong(key,null);
	}
	
	public Integer getInteger(Object key,Integer def){
		Object value = map.get(key);
		if(value instanceof Integer){
			return (Integer) value;
		}
		return def;
	}
	
	public Integer getInteger(Object key){
		return getInteger(key,null);
	}
	
	public Date getDate(Object key,Date def){
		Object value = map.get(key);
		if(value instanceof Date){
			return (Date) value;
		}
		return def;
	} 
	
	public Date getDate(Object key){
		return getDate(key,null);
	}
	
	public String getDateFormat(Object key,String format,String def){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = getDate(key);
		if(date == null){
			return def;
		}
		return sdf.format(date);
	}
	
	public String getDateFormat(Object key,String format){
		return getDateFormat(key,format,null);
	}
	
	public String getDateFormat(Object key){
		return getDateFormat(key,"yyyy-MM-dd HH:mm:ss");
	}
	
	public Double getDouble(Object key,Double def){
		Object value = map.get(key);
		if(value instanceof Double){
			return (Double) value;
		}
		return def;
	}
	
	public Double getDouble(Object key){
		return getDouble(key,null);
	}
	
	public Float getFloat(Object key,Float def){
		Object value = map.get(key);
		if(value instanceof Float){
			return (Float) value;
		}
		return def;
	}
	
	public Float getFloat(Object key){
		return getFloat(key,null);
	}
	
	public BigDecimal getBigDecimal(Object key,BigDecimal def){
		Object value = map.get(key);
		if(value instanceof BigDecimal){
			return (BigDecimal) value;
		}
		return def;
	}
	
	public BigDecimal getBigDecimal(Object key){
		return getBigDecimal(key,null);
	}
	
}
