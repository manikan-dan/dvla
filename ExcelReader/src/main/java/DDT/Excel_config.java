package DDT;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel_config {
	
	 XSSFWorkbook wb;
	 XSSFSheet sheet1;
	
	  public  Excel_config(String excelPath)
	{
		
		try {
			
			File src = new File(excelPath);
			FileInputStream fis = new FileInputStream(src);
			wb = new XSSFWorkbook(fis);
			System.out.println("Excel file found\t");
		
		} catch (Exception e) {
			
		//	System.out.println(e.getMessage());
		}
	}

	
	public String getData(int sheetNumber,int row,int column)
		{
			sheet1= wb.getSheetAt(sheetNumber);
		//	System.out.println(row+"\t"+column);
			String data = sheet1.getRow(row).getCell(column).getStringCellValue();
			return data;
		}
		
	
	public int getRowCount(int sheetIndex)
	{
		int row = wb.getSheetAt(sheetIndex).getLastRowNum();
		row=row+1;
		return row;
	}
	
}

