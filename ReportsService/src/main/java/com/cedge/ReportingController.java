package com.cedge;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cedge.model.CustomerReport;
import com.cedge.model.LoanReport;

@RestController
@RequestMapping("/reports")
public class ReportingController {
	
	    @Autowired
	    private ReportingService reportingService;

	    @GetMapping("/customer/{customerId}")
	    public ResponseEntity<CustomerReport> getCustomerReport(@PathVariable Long customerId) {
	        CustomerReport report = reportingService.generateCustomerReport(customerId);
	        return ResponseEntity.ok(report);
	    }
	    
	    
	    @GetMapping("/loans/active")
	    public ResponseEntity<List<LoanReport>> getActiveLoans() {
	        List<LoanReport> activeLoans = reportingService.getActiveLoans();
	        return ResponseEntity.ok(activeLoans);
	    }

}
