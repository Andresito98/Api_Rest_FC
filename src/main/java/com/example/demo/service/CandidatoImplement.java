package com.example.demo.service;


import com.example.demo.entity.Candidato;
import com.example.demo.repository.CandidatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CandidatoImplement implements CandidatoService{

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private CandidatoRepository candidatoRepository;

    @Override
    public Candidato findByCorreoElectronico(String correoElectronico) {return candidatoRepository.findByCorreoElectronico(correoElectronico);}

    @Override
    public Candidato registrar(Candidato u) {
        return candidatoRepository.save(u);
    }




}
