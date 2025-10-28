package com.example.SchoolManager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.SchoolManager.entities.Asignatura;
import com.example.SchoolManager.entities.Usuario;
import com.example.SchoolManager.repositories.AsignaturaRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProfesorController {

	@Autowired
    private AsignaturaRepository asignaturaRepository;

    @GetMapping("/profesor/dashboard")
    public String dashboardProfesor(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuario == null || usuario.getRol() == null || !usuario.getRol().name().equals("PROFESOR")) {
            return "redirect:/login";
        }

        List<Asignatura> asignaturas = asignaturaRepository.findByProfesor(usuario);
        model.addAttribute("asignaturas", asignaturas);
        model.addAttribute("profesor", usuario);
        return "profesor_dashboard";
    }
}
