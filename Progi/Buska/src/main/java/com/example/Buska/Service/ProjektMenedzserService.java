package com.example.Buska.Service;

import com.example.Buska.Entity.ProjektMenedzser;
import com.example.Buska.Repository.ProjektMenedzserRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProjektMenedzserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjektMenedzserRepository.class);
    @Autowired
    private ProjektMenedzserRepository projektMenedzserRepository;

/*
    @Autowired
    public ProjektMenedzserRepository getProjektMenedzserRepository() {
        return projektMenedzserRepository;
    }
*/

    @Transactional
    public List<ProjektMenedzser> getAllProjektMenedzser() {
        List<ProjektMenedzser> projektMenedzser = projektMenedzserRepository.findAll();
        if (projektMenedzser.isEmpty()) {
            throw new IllegalStateException("Nincsenek ProjektMenedzser az adatbázisban.");
        }
        return projektMenedzser;
    }

    @Transactional

    public ProjektMenedzser getProjektMenedzserById(Integer id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Érvénytelen azonosító.");
        }
        return projektMenedzserRepository.findById(id).orElse(null);
    }

    @Transactional
    public ProjektMenedzser createProjektMenedzser(ProjektMenedzser projektMenedzser) {
        if (projektMenedzser == null) {
            throw new IllegalArgumentException("A ProjektMenedzser objektum nem lehet null.");
        }
        if (projektMenedzser.getNev() == null || projektMenedzser.getNev().isBlank()) {
            throw new IllegalArgumentException("A ProjektMenedzser neve nem lehet üres.");
        }
        if (projektMenedzser.getLakcim() == null || projektMenedzser.getLakcim().isBlank()) {
            throw new IllegalArgumentException("A ProjektMenedzser lakcíme nem lehet üres.");
        }
        if (projektMenedzser.getSzuletesiDatum() == null || projektMenedzser.getSzuletesiDatum().isEmpty()) {
            throw new IllegalArgumentException("A ProjektMenedzser születési dátuma nem lehet üres.");
        }
        if (projektMenedzser.getEmail() == null || projektMenedzser.getEmail().isBlank()) {
            throw new IllegalArgumentException("A ProjektMenedzser e-mail címe nem lehet üres.");
        }
        if (projektMenedzser.getTelefonszam() == null || projektMenedzser.getTelefonszam().describeConstable().isEmpty()) {
            throw new IllegalArgumentException("A ProjektMenedzser telefonszáma nem lehet üres");
        }
        if (projektMenedzserRepository.existsByEmail(projektMenedzser.getEmail())) {
            throw new IllegalArgumentException("Ez az e-mail cím már létezik.");
        }
        if (projektMenedzserRepository.existsByTelefonszam(projektMenedzser.getTelefonszam())) {
            throw new IllegalArgumentException("Ez a telefonszám már létezik.");
        }
        projektMenedzser.setDeleted(false);
        return projektMenedzserRepository.save(projektMenedzser);
    }

    @Transactional
    public ProjektMenedzser updateProjektMenedzser(Integer id, ProjektMenedzser projektMenedzser) {
        if (id <= 0) {
            throw new IllegalArgumentException("Az azonosító érvénytelen.");
        }
        ProjektMenedzser existingEmployee = projektMenedzserRepository.findById(id).orElse(null);
        if (existingEmployee != null) {
            if (projektMenedzser == null) {
                throw new IllegalArgumentException("A ProjektMenedzser fejlesztő nem lehet null.");
            }
            if (projektMenedzser.getNev() == null || projektMenedzser.getNev().isEmpty()) {
                throw new IllegalArgumentException("A ProjektMenedzser neve nem lehet üres.");
            }
            if (projektMenedzser.getLakcim() == null || projektMenedzser.getLakcim().isEmpty()) {
                throw new IllegalArgumentException("A ProjektMenedzser lakcíme nem lehet üres.");
            }
            if (projektMenedzser.getSzuletesiDatum() == null || projektMenedzser.getSzuletesiDatum().isEmpty()) {
                throw new IllegalArgumentException("A ProjektMenedzser születési dátuma nem lehet üres.");
            }
        }
        if (projektMenedzser.getEmail() == null || projektMenedzser.getEmail().isEmpty()) {
            throw new IllegalArgumentException("A ProjektMenedzser e-mail címe nem lehet üres.");
        }
        projektMenedzser.setDeleted(false);
        assert existingEmployee != null;
        existingEmployee.setNev(projektMenedzser.getNev());
        existingEmployee.setLakcim(projektMenedzser.getLakcim());
        existingEmployee.setSzuletesiDatum(projektMenedzser.getSzuletesiDatum());
        existingEmployee.setTelefonszam(projektMenedzser.getTelefonszam());
        existingEmployee.setEmail(projektMenedzser.getEmail());
        existingEmployee.setDeleted(projektMenedzser.isDeleted());
        return projektMenedzserRepository.save(existingEmployee);
    }

    @Transactional
    public void deleteProjektMenedzser(Integer id) {
        ProjektMenedzser existingEmployee = projektMenedzserRepository.findById(id).orElse(null);
        if (existingEmployee != null) {
            existingEmployee.setDeleted(true);
            projektMenedzserRepository.save(existingEmployee);
        }
    }

    public void handleErrors(Exception e) {
        LOGGER.error("Hiba történt az alkalmazásban: " + e.getMessage());
        String errorMessage = "Hiba történt az alkalmazásban: " + e.getMessage();
        sendNotificationToDevelopers(errorMessage);
        sendNotificationToAdministrators(errorMessage);
    }

    private void sendNotificationToDevelopers(String errorMessage) {
        //  EmailService.sendEmail("fejlesztok@example.com", "Hiba az alkalmazásban", errorMessage);
        // Slack üzenet küldése a fejlesztőknek
        // SlackService.sendSlackMessage("#fejlesztok-csatorna", errorMessage);
    }

    //--Ebben az esetben a EmailService és SlackService olyan osztályok lennének, amelyek felelősek az e-mailek és a Slack üzenetek küldéséért.
    private void sendNotificationToAdministrators(String errorMessage) {
        //EmailService.sendEmail("adminisztratorok@example.com", "Hiba az alkalmazásban", errorMessage);
        // Slack üzenet küldése az adminisztrátoroknak
        //SlackService.sendSlackMessage("#adminisztratorok-csatorna", errorMessage);
    }
}
            /* Ezzel a módszerrel az alkalmazott törlésének helyett csak a deleted mező értéke módosul, így az adatbázisban továbbra is megmaradnak az információk az adott alkalmazottról.
            public void deleteEmployee(Long id) {
            Programozo programozo = EmployeeRepository.findById(id)
                          .orElseThrow(() -> new IllegalArgumentException("Nem található alkalmazott ezzel az azonosítóval: " + id));
           programozo.setDeleted(true);
           EmployeeRepository.save(programozo);
           }
           }
           } */

