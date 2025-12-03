-- Agregar columna 'orden' a la tabla ruta_sugerida
ALTER TABLE ruta_sugerida
ADD COLUMN orden INT NULL AFTER descripcion;
