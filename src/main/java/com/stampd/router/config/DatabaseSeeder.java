package com.stampd.router.config;

import com.stampd.router.model.Manager;
import com.stampd.router.model.PurchaseOrder;
import com.stampd.router.model.Vendor;
import com.stampd.router.repository.ManagerRepository;
import com.stampd.router.repository.PurchaseOrderRepository;
import com.stampd.router.repository.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseSeeder {

    @Bean
    CommandLineRunner initDatabase(ManagerRepository managerRepo, VendorRepository vendorRepo, PurchaseOrderRepository poRepo) {
        return args -> {
            // 1. Create a Manager
            Manager techLead = new Manager();
            techLead.setName("Mateen Qureshi");
            techLead.setDepartment("Engineering");
            techLead.setSlackId("U12345678"); // Fake Slack ID for now
            managerRepo.save(techLead);

            // 2. Create a Vendor
            Vendor dell = new Vendor();
            dell.setName("Dell Technologies");
            vendorRepo.save(dell);

            // 3. Create a Purchase Order linked to both
            PurchaseOrder po = new PurchaseOrder();
            po.setPoNumber("PO-98765");
            po.setTotalAmount(2500.00);
            po.setStatus("PENDING_DELIVERY");
            po.setManager(techLead);
            po.setVendor(dell);
            poRepo.save(po);
        };
    }
}