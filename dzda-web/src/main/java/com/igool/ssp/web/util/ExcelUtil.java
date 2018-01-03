package com.igool.ssp.web.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExcelUtil {
	private static Logger LOGGER = LoggerFactory.getLogger(ExcelUtil.class);

	static DecimalFormat numberFormat = new DecimalFormat("###0");
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static List<List<String>> getExcelData(String fileName, InputStream io, int sheetIndex) {
		List<List<String>> dataList = new ArrayList<List<String>>();
		Workbook wbs;

		try {
			if (fileName.endsWith("xls")) {
				wbs = new HSSFWorkbook(io);
			} else {
				wbs = new XSSFWorkbook(io);
			}
			
			Sheet childSheet = wbs.getSheetAt(sheetIndex);
			for(int i = childSheet.getFirstRowNum();i <= childSheet.getLastRowNum();i++){ 	
			    Row row = childSheet.getRow(i);
			    List<String> data = new ArrayList<String>();
			    for(int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++ ){ 
			    	  Cell cell = row.getCell(j);
			    	  data.add(getCellData(cell));
			    } 
				dataList.add(data);
			 } 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
			return null;
		}
		
		return dataList;
	}
	
	public static String getCellData(Cell cell){
		if (cell == null) {
			return "null" ;
		}
		int cellType = cell.getCellType();
		String data = null;
        switch (cellType) { 
        case Cell.CELL_TYPE_FORMULA :
        	data =cell.getCellFormula();
            cell.setCellType(Cell.CELL_TYPE_STRING);
            data = cell.getStringCellValue();  
            if(data!=null){  
            	data = data.toString().replaceAll("#N/A","").trim();  
            } 
        	
            break; 
        case Cell.CELL_TYPE_NUMERIC :
        	if (HSSFDateUtil.isCellDateFormatted(cell)) {
				// 如果是在Date类型，则取得该Cell的Date值
				Date date = cell.getDateCellValue();
				if (date!=null) {
					data = dateFormat.format(date);
				}
			} else {
				data = numberFormat.format(cell.getNumericCellValue());

			}
            break; 
        case Cell.CELL_TYPE_BOOLEAN :
          	   data = String.valueOf(cell.getBooleanCellValue());
               break; 
        case Cell.CELL_TYPE_STRING :
        	  data = cell.getStringCellValue().trim();//去掉头尾空格
              break; 
        case Cell.CELL_TYPE_BLANK :
        	  data = "null";
              break; 
        default : 
        	 data = "null";
             break; 
    }     
		return data;
	}


	public static void createExcel(String tableName, String title, List<String> content, File destFile){
		HSSFWorkbook wb = new HSSFWorkbook();//创建工作薄
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		HSSFSheet sheet = wb.createSheet(tableName);//创建工作表，名称为test
		LOGGER.info("tableName="+tableName);
		//设置标题栏
		int iRow = 0;//行号
		HSSFRow row = sheet.createRow(iRow);
		String[] titleNames = title.split(",");//标题用英文逗号","分隔开
		LOGGER.info("titleLength="+titleNames.length);
		for(int i=0; i<titleNames.length; i++){
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(new HSSFRichTextString(titleNames[i]));
			cell.setCellStyle(style);
		}

		//设置内容
		LOGGER.info("contentListSize="+content.size());
		for(int i=0; i<content.size(); i++){
			HSSFRow contentRow = sheet.createRow(++iRow);
			String contentStr = content.get(i);
			String[] contents = contentStr.split(",");//内容list里面的每一项用英文逗号","分隔开
			
			for(int j=0; j<contents.length; j++){
				HSSFCell cell = contentRow.createCell(j);
				cell.setCellValue(new HSSFRichTextString(contents[j]));
				cell.setCellStyle(style);
			}
		}

		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try{
			wb.write(os);
		}catch(IOException e){
			LOGGER.error(e.getMessage());
			//return null;
		}

		byte[] xls = os.toByteArray();
		OutputStream out = null;
		try {
			out = new FileOutputStream(destFile);
			try {
				out.write(xls);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				LOGGER.error(e.getMessage());
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			LOGGER.error(e1.getMessage());
		}
	}
}
