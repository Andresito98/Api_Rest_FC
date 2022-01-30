package com.example.demo.security.payload;

public class RegisterRequest2 {

    private String nombreCompleto;
    private String ciudad;
    private String pais;
    private String telefono;
    private String correoElectronico;
    private String presencialidad;
    private Boolean tipoTraslado;

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

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }
/*public String getCorreoElectronico_register() {
        return correoElectronico;
    }
    public void setCorreoElectronico_register(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }*/
}
