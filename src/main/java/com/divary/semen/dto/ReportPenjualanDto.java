package com.divary.semen.dto;

import jakarta.persistence.Column;

import java.util.Date;

public class ReportPenjualanDto {
    @Column(name = "order_id")
    private Long orderId;

    private Date date;
    private String store;
    @Column(name = "total_price")
    private int totalPrice;

}
