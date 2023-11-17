package com.example.Buska.Mapper;

import com.example.Buska.Entity.EntityDTO.ProjektMenedzserDTO;
import com.example.Buska.Entity.ProjektMenedzser;
import org.mapstruct.factory.Mappers;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

    @Mapper
    public interface ProjektMenedzserMapper {

    ProjektMenedzserMapper INSTANCE = Mappers.getMapper(ProjektMenedzserMapper.class);

    @Mapping(source = "projektek", target = "projektek")
    @Mapping(source = "beosztottak", target = "beosztottak")
    ProjektMenedzserDTO toDTO(ProjektMenedzser projektMenedzser);

    List<ProjektMenedzserDTO> toDTOList(List<ProjektMenedzser> projektMenedzserList);
}

