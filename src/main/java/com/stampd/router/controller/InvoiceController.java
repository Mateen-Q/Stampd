package com.stampd.router.controller;

import com.stampd.router.dto.IncomingInvoiceDto;
import com.stampd.router.service.InvoiceRoutingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceRoutingService routingService;

    @PostMapping("/webhook")
    public ResponseEntity<String> receiveInvoice(@RequestBody IncomingInvoiceDto incomingInvoice) {
        String result = routingService.processInvoice(incomingInvoice);
        return ResponseEntity.ok(result);
    }
}