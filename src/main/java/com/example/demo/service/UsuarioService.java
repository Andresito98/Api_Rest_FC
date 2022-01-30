package com.example.demo.service;


import com.example.demo.entity.User;


public interface UsuarioService {
    public User findByUsername(String username);
    public User registrar(User u);
}
