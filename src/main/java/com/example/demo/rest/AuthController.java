package com.example.demo.rest;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.jwt.JwtTokenUtil;
import com.example.demo.security.payload.JwtResponse;
import com.example.demo.security.payload.LoginRequest;
import com.example.demo.security.payload.MessageResponse;
import com.example.demo.security.payload.RegisterRequest;
import com.example.demo.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * Controlador para llevar a cabo la autenticación utilizando JWT
 *
 * Se utiliza AuthenticationManager para autenticar las credenciales que son el
 * usuario y password que llegan por POST en el cuerpo de la petición
 *
 * Si las credenciales son válidas se genera un token JWT como respuesta
 */
// @CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    private final Logger log = LoggerFactory.getLogger(AuthController.class);

    private final AuthenticationManager authManager;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtTokenUtil jwtTokenUtil;

    public AuthController(AuthenticationManager authManager,
            UserRepository userRepository,
            PasswordEncoder encoder,
            JwtTokenUtil jwtTokenUtil){
        this.authManager = authManager;
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.jwtTokenUtil = jwtTokenUtil;
    }


    //Lista todos los usuarios
    @GetMapping("/listar")
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @GetMapping("/login9")
    public ModelAndView login(Model model){
        model.addAttribute("users", new User());
        return new ModelAndView ("login.html");
    }


    @GetMapping("/page1")
    public ModelAndView indexX(Model model){
        return new ModelAndView ("page1.html");
    }

    @GetMapping("/fichaAlumno")
    public ModelAndView fichaAlumno(Model model){
        return new ModelAndView ("FichaAlumno.html");
    }


    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest loginRequest){

        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenUtil.generateJwtToken(authentication);

        // UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> register(@RequestBody RegisterRequest signUpRequest) {

        // Check 1: username
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        // Check 2: email
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }


    @PutMapping("/listar")
    public ResponseEntity<User> update(@RequestBody User user){
        if(user.getId() == null ){ // si no tiene id quiere decir que sí es una creación
            log.warn("Trying to update a non existent users");
            return ResponseEntity.badRequest().build();
        }
        if(!userRepository.existsById(user.getId())){
            log.warn("Trying to update a non existent users");
            return ResponseEntity.notFound().build();
        }

        // El proceso de actualización
        User result = userRepository.save(user);
        return ResponseEntity.ok(result); // el libro devuelto tiene una clave primaria
    }

    @ApiIgnore
    @DeleteMapping("/listar/{id}")
    public ResponseEntity<User> delete(@PathVariable Long id){

        if(!userRepository.existsById(id)){
            log.warn("Trying to delete a non existent users");
            return ResponseEntity.notFound().build();
        }

        userRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @ApiIgnore // ignorar este método para que no aparezca en la documentación de la api Swagger
    @DeleteMapping("/listar/todo")
    public ResponseEntity<User> deleteAll(){
        log.info("REST Request for delete all users");
        userRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }


} /**/
