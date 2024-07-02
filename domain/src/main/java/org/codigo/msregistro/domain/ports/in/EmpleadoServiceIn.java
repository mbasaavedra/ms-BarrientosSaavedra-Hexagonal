package org.codigo.msregistro.domain.ports.in;

import org.codigo.msregistro.domain.aggregates.dto.EmpleadoDTO;
import org.codigo.msregistro.domain.aggregates.request.RequestEmpleado;
import org.codigo.msregistro.domain.aggregates.response.ResponseBase;

public interface EmpleadoServiceIn {
    ResponseBase crearEmpleadoIn(RequestEmpleado requestEmpleado);

    ResponseBase actualizarIn(Long id, EmpleadoDTO empleadoDTO);

    ResponseBase eliminarEmpleadoIn(Long id);

    ResponseBase buscarXDocumento(String numDoc);

    ResponseBase buscarTodos(boolean estado);
}
