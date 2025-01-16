package com.NICOLA.LAB10.repository;

import com.NICOLA.LAB10.entity.Carte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CarteWebControler
{
    @Autowired
    private CarteRepository carteRepository;

    @GetMapping("/lista-carti")
    public String showCarti(Model model) {
        List<Carte> carti = carteRepository.findAll();
        model.addAttribute("carti", carti);
        model.addAttribute("message", "Lista cartilor preluate prin repository.");
        return "carti";
    }
    @PostMapping("/operatii")
    public String handleOperatii(@RequestParam String isbn,
                                 @RequestParam(required = false) String titlu,
                                 @RequestParam(required = false) String autor,
                                 @RequestParam(required = false) String adauga,
                                 @RequestParam(required = false) String modifica,
                                 @RequestParam(required = false) String sterge,
                                 @RequestParam(required = false) String filtreaza,
                                 Model model) {
        String message = "";
        if (adauga != null)
        {
            if (isbn.isEmpty() || titlu.isEmpty() || autor.isEmpty())
            {
                message = "Adaugarea nu se realizează dacă nu completaţi toate caracteristicile!";
            }
            else
            {
                carteRepository.save(new Carte(isbn, titlu, autor));
                message = "Adaugare realizata cu succes!";
            }
            model.addAttribute("message", message);
            model.addAttribute("carti", carteRepository.findAll());
        }
        else if (modifica != null)
        {
            Carte carte = carteRepository.findById(isbn).orElse(null);
            if (carte == null)
            {
                message = "Nu se găsește nicio carte cu ISBN-ul introdus.";
            }
            else
            {
                carte.setTitlu(titlu);
                carte.setAutor(autor);
                carteRepository.save(carte);
                message = "Cartea cu ISBN-ul " + isbn + " a fost modificată!";
            }
            model.addAttribute("message", message);
            model.addAttribute("carti", carteRepository.findAll());
        }
        else if (sterge != null)
        {
            if (carteRepository.existsById(isbn))
            {
                carteRepository.deleteById(isbn);
                message = "Cartea cu ISBN-ul " + isbn + " a fost ștearsă!";
            }
            else
            {
                message = "Nu există nicio carte cu ISBN-ul introdus.";
            }
            model.addAttribute("message", message);
            model.addAttribute("carti", carteRepository.findAll());
        }
        else if (filtreaza != null)
        {
            List<Carte> carti;
            if (autor.isEmpty())
            {
                carti = carteRepository.findAll();
                message = "Toate cărțile sunt afișate.";
            }
            else
            {
                carti = carteRepository.findByAutor(autor);
                message = "Cărțile următoare aparțin autorului " + autor + ".";
            }
            model.addAttribute("message", message);
           // model.addAttribute("carti", carteRepository.findAll());
            model.addAttribute("carti", carti);
        }


        return "carti";
    }
}
