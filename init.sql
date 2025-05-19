CREATE TABLE Registro_vendedor (
                                   id SERIAL PRIMARY KEY,
                                   nombre VARCHAR(255),
                                   apellido VARCHAR(255),
                                   cedula VARCHAR(255) UNIQUE,
                                   correo VARCHAR(255) UNIQUE
);

CREATE TABLE Registro_Cliente (
                                  id SERIAL PRIMARY KEY,
                                  nombre VARCHAR(255),
                                  apellido VARCHAR(255),
                                  cedula VARCHAR(255) UNIQUE,
                                  correo VARCHAR(255) UNIQUE,
                                  telefono VARCHAR(255),
                                  tipo_cliente VARCHAR(50) CHECK (tipo_cliente IN ('nuevo', 'frecuente'))
);

CREATE TABLE Direccion (
                           id SERIAL PRIMARY KEY,
                           cliente INT,
                           calle VARCHAR(255),
                           avenida VARCHAR(255),
                           numero_casa VARCHAR(255),
                           codigo_postal VARCHAR(255),
                           ciudad VARCHAR(255),
                           pais VARCHAR(255),
                           FOREIGN KEY (cliente) REFERENCES Registro_Cliente(id)
);

CREATE TABLE Persona (
                         id SERIAL PRIMARY KEY,
                         nombre VARCHAR(255),
                         apellido VARCHAR(255)
);

CREATE TABLE Compras (
                         id SERIAL PRIMARY KEY,
                         cliente INT,
                         fecha_compra TIMESTAMP,
                         monto_total DECIMAL(10, 2),
                         estado VARCHAR(50) CHECK (estado IN ('pagado', 'pendiente')),
                         metodo_pago VARCHAR(50) CHECK (metodo_pago IN ('tarjeta', 'efectivo')),
                         FOREIGN KEY (cliente) REFERENCES Registro_Cliente(id)
);

CREATE TABLE Carrito (
                         id SERIAL PRIMARY KEY,
                         cliente INT,
                         fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         estado VARCHAR(50) CHECK (estado IN ('activo', 'abandonado', 'comprado')),
                         FOREIGN KEY (cliente) REFERENCES Registro_Cliente(id)
);

CREATE TABLE Productos (
                           id SERIAL PRIMARY KEY,
                           nombre VARCHAR(255),
                           descripcion VARCHAR(255),
                           precio_unitario DECIMAL(10, 2),
                           stock INT,
                           tipo VARCHAR(255),
                           material VARCHAR(255)
);

CREATE TABLE Detalle_compra (
                                id SERIAL PRIMARY KEY,
                                compra_id INT,
                                producto_id INT,
                                cantidad INT,
                                precio_unitario DECIMAL(10, 2),
                                subtotal DECIMAL(10, 2),
                                FOREIGN KEY (compra_id) REFERENCES Compras(id),
                                FOREIGN KEY (producto_id) REFERENCES Productos(id)
);

CREATE TABLE Carrito_Producto (
                                  id SERIAL PRIMARY KEY,
                                  carrito_id INT,
                                  producto_id INT,
                                  cantidad INT,
                                  subtotal DECIMAL(10, 2),
                                  FOREIGN KEY (carrito_id) REFERENCES Carrito(id),
                                  FOREIGN KEY (producto_id) REFERENCES Productos(id)
);
