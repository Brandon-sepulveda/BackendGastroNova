-- ====================================================================================
--  ESQUEMA BASE - GASTRONOVA
--  (MySQL 8+)
-- ====================================================================================
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- =====================
-- 1) TABLAS BÁSICAS
-- =====================
CREATE TABLE region (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  nombre VARCHAR(120) NOT NULL UNIQUE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE comuna (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  nombre VARCHAR(120) NOT NULL,
  region_id BIGINT NOT NULL,
  UNIQUE KEY uq_comuna (nombre, region_id),
  CONSTRAINT fk_comuna_region FOREIGN KEY (region_id) REFERENCES region(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Usuario simple (puedes ampliar luego)
CREATE TABLE usuario (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  usuario VARCHAR(120) NOT NULL UNIQUE,
  contrasena VARCHAR(255) NOT NULL,
  nombre VARCHAR(150),
  email VARCHAR(200),
  creado_en TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE tematica (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  nombre VARCHAR(120) NOT NULL UNIQUE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Para simplificar el seed inicial, la dirección se guarda en texto.
-- (Más adelante puedes normalizar a una tabla Direccion si quieres.)
CREATE TABLE restaurant (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  nombre VARCHAR(150) NOT NULL,
  direccion_text VARCHAR(255) NOT NULL,
  descripcion VARCHAR(600),
  UNIQUE KEY uq_restaurant_nombre (nombre)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE ruta (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  nombre VARCHAR(150) NOT NULL UNIQUE,
  descripcion VARCHAR(600),
  tematica_id BIGINT NULL,
  CONSTRAINT fk_ruta_tematica FOREIGN KEY (tematica_id) REFERENCES tematica(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Relación N:M entre ruta y restaurant
CREATE TABLE ruta_sugerida (
  ruta_id BIGINT NOT NULL,
  restaurant_id BIGINT NOT NULL,
  PRIMARY KEY (ruta_id, restaurant_id),
  CONSTRAINT fk_rs_ruta FOREIGN KEY (ruta_id) REFERENCES ruta(id),
  CONSTRAINT fk_rs_rest FOREIGN KEY (restaurant_id) REFERENCES restaurant(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;

-- =====================
-- 2) SEEDS INICIALES
-- =====================

-- 2.1 Regiones de Chile (16)
INSERT INTO region (nombre) VALUES
('Arica y Parinacota'),
('Tarapacá'),
('Antofagasta'),
('Atacama'),
('Coquimbo'),
('Valparaíso'),
('Región Metropolitana de Santiago'),
('Libertador General Bernardo O''Higgins'),
('Maule'),
('Ñuble'),
('Biobío'),
('La Araucanía'),
('Los Ríos'),
('Los Lagos'),
('Aysén del General Carlos Ibáñez del Campo'),
('Magallanes y de la Antártica Chilena');

-- 2.2 Comunas de la Región Metropolitana (52)
-- Tomamos el id de la RM por subconsulta
INSERT INTO comuna (nombre, region_id) VALUES
('Santiago', (SELECT id FROM region WHERE nombre='Región Metropolitana de Santiago')),
('Cerrillos', (SELECT id FROM region WHERE nombre='Región Metropolitana de Santiago')),
('Cerro Navia', (SELECT id FROM region WHERE nombre='Región Metropolitana de Santiago')),
('Conchalí', (SELECT id FROM region WHERE nombre='Región Metropolitana de Santiago')),
('El Bosque', (SELECT id FROM region WHERE nombre='Región Metropolitana de Santiago')),
('Estación Central', (SELECT id FROM region WHERE nombre='Región Metropolitana de Santiago')),
('Huechuraba', (SELECT id FROM region WHERE nombre='Región Metropolitana de Santiago')),
('Independencia', (SELECT id FROM region WHERE nombre='Región Metropolitana de Santiago')),
('La Cisterna', (SELECT id FROM region WHERE nombre='Región Metropolitana de Santiago')),
('La Florida', (SELECT id FROM region WHERE nombre='Región Metropolitana de Santiago')),
('La Granja', (SELECT id FROM region WHERE nombre='Región Metropolitana de Santiago')),
('La Pintana', (SELECT id FROM region WHERE nombre='Región Metropolitana de Santiago')),
('La Reina', (SELECT id FROM region WHERE nombre='Región Metropolitana de Santiago')),
('Las Condes', (SELECT id FROM region WHERE nombre='Región Metropolitana de Santiago')),
('Lo Barnechea', (SELECT id FROM region WHERE nombre='Región Metropolitana de Santiago')),
('Lo Espejo', (SELECT id FROM region WHERE nombre='Región Metropolitana de Santiago')),
('Lo Prado', (SELECT id FROM region WHERE nombre='Región Metropolitana de Santiago')),
('Macul', (SELECT id FROM region WHERE nombre='Región Metropolitana de Santiago')),
('Maipú', (SELECT id FROM region WHERE nombre='Región Metropolitana de Santiago')),
('Ñuñoa', (SELECT id FROM region WHERE nombre='Región Metropolitana de Santiago')),
('Pedro Aguirre Cerda', (SELECT id FROM region WHERE nombre='Región Metropolitana de Santiago')),
('Peñalolén', (SELECT id FROM region WHERE nombre='Región Metropolitana de Santiago')),
('Providencia', (SELECT id FROM region WHERE nombre='Región Metropolitana de Santiago')),
('Pudahuel', (SELECT id FROM region WHERE nombre='Región Metropolitana de Santiago')),
('Quilicura', (SELECT id FROM region WHERE nombre='Región Metropolitana de Santiago')),
('Quinta Normal', (SELECT id FROM region WHERE nombre='Región Metropolitana de Santiago')),
('Recoleta', (SELECT id FROM region WHERE nombre='Región Metropolitana de Santiago')),
('Renca', (SELECT id FROM region WHERE nombre='Región Metropolitana de Santiago')),
('San Joaquín', (SELECT id FROM region WHERE nombre='Región Metropolitana de Santiago')),
('San Miguel', (SELECT id FROM region WHERE nombre='Región Metropolitana de Santiago')),
('San Ramón', (SELECT id FROM region WHERE nombre='Región Metropolitana de Santiago')),
('Vitacura', (SELECT id FROM region WHERE nombre='Región Metropolitana de Santiago')),
('Colina', (SELECT id FROM region WHERE nombre='Región Metropolitana de Santiago')),
('Lampa', (SELECT id FROM region WHERE nombre='Región Metropolitana de Santiago')),
('Tiltil', (SELECT id FROM region WHERE nombre='Región Metropolitana de Santiago')),
('Puente Alto', (SELECT id FROM region WHERE nombre='Región Metropolitana de Santiago')),
('Pirque', (SELECT id FROM region WHERE nombre='Región Metropolitana de Santiago')),
('San José de Maipo', (SELECT id FROM region WHERE nombre='Región Metropolitana de Santiago')),
('Buin', (SELECT id FROM region WHERE nombre='Región Metropolitana de Santiago')),
('Calera de Tango', (SELECT id FROM region WHERE nombre='Región Metropolitana de Santiago')),
('Paine', (SELECT id FROM region WHERE nombre='Región Metropolitana de Santiago')),
('San Bernardo', (SELECT id FROM region WHERE nombre='Región Metropolitana de Santiago')),
('Alhué', (SELECT id FROM region WHERE nombre='Región Metropolitana de Santiago')),
('Curacaví', (SELECT id FROM region WHERE nombre='Región Metropolitana de Santiago')),
('María Pinto', (SELECT id FROM region WHERE nombre='Región Metropolitana de Santiago')),
('Melipilla', (SELECT id FROM region WHERE nombre='Región Metropolitana de Santiago')),
('San Pedro', (SELECT id FROM region WHERE nombre='Región Metropolitana de Santiago')),
('El Monte', (SELECT id FROM region WHERE nombre='Región Metropolitana de Santiago')),
('Isla de Maipo', (SELECT id FROM region WHERE nombre='Región Metropolitana de Santiago')),
('Padre Hurtado', (SELECT id FROM region WHERE nombre='Región Metropolitana de Santiago')),
('Peñaflor', (SELECT id FROM region WHERE nombre='Región Metropolitana de Santiago')),
('Talagante', (SELECT id FROM region WHERE nombre='Región Metropolitana de Santiago'));

-- 2.3 Usuario admin (usuario: admin / clave: admin)  **Nota**: solo DEV. En PROD usar hash (BCrypt).
INSERT INTO usuario (usuario, contrasena, nombre, email)
VALUES ('admin', 'admin', 'Administrador General', 'admin@gastronova.local');

-- 2.4 Temáticas
INSERT INTO tematica (nombre) VALUES ('Tematica unica'), ('Tematica mixta');

-- =====================
-- 3) RUTAS Y RESTAURANTES
-- =====================

-- 3.1 Restaurantes base (deduplicados por nombre)
INSERT INTO restaurant (nombre, direccion_text, descripcion) VALUES
-- Ruta del ramen
('Ramen Kintaro', 'Monjitas 460, Santiago', 'Uno de los pioneros especializados en ramen auténtico en Santiago. Ofrecen variedades de caldo como miso, shio, shoyu y tonkotsu.'),
('Ramen Ryoma Providencia', 'General Holley 2312, Providencia', 'Restaurante japonés especializado en ramen tradicional con diferentes caldos y toppings, reconocido por su sabor auténtico.'),
('Ootoya – Ramen & Noodles House', 'Constitución 125, Providencia', 'Restaurante japonés moderno que ofrece ramen artesanal y platos de fideos, ideal para disfrutar en un ambiente relajado y contemporáneo.'),
('Isekai Ramen', 'Girardi 1236, Providencia', 'Pequeño local temático que mezcla cultura anime con ramen casero de excelente calidad. Recomendado para quienes buscan una experiencia distinta.'),
('Ramen One Independencia', 'Av. Independencia 565, Mall Barrio Independencia, Santiago', 'Ramen con fideos artesanales y recetas clásicas japonesas, ideal para quienes buscan buena calidad a precios accesibles.'),

-- Ruta del completo
('El Rey del Completo', 'Centro de Santiago', 'Local tradicional chileno especializado en completos, sandwiches y papas fritas, ideal para una experiencia rápida y auténtica.'),
('Dominó', 'Santiago, Región Metropolitana', 'Cadena dedicada al completo chileno, muy popular para comida rápida, sabor clásico y ambiente informal.'),
('Quick Lunch Alemán', 'Santiago, Región Metropolitana', 'Bar de comida rápida con completos y otras opciones rápidas, buena alternativa para un almuerzo ágil.'),
('El Completo.cl', 'Santiago, Región Metropolitana', 'Tienda con variedad de ingredientes e innovaciones del completo clásico.'),
('Pedro, Juan & Diego', 'Santiago, Región Metropolitana', 'Local que incluye completos en su carta junto a hamburguesas, ideal para quienes quieren explorar variantes del completo clásico.'),

-- Ruta a la italiana
('Ristorante Sole Mio', 'Moneda 1816, Santiago', 'Restaurante italiano clásico con pastas artesanales y ambiente acogedor.'),
('Piegari Ristorante', 'Santiago, Región Metropolitana', 'Cocina italiana tradicional con recetas familiares y ambiente elegante.'),
('La Fabbrica', 'Av. Ossa 123, La Reina, Santiago', 'Auténtica gastronomía italiana con pizzas napolitanas y pastas caseras.'),
('Italian Trattoria', 'Constitución 270, Providencia', 'Trattoria con pizzas, risottos y pastas en barrio Providencia.'),
('Bottega Rivoli', 'Santiago, Región Metropolitana', 'Restaurante italiano enfocado en pasta fresca y buena selección de vinos.'),

-- Ruta del sushi
('Matsuri', 'Hotel Mandarin Oriental, Las Condes', 'Restaurante de sushi de alta gama con toques nikkei y ambiente refinado.'),
('Osaka – Santiago', 'Av. Nueva Costanera 3736B, Vitacura', 'Experiencia de sushi nikkei con fusión peruana y japonesa.'),
('Do Sushi', 'Santiago, Región Metropolitana', 'Restaurante artesanal con rolls originales y excelente presentación.'),
('Kyoko Sushi', 'Amunátegui 646, Santiago / Av. Providencia 2571', 'Cadena con rolls variados, buen precio y sabor equilibrado.'),
('Niu Sushi', 'Santiago, Región Metropolitana', 'Cadena popular con gran variedad de rolls y combinaciones de sabores.'),

-- La gran ruta peruana
('La Mar Santiago', 'Nueva Costanera, Vitacura', 'Cocina peruana de mariscos inspirada en las cebicherías tradicionales del Perú.'),
('Zarita Restaurant Boutique', 'Santiago Centro', 'Especializado en comida peruana arequipeña con recetas criollas auténticas.'),
('El Gusto Peruano', 'Santiago Centro', 'Cevichería con ambiente casual y excelente relación precio/calidad.'),
('Sabores Aji Seco', 'Santiago Centro', 'Platos criollos y marinos peruanos en porciones abundantes.'),
('Sazón Peruana', 'Santiago Centro', 'Comida peruana tradicional con platos emblemáticos como lomo saltado y ají de gallina.'),

-- Ruta del chocolate
('Brussels Heart of Chocolate', 'Santa Magdalena 187, Providencia', 'Chocolatería gourmet con bombones, tortas y productos belgas premium.'),
('Club Chocolate', 'Ernesto Pinto Lagarrigue 192, Bellavista', 'Café-bar con postres y cocteles basados en chocolate artesanal.'),
('ChocolateCafé', 'Santiago Centro', 'Cafetería especializada en bebidas de cacao y repostería casera.'),
('La Piccola Italia Dulce', 'Paseo Ahumada 387, Santiago', 'Cafetería italiana con chocolates, cannolis y postres típicos.'),
('Club del Chocolate', 'Santiago, Región Metropolitana', 'Espacio gourmet para amantes del cacao con degustaciones y productos selectos.');

-- 3.2 Rutas gastronómicas
INSERT INTO ruta (nombre, descripcion, tematica_id) VALUES
('Ruta del ramen', 'Explora los mejores ramen de Santiago, con caldos intensos y fideos artesanales en distintos estilos japoneses.', (SELECT id FROM tematica WHERE nombre='Tematica unica')),
('Ruta del completo', 'Descubre los lugares más emblemáticos para probar el clásico completo chileno en sus distintas versiones.', (SELECT id FROM tematica WHERE nombre='Tematica unica')),
('Ruta a la italiana', 'Un recorrido por las mejores trattorias y ristorantes italianos de la Región Metropolitana.', (SELECT id FROM tematica WHERE nombre='Tematica unica')),
('Ruta del sushi', 'Una experiencia japonesa en Santiago con los rolls más frescos y creativos de la ciudad.', (SELECT id FROM tematica WHERE nombre='Tematica unica')),
('La gran ruta peruana', 'Viaja por los sabores del Perú sin salir de Santiago, con cebiches, lomo saltado y ajíes criollos.', (SELECT id FROM tematica WHERE nombre='Tematica unica')),
('Ruta del chocolate', 'Endulza el recorrido con los mejores lugares dedicados al chocolate artesanal y gourmet en Santiago.', (SELECT id FROM tematica WHERE nombre='Tematica unica'));

-- 3.3 Asociación RUTA ↔ RESTAURANT
-- Helper: función inline para buscar ids por nombre (empleamos subconsultas en cada insert)

-- Ruta del ramen
INSERT INTO ruta_sugerida (ruta_id, restaurant_id) VALUES
((SELECT id FROM ruta WHERE nombre='Ruta del ramen'), (SELECT id FROM restaurant WHERE nombre='Ramen Kintaro')),
((SELECT id FROM ruta WHERE nombre='Ruta del ramen'), (SELECT id FROM restaurant WHERE nombre='Ramen Ryoma Providencia')),
((SELECT id FROM ruta WHERE nombre='Ruta del ramen'), (SELECT id FROM restaurant WHERE nombre='Ootoya – Ramen & Noodles House')),
((SELECT id FROM ruta WHERE nombre='Ruta del ramen'), (SELECT id FROM restaurant WHERE nombre='Isekai Ramen')),
((SELECT id FROM ruta WHERE nombre='Ruta del ramen'), (SELECT id FROM restaurant WHERE nombre='Ramen One Independencia'));

-- Ruta del completo
INSERT INTO ruta_sugerida (ruta_id, restaurant_id) VALUES
((SELECT id FROM ruta WHERE nombre='Ruta del completo'), (SELECT id FROM restaurant WHERE nombre='El Rey del Completo')),
((SELECT id FROM ruta WHERE nombre='Ruta del completo'), (SELECT id FROM restaurant WHERE nombre='Dominó')),
((SELECT id FROM ruta WHERE nombre='Ruta del completo'), (SELECT id FROM restaurant WHERE nombre='Quick Lunch Alemán')),
((SELECT id FROM ruta WHERE nombre='Ruta del completo'), (SELECT id FROM restaurant WHERE nombre='El Completo.cl')),
((SELECT id FROM ruta WHERE nombre='Ruta del completo'), (SELECT id FROM restaurant WHERE nombre='Pedro, Juan & Diego'));

-- Ruta a la italiana
INSERT INTO ruta_sugerida (ruta_id, restaurant_id) VALUES
((SELECT id FROM ruta WHERE nombre='Ruta a la italiana'), (SELECT id FROM restaurant WHERE nombre='Ristorante Sole Mio')),
((SELECT id FROM ruta WHERE nombre='Ruta a la italiana'), (SELECT id FROM restaurant WHERE nombre='Piegari Ristorante')),
((SELECT id FROM ruta WHERE nombre='Ruta a la italiana'), (SELECT id FROM restaurant WHERE nombre='La Fabbrica')),
((SELECT id FROM ruta WHERE nombre='Ruta a la italiana'), (SELECT id FROM restaurant WHERE nombre='Italian Trattoria')),
((SELECT id FROM ruta WHERE nombre='Ruta a la italiana'), (SELECT id FROM restaurant WHERE nombre='Bottega Rivoli'));

-- Ruta del sushi
INSERT INTO ruta_sugerida (ruta_id, restaurant_id) VALUES
((SELECT id FROM ruta WHERE nombre='Ruta del sushi'), (SELECT id FROM restaurant WHERE nombre='Matsuri')),
((SELECT id FROM ruta WHERE nombre='Ruta del sushi'), (SELECT id FROM restaurant WHERE nombre='Osaka – Santiago')),
((SELECT id FROM ruta WHERE nombre='Ruta del sushi'), (SELECT id FROM restaurant WHERE nombre='Do Sushi')),
((SELECT id FROM ruta WHERE nombre='Ruta del sushi'), (SELECT id FROM restaurant WHERE nombre='Kyoko Sushi')),
((SELECT id FROM ruta WHERE nombre='Ruta del sushi'), (SELECT id FROM restaurant WHERE nombre='Niu Sushi'));

-- La gran ruta peruana
INSERT INTO ruta_sugerida (ruta_id, restaurant_id) VALUES
((SELECT id FROM ruta WHERE nombre='La gran ruta peruana'), (SELECT id FROM restaurant WHERE nombre='La Mar Santiago')),
((SELECT id FROM ruta WHERE nombre='La gran ruta peruana'), (SELECT id FROM restaurant WHERE nombre='Zarita Restaurant Boutique')),
((SELECT id FROM ruta WHERE nombre='La gran ruta peruana'), (SELECT id FROM restaurant WHERE nombre='El Gusto Peruano')),
((SELECT id FROM ruta WHERE nombre='La gran ruta peruana'), (SELECT id FROM restaurant WHERE nombre='Sabores Aji Seco')),
((SELECT id FROM ruta WHERE nombre='La gran ruta peruana'), (SELECT id FROM restaurant WHERE nombre='Sazón Peruana'));

-- Ruta del chocolate
INSERT INTO ruta_sugerida (ruta_id, restaurant_id) VALUES
((SELECT id FROM ruta WHERE nombre='Ruta del chocolate'), (SELECT id FROM restaurant WHERE nombre='Brussels Heart of Chocolate')),
((SELECT id FROM ruta WHERE nombre='Ruta del chocolate'), (SELECT id FROM restaurant WHERE nombre='Club Chocolate')),
((SELECT id FROM ruta WHERE nombre='Ruta del chocolate'), (SELECT id FROM restaurant WHERE nombre='ChocolateCafé')),
((SELECT id FROM ruta WHERE nombre='Ruta del chocolate'), (SELECT id FROM restaurant WHERE nombre='La Piccola Italia Dulce')),
((SELECT id FROM ruta WHERE nombre='Ruta del chocolate'), (SELECT id FROM restaurant WHERE nombre='Club del Chocolate'));
