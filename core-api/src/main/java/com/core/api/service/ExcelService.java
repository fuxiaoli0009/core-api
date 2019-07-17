package com.core.api.service;

import java.io.InputStream;
import java.util.List;

public interface ExcelService {

	public List<List<Object>> getDataFromExcel(InputStream inputStream, String fileName);

}
