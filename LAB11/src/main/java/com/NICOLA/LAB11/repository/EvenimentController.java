package com.NICOLA.LAB11.repository;

import com.NICOLA.LAB11.entity.Eveniment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class EvenimentController {

    @Autowired
    EvenimentRepository repository;

    @GetMapping("jpa/evenimente")
    public List<Eveniment> findAll() {
        return repository.findAll();
    }

    @GetMapping("jpa/evenimente/locatie/{locatie}")
    public Eveniment findByLocatie(@PathVariable String locatie) {
        Optional<Eveniment> OptionalEveniment = repository.findByLocatie(locatie);
        if (OptionalEveniment.isPresent()) {
            return OptionalEveniment.get();
        } else throw new RuntimeException("Evenimentul cu locatia " + locatie + " nu a fost găsita.");
    }

    @GetMapping("jpa/evenimente/data/{data}")
    public Eveniment findByData(@PathVariable LocalDate data) {
        Optional<Eveniment> OptionalEveniment = repository.findByData(data);
        if (OptionalEveniment.isPresent()) {
            return OptionalEveniment.get();
        } else throw new RuntimeException("Evenimentul cu data " + data + " nu a fost găsita.");
    }

    @PostMapping("jpa/evenimente")
    public void saveEveniment(@RequestBody Eveniment eveniment) {
        repository.save(eveniment);
    }

    @DeleteMapping("jpa/evenimente/{id}")
    public void deleteEvenimentById(@PathVariable Long id) {
        repository.deleteById(id);
    }
}