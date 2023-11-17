package com.example.Buska.Controller;

import com.example.Buska.Entity.Programozo;
import com.example.Buska.Repository.ProgramozoRepo;
import com.example.Buska.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;




@RestController
@RequestMapping("/proba")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    ProgramozoRepo programozoRepo;
    @Autowired
    public EmployeeController(ProgramozoRepo programozoRepo) {
        this.programozoRepo = programozoRepo;
    }

        @GetMapping("/programozok")
        public ResponseEntity<List<Programozo>> getAllEmployees() {
        List<Programozo> programozok = employeeService.getAllProgramozo();
        if (programozok.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(programozok, HttpStatus.OK);
        }
        @GetMapping("/{id}")
        public Programozo getEmployeeById(@PathVariable Integer id) {
        return employeeService.getProgramozoById(id);
    }

        @PostMapping
        public ResponseEntity<Programozo> createProgramozo(@RequestBody Programozo programozo) {
        try {
            Programozo savedProgramozo = employeeService.createProgramozo(programozo);
            return new ResponseEntity<>(savedProgramozo, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ez az e-mail cím már létezik.", ex);
        }
        }
        @PutMapping("/{id}")
        public ResponseEntity<Programozo> updateProgramozo(@PathVariable Integer id, @RequestBody Programozo programozo) {
        return ResponseEntity.ok(employeeService.updateProgramozo(id, programozo));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<String>  deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteProgramozo(id);
        return ResponseEntity.ok("A programozó sikeresen törölve!");
        }
        }
