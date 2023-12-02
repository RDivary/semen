package com.divary.semen.service;

import com.divary.semen.repository.OrderRepository;
import com.opencsv.CSVWriter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.List;

/**
 * The ReportServiceImpl class implements the ReportService interface and manages report generation related operations.
 */
@Service
@Slf4j
@AllArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final OrderRepository orderRepository;

    @Value("${file-path-csv}")
    private String filePath;

    @Value("${max-get-data-per-batch}")
    private int maxGetDataPerBatch;

    /**
     * Generates a report and writes it to a CSV file.
     */
    @Override
    public void generateReportPenjualan() {
        try (CSVWriter writer = new CSVWriter(new FileWriter(String.format(filePath, LocalDateTime.now())))) {

            Limit limit = Limit.of(maxGetDataPerBatch);

            List<Object> reportPenjualan = orderRepository.getReportPenjualan(limit);

            if (reportPenjualan.isEmpty()) {
                log.warn("data is empty");
                return;
            }

            log.info("start generate report");

            String[] header = new String[]{"order_id", "date", "store", "total_price"};

            writer.writeNext(header);

            while (!reportPenjualan.isEmpty()) {
                reportPenjualan
                        .forEach(report -> {
                            Object[] result = (Object[]) report;
                            writer.writeNext(new String[]{String.valueOf(result[0]), String.valueOf(result[1]), String.valueOf(result[2]), String.valueOf(result[3])});
                        });

                reportPenjualan = orderRepository.getReportPenjualanGreaterThanOrderId(getLastOrderId(reportPenjualan), limit);
            }

            log.info("finish generate report");

        } catch (Exception e) {
            log.error("error when generate report with error {}", e.getMessage());
        }
    }

    /**
     * Retrieves the last order ID from the generated report.
     *
     * @param reportPenjualan The list containing report data.
     * @return The last order ID from the report.
     */
    private int getLastOrderId(List<Object> reportPenjualan) {
        return (int) ((Object[]) reportPenjualan.get(reportPenjualan.size() - 1))[0];
    }
}
