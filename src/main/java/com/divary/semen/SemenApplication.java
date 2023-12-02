package com.divary.semen;

import com.divary.semen.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class SemenApplication implements CommandLineRunner {

	private final ReportService reportService;

	public static void main(String[] args) {
		SpringApplication.run(SemenApplication.class, args);
	}

	/**
	 * Executes the report generation process upon application startup.
	 *
	 * @param args Command-line arguments passed to the application.
	 * @throws Exception An exception that might occur during the execution.
	 */
	@Override
	public void run(String... args) throws Exception {
		reportService.generateReportPenjualan();
	}
}
