package com.example.Buska.Repository;

import com.example.Buska.Entity.ProjektMenedzser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProjektMenedzserRepository extends JpaRepository<ProjektMenedzser, Integer> {
    boolean existsByEmail(String email);
    boolean existsByTelefonszam(String telefonszam);
}

