package com.example.demo.rest;

import com.example.demo.entity.Candidato;
import com.example.demo.repository.CandidatoRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.jwt.JwtTokenUtil;
import com.example.demo.security.payload.*;
import com.example.demo.service.CandidatoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("/api/candidatos")
public class CandidatoController {

    @Autowired
    private CandidatoService candidatoService;

    private final Logger log = LoggerFactory.getLogger(CandidatoController.class);

    private final AuthenticationManager authManager;
    private final UserRepository userRepository;
    private final CandidatoRepository candidatoRepository;
    private final PasswordEncoder encoder;
    private final JwtTokenUtil jwtTokenUtil;

    public CandidatoController(CandidatoService candidatoService, AuthenticationManager authManager,
                               UserRepository userRepository,
                               CandidatoRepository candidatoRepository, PasswordEncoder encoder,
                               JwtTokenUtil jwtTokenUtil){
        this.candidatoService = candidatoService;
        this.authManager = authManager;
        this.userRepository = userRepository;
        this.candidatoRepository = candidatoRepository;
        this.encoder = encoder;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    //Lista todos los Candidatos
    @GetMapping("/listar")
    public List<Candidato> findAll(){
        return candidatoRepository.findAll();
    }

    @PostMapping("/addCandidato")
    public ResponseEntity<MessageResponse> register(@RequestBody RegisterRequest2 signUpRequest) {

        // Check 1: Correo Electronico
        if (candidatoRepository.existsBycorreoElectronico(signUpRequest.getCorreoElectronico())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: CorreoElectronico is already taken!"));
        }

        Candidato candidato = new Candidato(signUpRequest.getNombreCompleto(),
                signUpRequest.getCiudad(),
                signUpRequest.getPais(),
                signUpRequest.getTelefono(),
                signUpRequest.getCorreoElectronico(),
                signUpRequest.getPresencialidad(),
                signUpRequest.getTipoTraslado()
                );

        candidatoRepository.save(candidato);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }


    @PutMapping("/modificar")
    public ResponseEntity<Candidato> update(@RequestBody Candidato candidato){
        if(candidato.getId() == null ){
            log.warn("Trying to update a non existent candidates");
            return ResponseEntity.badRequest().build();
        }
        if(!candidatoRepository.existsById(candidato.getId())){
            log.warn("Trying to update a non existent candidates");
            return ResponseEntity.notFound().build();
        }

        Candidato result = candidatoRepository.save(candidato);
        return ResponseEntity.ok(result);
    }

    @ApiIgnore
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<Candidato> delete(@PathVariable Long id){

        if(!candidatoRepository.existsById(id)){
            log.warn("Trying to delete a non existent candidates");
            return ResponseEntity.notFound().build();
        }

        candidatoRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @ApiIgnore
    @DeleteMapping("/borrar/todo")
    public ResponseEntity<Candidato> deleteAll(){
        log.info("REST Request for delete all candidates");
        candidatoRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }



} /* */
