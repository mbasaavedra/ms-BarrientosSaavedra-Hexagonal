package org.codigo.msregistro.domain.ports.out;

import org.codigo.msregistro.domain.aggregates.dto.EmpleadoDTO;
import org.codigo.msregistro.domain.aggregates.request.RequestEmpleado;

import java.util.List;

public interface EmpleadoServiceOut {
    EmpleadoDTO crearEmpleadonaOut(RequestEmpleado empleado);

    EmpleadoDTO actualizarOut(Long id, EmpleadoDTO empleadoDTO);

    EmpleadoDTO eliminarOut(Long id);

    EmpleadoDTO buscarXNumDocu(String numeroDocumento);

    List<EmpleadoDTO> buscarTodos(boolean estado);
}
