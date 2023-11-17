package com.example.Buska.Repository;

import com.example.Buska.Entity.Programozo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProgramozoRepo extends JpaRepository<Programozo,Integer> {

    boolean existsByEmail(String email);

    boolean existsByTelefonszam(String telefonszam);

}


