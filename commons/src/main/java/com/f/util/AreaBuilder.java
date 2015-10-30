package com.f.util;

import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AreaBuilder {

	public static void main(String[] args) throws Exception{
		Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/f?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true", "root", "");
		ResultSet rs = con.createStatement().executeQuery("select * from area");
		List<Area> list = new ArrayList<Area>(2000);
		while(rs.next()){
			Area area = new Area();
			area.id = rs.getInt("id");
			area.name = rs.getString("name");
			area.fid = rs.getInt("fid");
			list.add(area);
		}
		rs.close();
		con.close();
		Map<Integer,String> map = new HashMap<Integer,String>();
		Map<Integer,List<AreaDto>> pmap = new HashMap<Integer,List<AreaDto>>();
		List<AreaDto> ads = new ArrayList<AreaDto>();
		for(Area area:list){
			map.put(area.getId(),area.getName());
			AreaDto ad = new AreaDto();
			ad.id = area.getId();
			ad.name = area.getName();
			ad.fid = area.fid;
			ads.add(ad);
			if(pmap.get(ad.fid) == null){
				pmap.put(ad.fid,new ArrayList<AreaDto>());
			}
			pmap.get(ad.fid).add(ad);
		}
		ObjectMapper om = new ObjectMapper();
		String str = "if(!window.f){window.f={};}window.f.area_map=";
		str = str + om.writeValueAsString(map);
		AreaDto dto = new AreaDto();
		dto.id = 1;
		dto.name = "中国";
		dto.fid = 0;
		getChildren(dto,pmap);
		str = str + ";\r\n window.f.area_tree="+om.writeValueAsString(dto)+";";
		str = str + "\r\n window.f.area_pmap="+om.writeValueAsString(pmap)+";";
		File file = new File("C:\\Users\\sls-30\\Desktop\\area.js");
		if(!file.exists()){
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file);
		fw.append(str);
		fw.flush();
		fw.close();
	}
	
	@SuppressWarnings("unused")
	private static class AreaDto{
		private Integer id;
		private String name;
		private Integer fid;
		public Integer getFid() {
			return fid;
		}
		public void setFid(Integer fid) {
			this.fid = fid;
		}
		private List<AreaDto> children;
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public List<AreaDto> getChildren() {
			return children;
		}
		public void setChildren(List<AreaDto> children) {
			this.children = children;
		}
	}
	
	private static void getChildren(AreaDto dto,Map<Integer,List<AreaDto>> map){
		if(dto.children == null){
			dto.children = map.get(dto.id);
		}
		if(dto.children != null){
			for(AreaDto ad:dto.children){
				getChildren(ad,map);
			}
		}
	}
	
	private static class Area {
	    private Integer id;

	    private String name;

	    private Integer fid;

	    public Integer getId() {
	        return id;
	    }

	    public String getName() {
	        return name;
	    }

	}
}
