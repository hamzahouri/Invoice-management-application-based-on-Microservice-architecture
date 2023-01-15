package org.sid.bilingservice.repository;

import org.sid.bilingservice.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill,Long> {

    @RestResource(path = "/byCustomerId")
    List<Bill> findByCustomerId(@Param("customerId") Long customerId);
}
