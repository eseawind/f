package com.f.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.f.commons.Constants;
import com.f.commons.User;

import framework.exception.BusinessException;
import framework.web.ResBo;
import framework.web.auth.Channel;
import framework.web.session.ISession;

@Controller
@RequestMapping("file")
public class FileController implements InitializingBean{
	
	@Autowired
	private ISession session;
	
	@Value("${upload.path}")
	private String path;
	@Value("${upload.single.max}")
	private int singlebytes = 1024*1024;
	
	@Channel(Constants.B)
	@RequestMapping("bupload.htm")
	@ResponseBody
	public ResBo<?> fileUpload(@RequestParam MultipartFile[] files){
		User user = (User) session.get(Constants.USERINFO);
		ResBo<List<String>> resBo = new ResBo<List<String>>();
		String fileDir = fileDirBuilder(user.getId());
		File dir = new File(path + fileDir);
		if(!dir.exists()){
			dir.mkdirs();
		}
		List<String> paths = new ArrayList<String>();
		int count = 0;
		int errCount = 0;
		StringBuilder sb = new StringBuilder();
		for(MultipartFile file:files){
			if(!file.isEmpty()){
				count++;
				if(file.getBytes()){
					
				}
				String filename = fileNameBuilder();
				File f = new File(dir,filename);
				f.deleteOnExit();
				try {
					file.transferTo(f);
					paths.add(fileDir + filename);
				} catch (Exception e) {
					errCount++;
					resBo.setSuccess(false);
				}
			}
		}
		if(!resBo.isSuccess()){
			resBo.setErrCode(10);
			resBo.setErrMsg(BusinessException.getMessage(10L, count, errCount));
		}
		resBo.setResult(paths);
		return resBo;
	}
	
	private String fileDirBuilder(long merchantId){
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		StringBuilder sb = new StringBuilder("/");
		sb.append(String.valueOf(merchantId));
		sb.append("/");
		sb.append(format.format(new Date()));
		sb.append("/");
		return sb.toString();
	}
	
	private String fileNameBuilder(){
		return UUID.randomUUID().toString()+".jpg";
	}

	public void afterPropertiesSet() throws Exception {
		path = path.trim();
		if(path.endsWith("/")){
			path = path.substring(0, path.length() - 1);
		}
	}
}
