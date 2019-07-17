package com.core.api.service.impl;

import java.io.InputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.core.api.service.ExcelService;
import com.core.api.utils.HandleFile;

@Service
public class ExcelServiceImpl implements ExcelService {
	
	private static final Logger logger = LoggerFactory.getLogger(ExcelServiceImpl.class);
	
	@Override
	public List<List<Object>> getDataFromExcel(InputStream inputStream, String fileName) {
		
		try {
			return HandleFile.parseExcel(inputStream, fileName);
		} catch (Exception e) {
			logger.error("excel解析异常", e);
		}
		return null;
	}
	
}
