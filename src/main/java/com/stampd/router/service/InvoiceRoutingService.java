package com.stampd.router.service;

import com.stampd.router.dto.IncomingInvoiceDto;
import com.stampd.router.model.PurchaseOrder;
import com.stampd.router.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
public class InvoiceRoutingService {

    @Autowired
    private PurchaseOrderRepository poRepository;

    public String processInvoice(IncomingInvoiceDto incomingInvoice) {
        Optional poOptional = poRepository.findByPoNumber(incomingInvoice.getPoNumber());

        if (poOptional.isEmpty()) {
            return "FALLBACK: PO Number " + incomingInvoice.getPoNumber() + " not found. Sending to Exceptions Queue.";
        }

        PurchaseOrder po = poOptional.get();

        if (!po.getTotalAmount().equals(incomingInvoice.getTotalAmount())) {
            return "WARNING: Amount mismatch! Expected $" + po.getTotalAmount() + ", but Invoice says $" + incomingInvoice.getTotalAmount();
        }

        // In the future, this is where we trigger the Slack Webhook
        String managerName = po.getManager().getName();
        return "SUCCESS: Match found! Routing approval request to Manager: " + managerName;
    }
}