package com.essabir.exam.repositories;

import com.essabir.exam.entities.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface EmployeRepository extends JpaRepository<Employe, Long> {
}
