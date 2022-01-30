package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "addStudent")
public class Candidato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreCompleto;
    private String ciudad;
    private String pais;
    private String telefono;
    private String correoElectronico;
    private String presencialidad;
    private Boolean tipoTraslado;

    public Candidato() {
    }

    public Candidato(String nombreCompleto, String ciudad, String pais, String telefono, String correoElectronico, String presencialidad, Boolean tipoTraslado) {
        this.nombreCompleto = nombreCompleto;
        this.ciudad = ciudad;
        this.pais = pais;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.presencialidad = presencialidad;
        this.tipoTraslado = tipoTraslado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getPresencialidad() {
        return presencialidad;
    }

    public void setPresencialidad(String presencialidad) {
        this.presencialidad = presencialidad;
    }

    public Boolean getTipoTraslado() {
        return tipoTraslado;
    }

    public void setTipoTraslado(Boolean tipoTraslado) {
        this.tipoTraslado = tipoTraslado;
    }

    @Override
    public String toString() {
        return "Candidato{" +
                "id=" + id +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", pais='" + pais + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correoElectronico='" + correoElectronico + '\'' +
                ", presencialidad='" + presencialidad + '\'' +
                ", tipoTraslado=" + tipoTraslado +
                '}';
    }
} /**/

/*
insert into add_candidatos(nombreCompleto,ciudad,pais,telefono,correoElectronico,presencialidad,tipoTraslado) values ('Andres', 'Badajoz', 'España', '678102546', 'Andresitosp@gmail.com', 'remoto', true);
insert into add_candidatos(nombreCompleto,ciudad,pais,telefono,correoElectronico,presencialidad,tipoTraslado) values ('Pedro', 'Badajoz', 'España', '678102546', 'Andresitosp@gmail.com', 'remoto', true);
insert into add_candidatos(nombreCompleto,ciudad,pais,telefono,correoElectronico,presencialidad,tipoTraslado) values ('Juanfran', 'Badajoz', 'España', '678102546', 'Andresitosp@gmail.com', 'remoto', true);

 */