package org.codigo.msregistro.application.controller;

import lombok.RequiredArgsConstructor;
import org.codigo.msregistro.application.controller.personalizada.EmpleadoException;
import org.codigo.msregistro.domain.aggregates.constans.Constants;
import org.codigo.msregistro.domain.aggregates.dto.EmpleadoDTO;
import org.codigo.msregistro.domain.aggregates.request.RequestEmpleado;
import org.codigo.msregistro.domain.aggregates.response.ResponseBase;
import org.codigo.msregistro.domain.ports.in.EmpleadoServiceIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/empleado")
@RequiredArgsConstructor
public class EmpleadoController {

    private final EmpleadoServiceIn empleadoServiceIn;

    @PostMapping
    public ResponseEntity<ResponseBase> registrar(@RequestBody RequestEmpleado empleado) {

        ResponseBase responseBase = empleadoServiceIn.crearEmpleadoIn(empleado);

        if(responseBase.getCode() == Constants.CODIGO_EXITO)
        {
            return ResponseEntity.ok(responseBase);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBase);
        }
    }

    @PutMapping({"/actualizar/{id}"})
    public ResponseEntity<ResponseBase> actualizar(@PathVariable String id, @RequestBody EmpleadoDTO empleadoDTO)
    {
        ResponseBase responseBase = empleadoServiceIn.actualizarIn(Long.valueOf(id), empleadoDTO);

        if(responseBase.getCode() == Constants.CODIGO_EXITO)
        {
            return ResponseEntity.ok(responseBase);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBase);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<ResponseBase> eliminar(@PathVariable Long id){
        ResponseBase responseBase = empleadoServiceIn.eliminarEmpleadoIn(Long.valueOf(id));

        if(responseBase.getCode() == Constants.CODIGO_EXITO)
        {
            return ResponseEntity.ok(responseBase);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBase);
        }
    }

    @GetMapping("/xdocumento/{numeroDocumento}")
    public ResponseEntity<ResponseBase> buscarXDocumento(@PathVariable String numeroDocumento){
        ResponseBase responseBase = empleadoServiceIn.buscarXDocumento(numeroDocumento);
        if(responseBase.getCode() == Constants.CODIGO_EXITO) {
            return ResponseEntity.ok(responseBase);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBase);
        }
    }

    @GetMapping("/xestado/{estado}")
    public ResponseEntity<ResponseBase> buscarXEstado(@PathVariable String estado){
        ResponseBase responseBase = empleadoServiceIn.buscarTodos(Boolean.parseBoolean(estado));
        if(responseBase.getCode() == Constants.CODIGO_EXITO) {
            return ResponseEntity.ok(responseBase);
        } else {
            throw new EmpleadoException(" **** NO se encontró estados.");
            // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBase);
        }
    }

}
