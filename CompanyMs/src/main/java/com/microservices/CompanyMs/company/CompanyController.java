package com.microservices.CompanyMs.company;

import com.microservices.CompanyMs.company.impl.CompanyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    CompanyService companyService;

    @Autowired
    public CompanyController(CompanyServiceImpl companyServiceImpl) {
        this.companyService = companyServiceImpl;
    }

//    @GetMapping("/{companyName}")
//    public ResponseEntity<Company> getCompanyByName(@PathVariable String companyName) {
//        Company company = companyService.getCompany(companyName);
//        if (company == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(company, HttpStatus.OK);
//    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies(){
        List<Company> companies = companyService.getAllCompanies();
        if(companies.isEmpty())
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(companies,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable("id") int id){
        Company company = companyService.getCompanyById(id);
        if (company == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);  // Return 404 if not found
        }
        return new ResponseEntity<>(company,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Company> addCompany(@RequestBody Company company){
        return new ResponseEntity<>(companyService.addCompany(company),HttpStatus.CREATED);
    }

}
