package org.codigo.msregistro.application.controller.advice;

import org.codigo.msregistro.application.controller.personalizada.EmpleadoException;
import org.codigo.msregistro.domain.aggregates.constans.Constants;
import org.codigo.msregistro.domain.aggregates.response.ResponseBase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.util.Optional;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseBase> manejandoExcepciones(Exception ex){
        // Aqui manejo exclusivamente lo que sucede cuando capturo una expresion general.
        ResponseBase response = new ResponseBase(Constants.CODIGO_ERROR, "ERROR INTERNO DEL SERVIDOR ---> 500: " + ex.getMessage(), Optional.empty());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ResponseBase> manejandoNullPointer(NullPointerException ex){
        // Aqui manejo exclusivamente lo que sucede cuando capturo una expresion general.
        ResponseBase response = new ResponseBase(Constants.CODIGO_ERROR, "ERROR HAY UN DATO NULO ---> 409: " + ex.getMessage(), Optional.empty());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ResponseBase> manejandoIOException(IOException ex){
        // Aqui manejo exclusivamente lo que sucede cuando capturo una expresion general.
        ResponseBase response = new ResponseBase(Constants.CODIGO_ERROR, "ERROR EN DATOS DE I/O ---> 406: " + ex.getMessage(), Optional.empty());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseBase> manejandoRuntimeException(RuntimeException ex){
        // Aqui manejo exclusivamente lo que sucede cuando capturo una expresion general.
        ResponseBase response = new ResponseBase(Constants.CODIGO_ERROR, "ERROR EN RUNTIME ---> 400: " + ex.getMessage(), Optional.empty());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EmpleadoException.class)
    public ResponseEntity<ResponseBase> manejandoPersonaException(EmpleadoException ex){
        // Aqui manejo exclusivamente lo que sucede cuando capturo una expresion general.
        ResponseBase response = new ResponseBase(Constants.CODIGO_ERROR, "ERROR EN EL EMPLEADO ---> 409" + ex.getMessage(), Optional.empty());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
}
