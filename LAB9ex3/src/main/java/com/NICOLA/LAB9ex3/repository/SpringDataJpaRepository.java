package com.NICOLA.LAB9ex3.repository;


import java.util.List;

import com.NICOLA.LAB9ex3.entity.Masina;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SpringDataJpaRepository extends JpaRepository<Masina, Integer>{

    List<Masina> findByMarca(String marca);
    List<Masina> findByNrkmIsBefore(int nrkm);
    List<Masina> findByAnulfabricatieiIsAfter(int anulFabricatiei);
}