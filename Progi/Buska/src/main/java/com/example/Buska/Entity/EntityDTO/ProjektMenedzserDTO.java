package com.example.Buska.Entity.EntityDTO;

import com.example.Buska.Entity.ProjektMenedzser;
import java.util.List;
import java.util.stream.Collectors;

public class ProjektMenedzserDTO extends AlkalmazottDTO {

    private List<ProjektDTO> projektek;
    private List<AlkalmazottDTO> beosztottak;
    public ProjektMenedzserDTO() {
        super();
    }
    public ProjektMenedzserDTO(ProjektMenedzser projektMenedzser) {
        super(projektMenedzser);
        this.projektek = projektMenedzser.getProjektek().stream()
                .map(ProjektDTO::new)
                .collect(Collectors.toList());
        this.beosztottak = projektMenedzser.getBeosztottak().stream()
                .map(AlkalmazottDTO::new)
                .collect(Collectors.toList());
    }
    public List<ProjektDTO> getProjektek() {
        return projektek;
    }
    public void setProjektek(List<ProjektDTO> projektek) {
        this.projektek = projektek;
    }
    public List<AlkalmazottDTO> getBeosztottak() {
        return beosztottak;
    }
    public void setBeosztottak(List<AlkalmazottDTO> beosztottak) {
        this.beosztottak = beosztottak;
    }
    }