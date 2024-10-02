package com.example.maxnumber;

import com.example.maxnumber.services.FindMaxService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class FindMaxNumberApplicationTests {

	@Autowired
	private FindMaxService findMaxService;

	@Test
	public void testFindMax() throws IOException {
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Test Sheet");
		Row row = sheet.createRow(0);
		row.createCell(0).setCellValue(10);
		row.createCell(1).setCellValue(2);
		row.createCell(2).setCellValue(4);
		row.createCell(3).setCellValue(8);
		row.createCell(4).setCellValue(15);
		row.createCell(5).setCellValue(6);
		row.createCell(6).setCellValue(3);
		row.createCell(7).setCellValue(20);
		row.createCell(8).setCellValue(1);

		File tempFile = File.createTempFile("testFile", ".xlsx");
		try (FileOutputStream fileOut = new FileOutputStream(tempFile)) {
			workbook.write(fileOut);
		}

		Integer result = findMaxService.findMax(tempFile.getAbsolutePath(), 3);
		assertThat(result).isEqualTo(10);

		Integer result2 = findMaxService.findMax(tempFile.getAbsolutePath(), 1);
		assertThat(result2).isEqualTo(20);

		Integer result3 = findMaxService.findMax(tempFile.getAbsolutePath(), 10);
		assertThat(result3).isNull();
	}


}
