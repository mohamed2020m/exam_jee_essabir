package com.essabir.exam.repositories;

import com.essabir.exam.entities.Employe;
import com.essabir.exam.entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface EmployeRepository extends JpaRepository<Employe, Long> {
    List<Employe> findByService(Service service);
}
