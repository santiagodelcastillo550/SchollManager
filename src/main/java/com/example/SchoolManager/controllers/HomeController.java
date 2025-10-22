package com.example.SchoolManager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.SchoolManager.repositories.AsignaturaRepository;
import com.example.SchoolManager.repositories.UsuarioRepository;

@Controller
public class HomeController {

	private final UsuarioRepository usuarioRepo;
    private final AsignaturaRepository asignaturaRepo;

    public HomeController(UsuarioRepository usuarioRepo, AsignaturaRepository asignaturaRepo) {
        this.usuarioRepo = usuarioRepo;
        this.asignaturaRepo = asignaturaRepo;
    }

    @GetMapping("/")
    public String home(Model model) {
        // Envío de datos de ejemplo al template
        model.addAttribute("usuarios", usuarioRepo.findAll());
        model.addAttribute("asignaturas", asignaturaRepo.findAll());
        return "index"; // Busca src/main/resources/templates/index.html
    }
}
