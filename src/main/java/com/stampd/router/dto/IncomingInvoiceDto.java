package com.stampd.router.dto;

import lombok.Data;

@Data
public class IncomingInvoiceDto {
    private String poNumber;
    private Double totalAmount;
    private String vendorName;
}