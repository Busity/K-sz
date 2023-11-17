package com.example.Buska.Entity.EntityDTO;

import com.example.Buska.Entity.Programozo;
import com.example.Buska.Enum.Feladatkor;
import java.util.List;
import java.util.stream.Collectors;

public class ProgramozoDTO extends AlkalmazottDTO {

    private Feladatkor feladatkor;
    private boolean gyakornok;
    private List<ProjektDTO> projektek;
    private ProjektMenedzserDTO projektmenedzser;
    private List<AlkalmazottDTO> beosztottak;

    public ProgramozoDTO() {
        super();
    }

    public ProgramozoDTO(Programozo programozo) {
        super(programozo);
        this.feladatkor = programozo.getFeladatkor();
        this.gyakornok = programozo.isGyakornok();
        this.projektek = programozo.getProjektek().stream()
                .map(ProjektDTO::new)
                .collect(Collectors.toList());
        this.projektmenedzser = new ProjektMenedzserDTO(programozo.getProjektmenedzser());
        this.beosztottak = programozo.getBeosztottak().stream()
                .map(AlkalmazottDTO::new)
                .collect(Collectors.toList());
    }
}
