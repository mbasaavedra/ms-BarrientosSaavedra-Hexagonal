package org.codigo.msregistro.infraestructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name ="empleados")
@Getter
@Setter
public class EmpleadoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado")
    private Long id;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 150)
    private String apellidos;

    @Column(name = "edad")
    private Integer edad;

    @Column(name = "cargo")
    private String cargo;

    @Column(name = "tipo_doc")
    private String tipoDoc;

    @Column(name = "num_docu", nullable = false, length = 15)
    private String numDoc;

    @Column(name = "departamento")
    private String departamento;

    @Column(name = "salario")
    private Double salario;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "correo")
    private String correo;

    @Column(name = "estado", nullable = false)
    private boolean estado;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "date_crea")
    private Timestamp dateCrea;

    @Column(name = "usua_crea", length = 45)
    private String usuaCrea;

    @Column(name = "date_upd")
    private Timestamp dateUpdate;

    @Column(name = "usua_upd", length = 45)
    private String usuaUpdate;

    @Column(name = "date_dele")
    private Timestamp dateDelete;

    @Column(name = "usua_dele", length = 45)
    private String usuaDelete;
}
