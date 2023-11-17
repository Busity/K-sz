package com.example.Buska.Service;

import com.example.Buska.Entity.Programozo;
import com.example.Buska.Repository.ProgramozoRepo;
import com.example.Buska.Repository.ProjektMenedzserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeService  {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjektMenedzserRepository.class);
    @Autowired
    private ProgramozoRepo programozoRepo;
    public EmployeeService(ProgramozoRepo programozoRepo) {
        this.programozoRepo = programozoRepo;
    }

    @Scheduled(cron = "0 0 1 * * ?") // minden nap 1:00-kor
    public void myScheduledTask() {
        List<Programozo> programozok = getAllProgramozo();
        int programozoCount = programozok.size();

        System.out.println("Az időzített feladat lefutott, programozók száma: " + programozoCount);
    }
    public List<Programozo> getAllProgramozo() {
        List<Programozo> programozok = programozoRepo.findAll();
        if (programozok.isEmpty()) {
            throw new IllegalStateException("Nincsenek Programozók az adatbázisban.");
        }
        return programozok;
    }

   /* public List<ProgramozoDTO> getAllProgramozo() {
        List<Programozo> programozoList = programozoRepo.findAll();
        if (programozoList.isEmpty()) {
            throw new IllegalStateException("Nincsenek Programozók az adatbázisban.");
        }

        // Programozo entitások átalakítása ProgramozoDTO objektumokká
        List<ProgramozoDTO> programozoDTOList = programozoList.stream()
                .map(ProgramozoDTO::new) // ProgramozoDTO osztály konstruktorának használata
                .collect(Collectors.toList());

        return programozoDTOList;
    }*/

    public Programozo getProgramozoById(Integer id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Érvénytelen azonosító.");
        }
        return programozoRepo.findById(id).orElse(null);
        }
    /*public ProgramozoDTO getProgramozoById(Integer id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Érvénytelen azonosító.");
        }
        Programozo programozo = programozoRepo.findById(id).orElse(null);
        if (programozo == null) {
            return null;
        }
        // Programozo entitás átalakítása ProgramozoDTO objektummá
        return new ProgramozoDTO(programozo); // ProgramozoDTO osztály konstruktorának használata
    }*/

    public Programozo createProgramozo(Programozo programozo) {
        if (programozo == null) {
            throw new IllegalArgumentException("A ProjektMenedzser objektum nem lehet null.");
        }
        if (programozo.getNev() == null || programozo.getNev().isBlank()) {
            throw new IllegalArgumentException("A ProjektMenedzser neve nem lehet üres.");
        }
        if (programozo.getLakcim() == null || programozo.getLakcim().isBlank()) {
            throw new IllegalArgumentException("A ProjektMenedzser lakcíme nem lehet üres.");
        }
        if (programozo.getSzuletesiDatum() == null || programozo.getSzuletesiDatum().isEmpty()) {
            throw new IllegalArgumentException("A ProjektMenedzser születési dátuma nem lehet üres.");
        }
        if (programozo.getEmail() == null || programozo.getEmail().isBlank()) {
            throw new IllegalArgumentException("A ProjektMenedzser e-mail címe nem lehet üres.");
        }
        if (programozo.getTelefonszam() == null || programozo.getTelefonszam().describeConstable().isEmpty()) {
            throw new IllegalArgumentException("A ProjektMenedzser telefonszáma nem lehet üres");
        }
        if (programozoRepo.existsByEmail(programozo.getEmail())) {
            throw new IllegalArgumentException("Ez az e-mail cím már létezik.");
        }
        if (programozoRepo.existsByTelefonszam(programozo.getTelefonszam())) {
            throw new IllegalArgumentException("Ez a telefonszám már létezik.");
        }
        programozo.setDeleted(false);
        return programozoRepo.save(programozo);
        }


    public Programozo updateProgramozo(Integer id, Programozo programozo) {
        if (id <= 0) {
            throw new IllegalArgumentException("Az azonosító érvénytelen.");
        }
        Programozo existingEmployee = programozoRepo.findById(id).orElse(null);
        if (existingEmployee != null) {
            if (programozo == null) {
                throw new IllegalArgumentException("A ProjektMenedzser fejlesztő nem lehet null.");
            }
            if (programozo.getNev() == null || programozo.getNev().isEmpty()) {
                throw new IllegalArgumentException("A ProjektMenedzser neve nem lehet üres.");
            }
            if (programozo.getLakcim() == null || programozo.getLakcim().isEmpty()) {
                throw new IllegalArgumentException("A ProjektMenedzser lakcíme nem lehet üres.");
            }
            if (programozo.getSzuletesiDatum() == null || programozo.getSzuletesiDatum().isEmpty()) {
                throw new IllegalArgumentException("A ProjektMenedzser születési dátuma nem lehet üres.");
            }
        }
        if (programozo.getEmail() == null || programozo.getEmail().isEmpty()) {
            throw new IllegalArgumentException("A ProjektMenedzser e-mail címe nem lehet üres.");
        }
        if (programozoRepo.existsByEmail(programozo.getEmail())) {
            throw new IllegalArgumentException("Ez az e-mail cím már létezik.");
        }
        if (programozoRepo.existsByTelefonszam(programozo.getTelefonszam())) {
            throw new IllegalArgumentException("Ez a telefonszám már létezik.");
        }
        programozo.setDeleted(false);
        assert existingEmployee != null;
        existingEmployee.setNev(programozo.getNev());
        existingEmployee.setLakcim(programozo.getLakcim());
        existingEmployee.setSzuletesiDatum(programozo.getSzuletesiDatum());
        existingEmployee.setTelefonszam(programozo.getTelefonszam());
        existingEmployee.setEmail(programozo.getEmail());
        existingEmployee.setDeleted(programozo.isDeleted());
        return programozoRepo.save(existingEmployee);
        }

    public void deleteProgramozo(Integer id) {
        Programozo existingEmployee = programozoRepo.findById(id).orElse(null);
        if (existingEmployee != null) {
            existingEmployee.setDeleted(true);
            programozoRepo.save(existingEmployee);
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


       // Ezzel a módszerrel az alkalmazott törlésének helyett csak a deleted mező értéke módosul, így az adatbázisban továbbra is megmaradnak az információk az adott alkalmazottról.
       /*        public void deleteEmployee(Integer id) {
          Programozo programozo = programozoRepo.findById(id)
                          .orElseThrow(() -> new IllegalArgumentException("Nem található alkalmazott ezzel az azonosítóval: " + id));
           programozo.setDeleted(true);
           programozoRepo.save(programozo);
        }
        }*/

        }
   /* @Override
    public void run(String... args) throws Exception {
        // Itt végezd el az inicializációs műveleteket
        System.out.println("Az alkalmazás indult, elindítom az EmployeeService inicializációját...");

        // Példa: Beszúrunk egy kezdeti programozót az adatbázisba, ha még nincs ott.
        if (programozoRepo.count() == 0) {
            Programozo kezdetiProgramozo = new Programozo();
            kezdetiProgramozo.setNev("Kezdeti Programozo");
            kezdetiProgramozo.setLakcim("Kezdeti Lakcim");
            kezdetiProgramozo.setSzuletesiDatum("2000-01-01");
            kezdetiProgramozo.setEmail("kezdeti@example.com");
            kezdetiProgramozo.setTelefonszam("123456789");

            programozoRepo.save(kezdetiProgramozo);
        }
*/
    }

