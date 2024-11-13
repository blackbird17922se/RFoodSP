INSERT INTO rol VALUES (1,'Administrador');

CREATE TABLE usuario(
    nombre VARCHAR(100),
    apellido VARCHAR(100),
    nom_usuario VARCHAR(50) UNIQUE,
    contrasena VARCHAR(255),
    id_rol INT,
    FOREIGN KEY (id_rol) REFERENCES rol(id_rol)
);

ALTER TABLE usuario ADD COLUMN activo BOOLEAN NOT NULL;

