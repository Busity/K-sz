package com.example.Buska.Mapper;

import com.example.Buska.Entity.EntityDTO.ProjektDTO;
import com.example.Buska.Entity.Projekt;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

    @Mapper
    public interface ProjektMapper {

    ProjektMapper INSTANCE = Mappers.getMapper(ProjektMapper.class);

    @Mapping(source = "megrendelo", target = "megrendelo")
    @Mapping(source = "indulasiDatum", target = "indulasiDatum")
    @Mapping(source = "leiras", target = "leiras")
    @Mapping(source = "deleted", target = "deleted")
    ProjektDTO toDTO(Projekt projekt);

    }