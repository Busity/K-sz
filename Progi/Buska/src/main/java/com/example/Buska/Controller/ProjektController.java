package com.example.Buska.Controller;

import com.example.Buska.Entity.Projekt;
import com.example.Buska.Service.ProjektService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projektek")
public class ProjektController {
    private final ProjektService projektService;

    public ProjektController(ProjektService projektService) {
        this.projektService = projektService;
    }

    @GetMapping
    public List<Projekt> getAllProjekts() {
        return projektService.getAllProjekts();
    }

    @GetMapping("/{id}")
    public Projekt getProjektById(@PathVariable Integer id) {
        return projektService.getProjektById(id);
    }

    @PostMapping
    public Projekt createProjekt(@RequestBody Projekt projekt) {
        return projektService.createProjekt(projekt);
    }

    @PutMapping("/{id}")
    public Projekt updateProjekt(@PathVariable Integer id, @RequestBody Projekt projekt) {
        return projektService.updateProjekt(id, projekt);
    }

    @DeleteMapping("/{id}")
    public void deleteProjekt(@PathVariable Integer id) {
        projektService.deleteProjekt(id);
    }
}