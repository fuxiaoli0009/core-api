package com.core.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.core.api.enums.FlagTypeEnum;
import com.core.api.service.ExcelService;
import com.core.api.service.RabbitMqService;

@RestController
@RequestMapping(value = "excel")
public class ExcelController {
	
	private static final Logger logger = LoggerFactory.getLogger(ExcelController.class);
	
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
				if(row.get(0)!=null && row.get(1)!=null) {
					map.put("type", row.get(0)!=null?row.get(0).toString():"");
					map.put("code", row.get(1)!=null?row.get(1).toString().replace("\"", ""):"");
					map.put("name", row.get(2)!=null?row.get(2).toString().replace(" ", ""):"");
					map.put("maxValue", row.get(3)!=null?row.get(3).toString():"");
					map.put("flag", FlagTypeEnum.INIT_CONCERNED.getCode());
					rabbitMqService.sendMessage(JSON.toJSONString(map));
					logger.info("code:{}成功发送mq", row.get(1).toString());
				}
			}
		} catch (Exception e) {
			logger.error("发送mq异常", e);
			return "处理异常,请修改数据后重新上传";
		}
		return "处理成功";
	}
}
