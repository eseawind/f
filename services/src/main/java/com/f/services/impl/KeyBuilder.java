package com.f.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.f.dao.ext.KeyMapperExt;

import framework.key.DefaultKeyBuilder;

public class KeyBuilder extends DefaultKeyBuilder{
	
	@Autowired
	private KeyMapperExt ext;

	@Override
	public long getRangeKey(String table){
		Integer skip = this.getSkips().get(table);
		skip = (skip == null?this.getSkip():skip);
		int i = ext.updateKey(table, skip);
		if(i == 0){
			i = ext.insertKey(table);
			if(i == 0){
				throw new RuntimeException("insert prikey error tablename="+table);
			}else{
				i = ext.updateKey(table, skip);
				if(i == 0){
					throw new RuntimeException("update prikey error tablename="+table);
				}
			}
		}
		long key = ext.selKey(table);
		if(key == 0){
			throw new RuntimeException("sel prikey error tablename="+table);
		}
		return key;
	} 
}
