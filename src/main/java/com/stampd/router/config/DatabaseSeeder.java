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
            // Safety check: Only inject if the cabinet is completely empty
            if (poRepo.count() == 0) {
                Manager techLead = new Manager();
                techLead.setName("Mateen Qureshi");
                managerRepo.save(techLead);

                Vendor dell = new Vendor();
                dell.setName("Dell Technologies");
                vendorRepo.save(dell);

                // Generate 50 unique Purchase Orders (PO-1001 to PO-1050)
                for (int i = 1; i <= 50; i++) {
                    PurchaseOrder po = new PurchaseOrder();
                    po.setPoNumber("PO-" + (1000 + i));
                    po.setTotalAmount(1000.00); // All POs have a true value of $1,000
                    po.setManager(techLead);
                    po.setVendor(dell);
                    poRepo.save(po);
                }
            }
        };
    }
}