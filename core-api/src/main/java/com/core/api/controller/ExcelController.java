package com.core.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.coyote.http2.Flags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.core.api.enums.FlagEnum;
import com.core.api.service.ExcelService;
import com.core.api.service.RabbitMqService;

@RestController
@RequestMapping(value = "excel")
public class ExcelController {
	
	@Autowired
	private ExcelService excelService;
	
	@Autowired
	private RabbitMqService rabbitMqService;

	@PostMapping(value = "parse")
	@ResponseBody
	public String uploadExcel(HttpServletRequest request) throws Exception {
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = multipartRequest.getFile("filename");
		if (file.isEmpty()) {
			return "文件不能为空";
		}
		
		try {
			List<List<Object>> rowList = excelService.getDataFromExcel(file.getInputStream(), file.getOriginalFilename());
			Map<String, String> map = new HashMap<String, String>();
			//初始化受关注的数据
			for(List<Object> row : rowList) {
				map.put("flag", FlagEnum.INIT_CONCERNED.getCode());
				map.put("code", row.get(0)!=null?row.get(0).toString():"");
				map.put("name", row.get(1)!=null?row.get(1).toString():"");
				map.put("maxValue", row.get(2)!=null?row.get(2).toString():"");
				rabbitMqService.sendMessage(JSON.toJSONString(map));
			}
		} catch (Exception e) {
		}
		return "上传成功";
	}
}
