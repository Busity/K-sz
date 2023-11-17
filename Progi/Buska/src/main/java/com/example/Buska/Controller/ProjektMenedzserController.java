package com.example.Buska.Controller;

import com.example.Buska.Entity.ProjektMenedzser;
import com.example.Buska.Service.ProjektMenedzserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ProjMan")
public class ProjektMenedzserController {

    @Autowired
    private final ProjektMenedzserService projektMenedzserService;

    @Autowired
    public ProjektMenedzserController(ProjektMenedzserService projektMenedzserService) {
        this.projektMenedzserService = projektMenedzserService;
    }

    @GetMapping("/mind1")
    public ResponseEntity<List<ProjektMenedzser>> getAllEmployees1() {
        List<ProjektMenedzser> employees = projektMenedzserService.getAllProjektMenedzser();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ProjektMenedzser getEmployeeById(@PathVariable Integer id) {
        return projektMenedzserService.getProjektMenedzserById(id);
    }

    @PostMapping()
    public ProjektMenedzser createEmployee(@RequestBody ProjektMenedzser projektMenedzser) {
        return projektMenedzserService.createProjektMenedzser(projektMenedzser);
    }

    @PutMapping("/{id}")
    public ProjektMenedzser updateEmployee(@PathVariable Integer id, @RequestBody ProjektMenedzser projektMenedzser) {
        return projektMenedzserService.updateProjektMenedzser(id, projektMenedzser);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Integer id) {
        projektMenedzserService.deleteProjektMenedzser(id);
    }
}