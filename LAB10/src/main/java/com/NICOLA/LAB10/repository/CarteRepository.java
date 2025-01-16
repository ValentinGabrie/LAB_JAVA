package com.NICOLA.LAB10.repository;

import com.NICOLA.LAB10.entity.Carte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarteRepository extends JpaRepository<Carte, String> {
    List<Carte> findByAutor(@Param("autor") String autor);


}
