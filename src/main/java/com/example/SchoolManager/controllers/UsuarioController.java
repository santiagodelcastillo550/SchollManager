package com.example.SchoolManager.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.SchoolManager.entities.Rol;
import com.example.SchoolManager.entities.Usuario;
import com.example.SchoolManager.repositories.UsuarioRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsuarioController {

	@Autowired
    private UsuarioRepository usuarioRepository;

    // Mostrar formulario de login
    @GetMapping("/login")
    public String mostrarLogin(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "login";
    }

    // Procesar login
    @PostMapping("/login")
    public String procesarLogin(@ModelAttribute Usuario usuario, Model model, HttpSession session) {
        Optional<Usuario> existente = usuarioRepository.findByEmail(usuario.getEmail());

        if (existente.isPresent()) {
            Usuario u = existente.get();

            // comparacion directa sin encoder (ajusta si usas encoder)
            if (u.getPassword().equals(usuario.getPassword())) {
                // Guardamos usuario en sesión
                session.setAttribute("usuarioLogueado", u);

                // Redirige según enum Rol
                if (u.getRol() == Rol.PROFESOR) {
                    return "redirect:/profesor/dashboard";
                } else if (u.getRol() == Rol.ALUMNO) {
                    return "redirect:/alumno/dashboard";
                } else {
                    return "redirect:/";
                }
            }
        }

        model.addAttribute("error", "Correo o contraseña incorrectos");
        return "login";
    }



    // Mostrar formulario de registro
    @GetMapping("/register")
    public String mostrarRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "register";
    }

    // Procesar registro
    @PostMapping("/register")
    public String procesarRegistro(@ModelAttribute Usuario usuario, Model model) {
        Optional<Usuario> existente = usuarioRepository.findByEmail(usuario.getEmail());

        if (existente.isPresent()) {
            model.addAttribute("error", "Ya existe un usuario con ese correo electrónico");
            return "register";
        }

        // Guarda el usuario directamente
        usuarioRepository.save(usuario);
        model.addAttribute("mensaje", "Registro exitoso. Ahora puedes iniciar sesión.");
        return "login";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
