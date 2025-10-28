-- ==========================
-- USUARIOS
-- ==========================
INSERT INTO usuario (nombre, apellido, email, password, rol)
VALUES ('Admin', 'Principal', 'admin@school.com', 'admin', 'ADMIN');

INSERT INTO usuario (nombre, apellido, email, password, rol)
VALUES ('Juan', 'Pérez', 'juan.perez@school.com', '1234', 'PROFESOR');

INSERT INTO usuario (nombre, apellido, email, password, rol)
VALUES ('Ana', 'Gómez', 'ana.gomez@school.com', '1234', 'ALUMNO');

INSERT INTO usuario (nombre, apellido, email, password, rol)
VALUES ('Luis', 'Martínez', 'luis.martinez@school.com', '1234', 'ALUMNO');


-- ==========================
-- ASIGNATURAS
-- ==========================
INSERT INTO asignatura (nombre, descripcion, profesor_id)
VALUES ('Matemáticas', 'Álgebra y geometría',
        (SELECT id FROM usuario WHERE email = 'juan.perez@school.com'));

INSERT INTO asignatura (nombre, descripcion, profesor_id)
VALUES ('Historia', 'Historia mundial',
        (SELECT id FROM usuario WHERE email = 'juan.perez@school.com'));


-- ==========================
-- RELACIÓN ALUMNOS-ASIGNATURAS
-- ==========================
INSERT INTO asignaturas_alumnos (asignatura_id, alumno_id)
VALUES (
    (SELECT id FROM asignatura WHERE nombre = 'Matemáticas'),
    (SELECT id FROM usuario WHERE email = 'ana.gomez@school.com')
);

INSERT INTO asignaturas_alumnos (asignatura_id, alumno_id)
VALUES (
    (SELECT id FROM asignatura WHERE nombre = 'Matemáticas'),
    (SELECT id FROM usuario WHERE email = 'luis.martinez@school.com')
);

INSERT INTO asignaturas_alumnos (asignatura_id, alumno_id)
VALUES (
    (SELECT id FROM asignatura WHERE nombre = 'Historia'),
    (SELECT id FROM usuario WHERE email = 'ana.gomez@school.com')
);


-- ==========================
-- NOTAS
-- ==========================
INSERT INTO nota (valor, fecha, alumno_id, asignatura_id)
VALUES (
    8.5, '2025-10-22',
    (SELECT id FROM usuario WHERE email = 'ana.gomez@school.com'),
    (SELECT id FROM asignatura WHERE nombre = 'Matemáticas')
);

INSERT INTO nota (valor, fecha, alumno_id, asignatura_id)
VALUES (
    9.0, '2025-10-22',
    (SELECT id FROM usuario WHERE email = 'luis.martinez@school.com'),
    (SELECT id FROM asignatura WHERE nombre = 'Matemáticas')
);

INSERT INTO nota (valor, fecha, alumno_id, asignatura_id)
VALUES (
    7.5, '2025-10-22',
    (SELECT id FROM usuario WHERE email = 'ana.gomez@school.com'),
    (SELECT id FROM asignatura WHERE nombre = 'Historia')
);


-- ==========================
-- ASISTENCIAS
-- ==========================
INSERT INTO asistencia (fecha, presente, alumno_id, asignatura_id)
VALUES (
    '2025-10-22', TRUE,
    (SELECT id FROM usuario WHERE email = 'ana.gomez@school.com'),
    (SELECT id FROM asignatura WHERE nombre = 'Matemáticas')
);

INSERT INTO asistencia (fecha, presente, alumno_id, asignatura_id)
VALUES (
    '2025-10-22', TRUE,
    (SELECT id FROM usuario WHERE email = 'luis.martinez@school.com'),
    (SELECT id FROM asignatura WHERE nombre = 'Matemáticas')
);

INSERT INTO asistencia (fecha, presente, alumno_id, asignatura_id)
VALUES (
    '2025-10-22', FALSE,
    (SELECT id FROM usuario WHERE email = 'ana.gomez@school.com'),
    (SELECT id FROM asignatura WHERE nombre = 'Historia')
);
