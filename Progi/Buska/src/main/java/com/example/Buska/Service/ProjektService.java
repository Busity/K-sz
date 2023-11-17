package com.example.Buska.Service;

import com.example.Buska.Entity.Projekt;
import com.example.Buska.Repository.ProjektRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjektService {
    private final ProjektRepo projektRepo;

    public ProjektService(ProjektRepo projektRepo) {
        this.projektRepo = projektRepo;
    }
    public List<Projekt> getAllProjekts() {
        return projektRepo.findAll();
    }
    public Projekt getProjektById(Integer id) {
        return projektRepo.findById(id).orElse(null);
    }
    public Projekt createProjekt(Projekt projekt) {
        return projektRepo.save(projekt);
    }
    public Projekt updateProjekt(Integer id, Projekt projekt) {
        if (projektRepo.existsById(id)) {
            projekt.setId(id);
            return projektRepo.save(projekt);
        }
        return null;
        }

    public void deleteProjekt(Integer id) {
        Projekt projekt = projektRepo.findById(id).orElse(null);
        if (projekt != null) {
            projekt.setDeleted(true);
            projektRepo.save(projekt);
        }
        }
        }