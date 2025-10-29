package com.example.SchoolManager.bussiness;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SchoolManager.entities.Nota;
import com.example.SchoolManager.repositories.AsignaturaRepository;
import com.example.SchoolManager.repositories.NotaRepository;
import com.example.SchoolManager.repositories.UsuarioRepository;

@Service
public class NotaService {

	@Autowired
    private NotaRepository notaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    public void actualizarONuevaNota(Long alumnoId, Long asignaturaId, Double valor) {
        Optional<Nota> existente = notaRepository.findByAlumnoIdAndAsignaturaId(alumnoId, asignaturaId);
        Nota nota = existente.orElseGet(() -> {
            Nota n = new Nota();
            n.setAlumno(usuarioRepository.findById(alumnoId).orElseThrow());
            n.setAsignatura(asignaturaRepository.findById(asignaturaId).orElseThrow());
            return n;
        });

        nota.setValor(valor);
        nota.setFecha(LocalDate.now());
        notaRepository.save(nota);
    }
}
