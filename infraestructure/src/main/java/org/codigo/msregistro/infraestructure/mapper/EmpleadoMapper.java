package org.codigo.msregistro.infraestructure.mapper;

import org.codigo.msregistro.domain.aggregates.dto.EmpleadoDTO;
import org.codigo.msregistro.infraestructure.entity.EmpleadoEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public EmpleadoDTO mapToDto(EmpleadoEntity empleadoEntity)
    {
        return modelMapper.map(empleadoEntity, EmpleadoDTO.class);
    }

    public EmpleadoEntity mapToEntity(EmpleadoDTO empleadoDTO){
        return modelMapper.map(empleadoDTO, EmpleadoEntity.class);
    }
}
