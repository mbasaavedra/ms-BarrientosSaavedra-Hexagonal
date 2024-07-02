package org.codigo.msregistro.domain.impl;

import lombok.RequiredArgsConstructor;
import org.codigo.msregistro.domain.aggregates.constans.Constants;
import org.codigo.msregistro.domain.aggregates.dto.EmpleadoDTO;
import org.codigo.msregistro.domain.aggregates.request.RequestEmpleado;
import org.codigo.msregistro.domain.aggregates.response.ResponseBase;
import org.codigo.msregistro.domain.ports.in.EmpleadoServiceIn;
import org.codigo.msregistro.domain.ports.out.EmpleadoServiceOut;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmpleadoServiceImpl implements EmpleadoServiceIn {

    private final EmpleadoServiceOut empleadoServiceOut;

    @Override
    public ResponseBase crearEmpleadoIn(RequestEmpleado empleado) {
        EmpleadoDTO empleadoDTO =  empleadoServiceOut.crearEmpleadonaOut(empleado);
        if(empleadoDTO != null){
            return new ResponseBase(Constants.CODIGO_EXITO, Constants.MENSAJE_EXITO, Optional.empty());
        } else{
            return new ResponseBase(Constants.CODIGO_ERROR, Constants.MENSAJE_ERROR, Optional.empty());
        }
    }

    @Override
    public ResponseBase actualizarIn(Long id, EmpleadoDTO empleadoDTO) {
        EmpleadoDTO empleado = empleadoServiceOut.actualizarOut(id, empleadoDTO);

        if(empleado != null){
            return new ResponseBase(Constants.CODIGO_EXITO, Constants.MENSAJE_UPD_EXITO, Optional.of(empleado));
        } else{
            return new ResponseBase(Constants.CODIGO_ERROR, Constants.MENSAJE_ERROR, Optional.empty());
        }
    }

    @Override
    public ResponseBase eliminarEmpleadoIn(Long id) {
        EmpleadoDTO empleado = empleadoServiceOut.eliminarOut(id);

        if(empleado != null){
            return new ResponseBase(Constants.CODIGO_EXITO, Constants.MENSAJE_DLT_EXITO, Optional.of(empleado));
        } else{
            return new ResponseBase(Constants.CODIGO_ERROR, Constants.MENSAJE_ERROR, Optional.empty());
        }
    }

    @Override
    public ResponseBase buscarXDocumento(String numDoc) {
        EmpleadoDTO empleado = empleadoServiceOut.buscarXNumDocu(numDoc);
        if(empleado != null){
            return new ResponseBase(Constants.CODIGO_EXITO, Constants.MENSAJE_OK_EMPLEADO, Optional.of(empleado));
        } else{
            return new ResponseBase(Constants.CODIGO_ERROR, Constants.MENSAJE_NOT_EMPLEADO, Optional.empty());
        }
    }

    @Override
    public ResponseBase buscarTodos(boolean estado) {
        List<EmpleadoDTO> empleado = empleadoServiceOut.buscarTodos(estado);
        if(empleado != null){
            return new ResponseBase(Constants.CODIGO_EXITO, Constants.MENSAJE_LISTADO_OK, Optional.of(empleado));
        } else{
            return new ResponseBase(Constants.CODIGO_ERROR, Constants.MENSAJE_NOT_LISTADO, Optional.empty());
        }
    }
}
