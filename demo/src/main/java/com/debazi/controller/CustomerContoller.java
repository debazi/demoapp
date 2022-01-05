/**
 * 
 */
package com.debazi.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.debazi.entity.Customer;
import com.debazi.export.CustomerPDFExporter;
import com.debazi.service.CustomerService;
import com.google.common.collect.Lists;
import com.lowagie.text.DocumentException;

/**
 * @author marc-assie
 */
@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api")
public class CustomerContoller {

    /**
     * Service sur les clients.
     */
    @Autowired
    private CustomerService customerService;

    /**
     * Recherche et retourne la liste de tous les clients.
     * @return Liste de tous les clients
     */
    @GetMapping("/customers")
    public List<Customer> getCustomers () {
        List<Customer> customers = Lists.newArrayList();
        for (Customer customer : this.customerService.findAll()) {
            customers.add(customer);
        }
        return customers;
    }

    /**
     * Créé et enregistre tous les clients.
     * @param customer client (requis).
     * @return client.
     */
    @PostMapping("/customer")
    public Customer postCustomer (@RequestBody Customer customer) {
        Customer _customer = this.customerService.save(new Customer(customer.getName(), customer.getAge()));
        return _customer;
    }

    /**
     * Supprime un client par son identifiant.
     * @param id identifiant du client(requis).
     * @return x
     */
    @DeleteMapping("/customer/{id}")
    public ResponseEntity<String> deleteCustomer (@PathVariable("id") long id) {
        this.customerService.deleteById(Long.valueOf(id));
        return new ResponseEntity<String>("Customer has been deleted!", HttpStatus.OK);
    }

    /**
     * Recherche et retourne la liste des clients selon l'âge selectionné.
     * @param age âge du client (requis)
     * @return Liste des clients selon l'âge.
     */
    @GetMapping("customers/age/{age}")
    public List<Customer> findByAge (@PathVariable int age) {
        List<Customer> customers = this.customerService.findByAge(age);
        return customers;
    }

    /**
     * Met à jour le client.
     * @param id Identifiant technique du client.
     * @param customer Client
     * @return Client
     */
    @PutMapping("/customer/{id}")
    public ResponseEntity<Customer> updateCustomer (@PathVariable("id") long id, @RequestBody Customer customer) {
        Optional<Customer> customerData = this.customerService.findById(Long.valueOf(id));

        if (customerData.isPresent()) {
            Customer _customer = customerData.get();
            _customer.setName(customer.getName());
            _customer.setAge(customer.getAge());
            _customer.setActive(customer.isActive());
            return new ResponseEntity<Customer>(this.customerService.save(_customer), HttpStatus.OK);
        }
        return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
    }

    /**
     * @param response x
     * @throws DocumentException x
     * @throws IOException x
     */
    @GetMapping("/customers/export/pdf")
    public void exportPDF (HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=clients" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Customer> listUsers = this.customerService.findAll(Sort.by("name").ascending());

        CustomerPDFExporter exporter = new CustomerPDFExporter(listUsers);
        exporter.export(response);

    }

}
