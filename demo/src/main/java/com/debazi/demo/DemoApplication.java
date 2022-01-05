package com.debazi.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import com.debazi.entity.Customer;
import com.debazi.service.CustomerService;

/**
 * Objet représentant l'élément de démarage de notre application.
 * @author marc-assie
 */
@SpringBootApplication
@ComponentScan({ "com.debazi.controller" })
@EntityScan("com.debazi.entity")
@EnableJpaRepositories("com.debazi.service")
public class DemoApplication
        implements CommandLineRunner {

    /**
     * Constructeur.
     * @param args arguments (requis)
     */
    public static void main (String[] args) {
        SpringApplication.run(DemoApplication.class, args);

    }

    /**
     * Service
     */
    @Autowired
    private CustomerService customerService;

    /**
     * (non-Javadoc)
     * @see org.springframework.boot.CommandLineRunner#run(java.lang.String[])
     */
    public void run (String... args) {
        Customer customer1 = new Customer("Atto Koffi", 25);
        customer1.setActive(true);
        Customer customer2 = new Customer("Jean Noel", 10);
        customer2.setActive(false);
        Customer customer3 = new Customer("Doumbia Salifou", 16);
        customer3.setActive(true);
        Customer customer4 = new Customer("Blaise Bolorré", 48);
        customer4.setActive(true);
        Customer customer5 = new Customer("Enersto Jean Fabrice", 70);
        customer5.setActive(false);

        this.customerService.save(customer1);
        this.customerService.save(customer2);
        this.customerService.save(customer3);
        this.customerService.save(customer4);
        this.customerService.save(customer5);
    }

}
