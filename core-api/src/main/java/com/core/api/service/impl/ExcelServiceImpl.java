package com.core.api.service.impl;

import java.io.InputStream;
import java.util.List;
import org.springframework.stereotype.Service;
import com.core.api.service.ExcelService;
import com.core.api.utils.HandleFile;

@Service
public class ExcelServiceImpl implements ExcelService {
	
	@Override
	public List<List<Object>> getDataFromExcel(InputStream inputStream, String fileName) {
		
		try {
			return HandleFile.parseExcel(inputStream, fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
