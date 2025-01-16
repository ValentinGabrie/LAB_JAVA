package com.NICOLA.LAB11.repository;

import com.NICOLA.LAB11.entity.Eveniment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface EvenimentRepository extends JpaRepository<Eveniment, Long> {
    Optional<Eveniment> findByLocatie(String locatie);

    Optional<Eveniment> findByData(LocalDate data);
}
