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

CREATE TABLE mesa(
    id_mesa INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50) NULL,
    disponible BOOLEAN NOT NULL,
    PRIMARY KEY (id_mesa)
);


CREATE TABLE producto(
    id_producto INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(200) NOT NULL,
    precio DECIMAL(8, 2) NULL,
    activo BOOLEAN DEFAULT TRUE,
);


CREATE TABLE orden(
	id_orden INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    entregado BOOLEAN NOT NULL,
    pagado BOOLEAN NOT NULL,
    observaciones VARCHAR(1000) NULL,
    id_mesero INT NOT NULL,  -- Apunta a la tabla usuario
    id_mesa INT NOT NULL,    -- Apunta a la tabla mesa
    activo BOOLEAN DEFAULT TRUE,
    CONSTRAINT fk_mesero FOREIGN KEY(id_mesero) REFERENCES usuario(id_usuario),
    CONSTRAINT fk_mesa FOREIGN KEY(id_mesa) REFERENCES mesa(id_mesa)
);

ALTER TABLE mesa ADD COLUMN activo BOOLEAN DEFAULT TRUE;
ALTER TABLE rol ADD COLUMN activo BOOLEAN DEFAULT TRUE;


CREATE TABLE venta (
	id_venta INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    fecha DATETIME DEFAULT NOW(),
    total DECIMAL (8, 2) NOT NULL,
    activo BOOLEAN DEFAULT TRUE,
    id_usuario INT NOT NULL COMMENT 'Id del vendedor, cajero o quien realiza la venta',
    id_orden INT NOT NULL,
    CONSTRAINT fk_usuario FOREIGN KEY(id_usuario) REFERENCES usuario(id_usuario),
    CONSTRAINT fk_orden FOREIGN KEY(id_orden) REFERENCES orden(id_orden),
    INDEX idx_usuario(id_usuario),
    INDEX idx_orden(id_orden)
);


CREATE TABLE venta_producto(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    precio DECIMAL(8,2) DEFAULT 0,
    cantidad INT DEFAULT 0,
    subtotal DECIMAL(8,2) DEFAULT 0,
    id_producto INT NOT NULL,
    id_venta INT NOT NULL,
    CONSTRAINT fk_producto FOREIGN KEY(id_producto) REFERENCES producto(id_producto),
    CONSTRAINT fk_venta FOREIGN KEY(id_venta) REFERENCES venta(id_venta)
);