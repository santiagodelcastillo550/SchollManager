package com.example.SchoolManager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.SchoolManager.entities.Asignatura;
import com.example.SchoolManager.entities.Nota;
import com.example.SchoolManager.entities.Usuario;
import com.example.SchoolManager.repositories.NotaRepository;
import com.example.SchoolManager.repositories.UsuarioRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class AlumnoController {

	@Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private NotaRepository notaRepository;

    @GetMapping("/alumno/dashboard")
    public String dashboardAlumno(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuario == null || usuario.getRol() == null || !usuario.getRol().name().equals("ALUMNO")) {
            return "redirect:/login";
        }

        Usuario alumno = usuarioRepository.findById(usuario.getId()).orElse(null);
        if (alumno == null) return "redirect:/login";

        List<Asignatura> asignaturas = alumno.getAsignaturasCursadas();
        List<Nota> notas = notaRepository.findByAlumno(alumno);

        model.addAttribute("alumno", alumno);
        model.addAttribute("asignaturas", asignaturas);
        model.addAttribute("notas", notas);
        return "alumno_dashboard";
    }
}
