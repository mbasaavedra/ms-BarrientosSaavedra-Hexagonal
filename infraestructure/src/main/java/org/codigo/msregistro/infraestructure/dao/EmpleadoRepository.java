package org.codigo.msregistro.infraestructure.dao;

import org.codigo.msregistro.infraestructure.entity.EmpleadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmpleadoRepository extends JpaRepository<EmpleadoEntity, Long> {
    Optional<EmpleadoEntity> findByNumDoc(String numeroDocumento);
    List<Optional<EmpleadoEntity>> findByEstado(boolean estado);
}
