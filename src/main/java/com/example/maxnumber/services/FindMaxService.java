package com.example.maxnumber.services;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.PriorityQueue;

@Service
public class FindMaxService {

    public Integer findMax(String filePath, int n) throws IOException {

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int sheetIndex = 0;

        try (
                FileInputStream fis = new FileInputStream(filePath);
                Workbook workbook = new XSSFWorkbook(fis)
        ) {
            Sheet sheet = workbook.getSheetAt(sheetIndex);

            for (Row row : sheet) {
                for (Cell cell : row) {
                    if (cell.getCellType() == CellType.NUMERIC) {
                        int number = (int) cell.getNumericCellValue();
                        if (minHeap.size() < n) {
                            minHeap.offer(number);
                        } else if (number > minHeap.peek()) {
                            minHeap.poll();
                            minHeap.offer(number);
                        }
                    }
                }
            }
        }

        return minHeap.size() == n ? minHeap.peek() : null;
    }
}
