/**
 * 
 */
package com.debazi.service;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import com.debazi.entity.Customer;

/**
 * Objet représentant le service sur
 * @author marc-assie
 */
public interface CustomerService
        extends CrudRepository<Customer, Long> {

    /**
     * Recherche et retourne la liste des clients selon l'âge selectionné.
     * @param age âge du client (requis)
     * @return Liste des clients selon l'âge (
     */
    List<Customer> findByAge (int age);

    /**
     * @param sort x
     * @return x
     */
    List<Customer> findAll (Sort sort);
}
