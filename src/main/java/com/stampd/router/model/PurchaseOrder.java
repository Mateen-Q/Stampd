package com.stampd.router.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "purchase_orders")
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String poNumber; 

    private Double totalAmount;
    private String status; 

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Manager manager; 

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor; 
}