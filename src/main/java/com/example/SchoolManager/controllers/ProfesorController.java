package com.example.SchoolManager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.SchoolManager.bussiness.NotaService;
import com.example.SchoolManager.entities.Asignatura;
import com.example.SchoolManager.entities.Usuario;
import com.example.SchoolManager.repositories.AsignaturaRepository;
import com.example.SchoolManager.repositories.UsuarioRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProfesorController {

	@Autowired
    private AsignaturaRepository asignaturaRepository;
	
	@Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private NotaService notaService;

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
    
    @PostMapping("/profesor/actualizarNota")
    public String actualizarNota(@RequestParam Long asignaturaId,
                                 @RequestParam Long alumnoId,
                                 @RequestParam Double valor) {
        notaService.actualizarONuevaNota(alumnoId, asignaturaId, valor);
        return "redirect:/profesor/dashboard";
    }

}
