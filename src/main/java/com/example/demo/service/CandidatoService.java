package com.example.demo.service;

import com.example.demo.entity.Candidato;


public interface CandidatoService {
    public Candidato findByCorreoElectronico(String correoElectronico);
    public Candidato registrar(Candidato u);

}
