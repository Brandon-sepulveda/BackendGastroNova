package GastroNova.gastro_nova.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import GastroNova.gastro_nova.model.Ruta;
import GastroNova.gastro_nova.service.RutaGuardadaService;

@RestController
@RequestMapping("/favoritos")
@CrossOrigin(origins = "*")
public class RutaGuardadaController {

    @Autowired
    private RutaGuardadaService rutaGuardadaService;

    // DTO simple para recibir usuarioId y rutaId desde el front
    static class FavoritoRequest {
        public int usuarioId;
        public int rutaId;
    }

    // ============================
    // 1) GUARDAR RUTA EN FAVORITOS
    // ============================

    @PostMapping("/guardar")
    public ResponseEntity<Boolean> guardarFavorito(@RequestBody FavoritoRequest body) {
        try {
            boolean ok = rutaGuardadaService.guardarRutaFavorita(body.usuarioId, body.rutaId);

            if (!ok) {
                // Puede ser porque ya existía o porque usuario/ruta no existen
                return ResponseEntity.status(409).body(false);
            }

            return ResponseEntity.ok(true);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(false);
        }
    }

    // ======================================
    // 2) LISTAR TODAS LAS RUTAS FAVORITAS
    //    DE UN USUARIO
    // ======================================

    @GetMapping("/list/{usuarioId}")
    public ResponseEntity<List<Ruta>> listarFavoritos(@PathVariable int usuarioId) {
        try {
            List<Ruta> rutasFavoritas = rutaGuardadaService.listarFavoritasPorUsuario(usuarioId);
            return ResponseEntity.ok(rutasFavoritas);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).build();
        }
    }

    // ======================================
    // 3) ELIMINAR UNA RUTA DE FAVORITOS
    // ======================================

    @DeleteMapping("/eliminar")
    public ResponseEntity<Boolean> eliminarFavorito(@RequestBody FavoritoRequest body) {
        try {
            boolean ok = rutaGuardadaService.eliminarRutaFavorita(body.usuarioId, body.rutaId);

            if (!ok) {
                // No existía ese favorito
                return ResponseEntity.status(404).body(false);
            }

            return ResponseEntity.ok(true);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(false);
        }
    }
}
