package com.example.maxnumber.controllers;

import com.example.maxnumber.services.FindMaxService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class MainController {

    private final FindMaxService findMaxService;

    public MainController(FindMaxService findMaxService) {
        this.findMaxService = findMaxService;
    }

    @Operation(summary = "Get an N max number", description = "Get an N number of max numbers are written in sheet")
    @GetMapping("/v1/maxvalue")
    public ResponseEntity<Integer> getMaxValueFromExcelFile(
            @Parameter(description = "Path to the file as string", required = true)
            @RequestParam String filePath,
            @Parameter(description = "The N-th maximum number from the end in the sheet", required = true)
            @RequestParam int n
    ) {
        try {
            int maxNumberAtN = findMaxService.findMax(filePath, n);
            return ResponseEntity.ok(maxNumberAtN);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
