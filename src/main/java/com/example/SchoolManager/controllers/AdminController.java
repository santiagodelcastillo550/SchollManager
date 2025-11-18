package com.example.SchoolManager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.SchoolManager.entities.Asignatura;
import com.example.SchoolManager.entities.Rol;
import com.example.SchoolManager.entities.Usuario;
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
    
    @GetMapping("/admin/crear-profesor")
    public String mostrarFormularioCrearProfesor(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "admin/crear_profesor";
    }

    @PostMapping("/admin/guardar-profesor")
    public String guardarProfesor(@ModelAttribute Usuario usuario,
                                  RedirectAttributes redirectAttributes) {

        usuario.setRol(Rol.PROFESOR); // IMPORTANTE
        usuarioRepository.save(usuario);

        redirectAttributes.addFlashAttribute("mensaje", "Profesor añadido correctamente.");
        return "redirect:/admin/dashboard";
    }
    
    @GetMapping("/admin/crear-alumno")
    public String mostrarFormularioAlumno(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "admin/crear_alumno";
    }

    @PostMapping("/admin/guardar-alumno")
    public String crearAlumno(@ModelAttribute("usuario") Usuario usuario) {

        usuario.setRol(Rol.ALUMNO);
        usuarioRepository.save(usuario);

        return "redirect:/admin/dashboard";
    }
    
    @GetMapping("/admin/crear-asignatura")
    public String mostrarFormularioAsignatura(Model model) {

        model.addAttribute("asignatura", new Asignatura());

        return "admin/crear_asignatura";
    }

    @PostMapping("/admin/guardar-asignatura")
    public String guardarAsignatura(@ModelAttribute("asignatura") Asignatura asignatura) {

        asignaturaRepository.save(asignatura);
        return "redirect:/admin/dashboard";
    }
    
    @GetMapping("/admin/asignar-asignatura-profesor")
    public String mostrarAsignarAsignaturaProfesor(Model model) {

        model.addAttribute("profesores", usuarioRepository.findByRol(Rol.PROFESOR));
        model.addAttribute("asignaturas", asignaturaRepository.findAll());

        return "admin/asignar_asignatura_profesor";
    }
    
    @PostMapping("/admin/asignar-asignatura-profesor")
    public String asignarAsignaturaProfesor(
            @RequestParam Long profesorId,
            @RequestParam Long asignaturaId) {

        Usuario profesor = usuarioRepository.findById(profesorId).orElse(null);
        Asignatura asignatura = asignaturaRepository.findById(asignaturaId).orElse(null);

        if (profesor != null && asignatura != null) {
            asignatura.setProfesor(profesor);
            asignaturaRepository.save(asignatura);
        }

        return "redirect:/admin/dashboard";
    }


    @GetMapping("/admin/asignar-asignatura-alumno")
    public String mostrarAsignarAsignaturaAlumno(Model model) {

        model.addAttribute("alumnos", usuarioRepository.findByRol(Rol.ALUMNO));
        model.addAttribute("asignaturas", asignaturaRepository.findAll());

        return "admin/asignar_asignatura_alumno";
    }

    @PostMapping("/admin/asignar-asignatura-alumno")
    public String asignarAsignaturaAlumno(
            @RequestParam Long alumnoId,
            @RequestParam Long asignaturaId) {

        Usuario alumno = usuarioRepository.findById(alumnoId).orElse(null);
        Asignatura asignatura = asignaturaRepository.findById(asignaturaId).orElse(null);

        if (alumno != null && asignatura != null) {

            // Añadir alumno solo si no está ya
            if (!asignatura.getAlumnos().contains(alumno)) {
                asignatura.getAlumnos().add(alumno);
                asignaturaRepository.save(asignatura);
            }
        }

        return "redirect:/admin/dashboard";
    }
}
