package com.example.SchoolManager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.SchoolManager.entities.Rol;
import com.example.SchoolManager.repositories.AsignaturaRepository;
import com.example.SchoolManager.repositories.NotaRepository;
import com.example.SchoolManager.repositories.UsuarioRepository;

@Controller
public class AdminController {

	@Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @Autowired
    private NotaRepository notaRepository;

    @GetMapping("/admin/dashboard")
    public String dashboardAdmin(Model model) {

        long totalProfesores = usuarioRepository.countByRol(Rol.PROFESOR);
        long totalAlumnos = usuarioRepository.countByRol(Rol.ALUMNO);
        long totalAsignaturas = asignaturaRepository.count();

        Double promedioGlobal = notaRepository.promedioGlobal();
        if (promedioGlobal == null) promedioGlobal = 0.0;

        model.addAttribute("totalProfesores", totalProfesores);
        model.addAttribute("totalAlumnos", totalAlumnos);
        model.addAttribute("totalAsignaturas", totalAsignaturas);
        model.addAttribute("promedioGlobal", promedioGlobal);

        model.addAttribute("usuarios", usuarioRepository.findAll());
        model.addAttribute("asignaturas", asignaturaRepository.findAll());

        return "admin_dashboard";
    }
}
