-- 1. Crear la base de datos (ejecutar conectado a "postgres" o cualquier otra)
CREATE DATABASE web;

-- 2. IMPORTANTE: reconectar a la base "web" antes de crear la tabla.
--    Si ejecutas esto con psql -f, la siguiente línea hace el cambio de base.
\c web

-- 3. Crear la tabla ya dentro de "web", con columna id
CREATE TABLE cliente (
    id SERIAL PRIMARY KEY,
    nombre CHAR(32)
);

-- 4. Datos de prueba
INSERT INTO cliente (nombre) VALUES ('Tito MC');
