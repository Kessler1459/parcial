package com.utn.parcial.repositories;

import com.utn.parcial.models.Cumpleanitos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CumpleRepository extends JpaRepository<Cumpleanitos,Integer> {
}
