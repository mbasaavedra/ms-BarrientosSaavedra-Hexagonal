package org.codigo.msregistro.infraestructure.adapters;

import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.codigo.msregistro.domain.aggregates.constans.Constants;
import org.codigo.msregistro.domain.aggregates.dto.EmpleadoDTO;
import org.codigo.msregistro.domain.aggregates.request.RequestEmpleado;
import org.codigo.msregistro.domain.aggregates.response.ResponseBase;
import org.codigo.msregistro.domain.aggregates.response.ResponseReniec;
import org.codigo.msregistro.domain.ports.out.EmpleadoServiceOut;
import org.codigo.msregistro.infraestructure.dao.EmpleadoRepository;
import org.codigo.msregistro.infraestructure.entity.EmpleadoEntity;
import org.codigo.msregistro.infraestructure.mapper.EmpleadoMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class EmpleadoAdapter implements EmpleadoServiceOut {

    private final EmpleadoRepository empleadoRepository;
    private final EmpleadoMapper empleadoMapper;

    private final RestTemplate restTemplate;

    //@Value("${token}")
    //private String token;

    @Override
    public EmpleadoDTO crearEmpleadonaOut(RequestEmpleado empleado) {
        EmpleadoEntity empleadoEntity = getEntityRestTemplate (empleado);
        if(empleadoEntity != null) {
            return empleadoMapper.mapToDto(empleadoRepository.save(empleadoEntity));
        }else{
            return null;
        }
    }


    // Metodo que se utiliza para RestTemplate
    private EmpleadoEntity getEntityRestTemplate(RequestEmpleado requestEmpleado){
        String url = "https://api.apis.net.pe/v2/reniec/dni?numero="+requestEmpleado.getNumDoc();
        String token = "apis-token-8988.xC-vZETdPEGtjAumNCx1H6k9VJVbtZQX";

        try
        {
            ResponseEntity<ResponseReniec> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    new HttpEntity<>(createHeaders(token)),
                    ResponseReniec.class
            );

            ResponseReniec responseReniec = response.getBody();
            EmpleadoEntity empleadoEntity = new EmpleadoEntity();

            empleadoEntity.setNombre(responseReniec.getNombres());
            empleadoEntity.setApellidos(responseReniec.getApellidoPaterno() + ' ' + responseReniec.getApellidoMaterno());
            empleadoEntity.setTipoDoc(responseReniec.getTipoDocumento());
            empleadoEntity.setNumDoc(responseReniec.getNumeroDocumento());
            empleadoEntity.setEstado(Constants.ESTADO_ACTIVO);
            empleadoEntity.setUsuaCrea(Constants.USU_ADMIN);
            empleadoEntity.setDateCrea(new Timestamp(System.currentTimeMillis()));
            return empleadoEntity;
        } catch(HttpClientErrorException e) {
            System.err.println("ERROR AL CONSUMIR EL API EXTERNA " + e.getMessage());
        }

        return null;
    }

    private HttpHeaders createHeaders(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization","Bearer " + token);
        return headers;
    }

    @Override
    public EmpleadoDTO actualizarOut(Long id, EmpleadoDTO empleadoDTO) {
        Optional<EmpleadoEntity> empleadoEntity = empleadoRepository.findById(id);

        if (empleadoEntity.isPresent()) {
            EmpleadoEntity empleado = empleadoEntity.get();
            empleado.setNombre(empleadoDTO.getNombre());
            empleado.setApellidos(empleadoDTO.getApellidos());
            empleado.setEdad(empleadoDTO.getEdad());
            empleado.setCargo(empleadoDTO.getCargo());
            empleado.setTipoDoc(empleadoDTO.getTipoDoc());
            empleado.setNumDoc(empleadoDTO.getNumDoc());
            empleado.setDepartamento(empleadoDTO.getDepartamento());
            empleado.setSalario(empleadoDTO.getSalario());
            empleado.setTelefono(empleadoDTO.getTelefono());
            empleado.setCorreo(empleadoDTO.getCorreo());
            empleado.setEstado(empleadoDTO.isEstado());
            empleado.setDireccion(empleadoDTO.getDireccion());
            empleado.setUsuaUpdate(Constants.USU_ADMIN);
            empleado.setDateUpdate(new Timestamp(System.currentTimeMillis()));

            return empleadoMapper.mapToDto(empleadoRepository.save(empleado));
        }else {
            return null;
        }
    }

    @Override
    public EmpleadoDTO eliminarOut(Long id) {
        Optional<EmpleadoEntity> empleadoRecuperado = empleadoRepository.findById(id);
        if(empleadoRecuperado.isPresent()){
            EmpleadoEntity empresa = empleadoRecuperado.get();
            empresa.setEstado(Constants.ESTADO_INACTIVO);
            empresa.setUsuaDelete(Constants.USU_ADMIN);
            empresa.setDateDelete(new Timestamp(System.currentTimeMillis()));
            return empleadoMapper.mapToDto(empleadoRepository.save(empresa));
        }
        return null;
    }

    @Override
    public EmpleadoDTO buscarXNumDocu(String numeroDocumento) {
        Optional<EmpleadoEntity> empleado = empleadoRepository.findByNumDoc(numeroDocumento);
        if(empleado.isPresent()){
            return empleadoMapper.mapToDto(empleado.get());
        }else{
            return null;
        }
    }

    @Override
    public List<EmpleadoDTO> buscarTodos(boolean estado) {
        List<Optional<EmpleadoEntity>> empleado = empleadoRepository.findByEstado(estado);
        if(empleado.isEmpty() ){
            return Collections.singletonList(empleadoMapper.mapToDto((EmpleadoEntity) empleado));
        }else{
            return null;
        }
    }
}