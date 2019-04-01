package readers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadXLSX {

	private String path;

	public ReadXLSX(String p) {
		this.path = p;
	}

	public boolean findString(String s, boolean cs) {
		try {
			FileInputStream fileStream = new FileInputStream(new File(path));
			XSSFWorkbook workbook = null;
			try {
				workbook = new XSSFWorkbook(fileStream);
			} catch (POIXMLException e) {
				System.out.println("ERROR ReadXLSX - POIXMLException (Strict OOXML isn't currently supported, please see bug #57699) - " + this.path);
			}catch (NotOfficeXmlFileException e) {
				System.out.println("ERROR ReadXLSX - NotOfficeXmlFileException (No valid entries or contents found, this is not a valid OOXML) - " + this.path);
			}
			for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
				XSSFSheet sheet = workbook.getSheetAt(i);
				Iterator<Row> rowIt = sheet.iterator();
				while (rowIt.hasNext()) {
					Row row = rowIt.next();
					Iterator<Cell> cellIterator = row.cellIterator();
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						if (cs) {
							if (cell.toString().contains(s)) {
								workbook.close();
								return true;
							}
						} else {
							if (cell.toString().toLowerCase().contains(s.toLowerCase())) {
								workbook.close();
								return true;
							}
						}
					}
				}
				workbook.close();
			}
		} catch (FileNotFoundException e) {
			System.out.println("ERROR ReadXLSX - FileNotFoundException - " + this.path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
