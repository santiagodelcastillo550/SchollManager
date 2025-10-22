-- Usuarios
INSERT INTO usuario (id, nombre, apellido, email, password, rol) VALUES (1, 'Admin', 'Principal', 'admin@school.com', 'admin', 'ADMIN');
INSERT INTO usuario (id, nombre, apellido, email, password, rol) VALUES (2, 'Juan', 'Pérez', 'juan.perez@school.com', '1234', 'PROFESOR');
INSERT INTO usuario (id, nombre, apellido, email, password, rol) VALUES (3, 'Ana', 'Gómez', 'ana.gomez@school.com', '1234', 'ALUMNO');
INSERT INTO usuario (id, nombre, apellido, email, password, rol) VALUES (4, 'Luis', 'Martínez', 'luis.martinez@school.com', '1234', 'ALUMNO');

-- Asignaturas
INSERT INTO asignatura (id, nombre, descripcion, profesor_id) VALUES (1, 'Matemáticas', 'Álgebra y geometría', 2);
INSERT INTO asignatura (id, nombre, descripcion, profesor_id) VALUES (2, 'Historia', 'Historia mundial', 2);

-- Relación alumnos - asignaturas (tabla intermedia asignaturas_alumnos)
INSERT INTO asignaturas_alumnos (asignatura_id, alumno_id) VALUES (1, 3);
INSERT INTO asignaturas_alumnos (asignatura_id, alumno_id) VALUES (1, 4);
INSERT INTO asignaturas_alumnos (asignatura_id, alumno_id) VALUES (2, 3);

-- Notas
INSERT INTO nota (id, valor, fecha, alumno_id, asignatura_id) VALUES (1, 8.5, '2025-10-22', 3, 1);
INSERT INTO nota (id, valor, fecha, alumno_id, asignatura_id) VALUES (2, 9.0, '2025-10-22', 4, 1);
INSERT INTO nota (id, valor, fecha, alumno_id, asignatura_id) VALUES (3, 7.5, '2025-10-22', 3, 2);

-- Asistencias
INSERT INTO asistencia (id, fecha, presente, alumno_id, asignatura_id) VALUES (1, '2025-10-22', TRUE, 3, 1);
INSERT INTO asistencia (id, fecha, presente, alumno_id, asignatura_id) VALUES (2, '2025-10-22', TRUE, 4, 1);
INSERT INTO asistencia (id, fecha, presente, alumno_id, asignatura_id) VALUES (3, '2025-10-22', FALSE, 3, 2);
