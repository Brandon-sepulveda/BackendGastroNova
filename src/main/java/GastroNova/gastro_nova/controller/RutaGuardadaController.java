package GastroNova.gastro_nova.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import GastroNova.gastro_nova.dto.RutaDto;
import GastroNova.gastro_nova.service.RutaGuardadaService;

@RestController
@RequestMapping("/favoritos")
@CrossOrigin(origins = "*")
public class RutaGuardadaController {

    @Autowired
    private RutaGuardadaService rutaGuardadaService;

    public static class FavoritoRequest {
        public int usuarioId;
        public int rutaId;
    }

    @PostMapping("/guardar")
    public ResponseEntity<Void> guardarFavorito(@RequestBody FavoritoRequest body) {
        try {
            if (body == null || body.usuarioId <= 0 || body.rutaId <= 0) {
                return ResponseEntity.badRequest().build();
            }

            boolean ok = rutaGuardadaService.guardarRutaFavorita(body.usuarioId, body.rutaId);
            if (!ok) {
                // ya existía o usuario/ruta no válidos
                return ResponseEntity.status(409).build();
            }

            return ResponseEntity.noContent().build(); // 204 sin body
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/list/{usuarioId}")
    public ResponseEntity<List<RutaDto>> listarFavoritos(@PathVariable int usuarioId) {
        try {
            if (usuarioId <= 0) {
                return ResponseEntity.badRequest().build();
            }

            List<RutaDto> rutas = rutaGuardadaService.listarFavoritasPorUsuario(usuarioId);
            return ResponseEntity.ok(rutas); // 200 [] si no hay
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<Void> eliminarFavorito(@RequestBody FavoritoRequest body) {
        try {
            if (body == null || body.usuarioId <= 0 || body.rutaId <= 0) {
                return ResponseEntity.badRequest().build();
            }

            boolean ok = rutaGuardadaService.eliminarRutaFavorita(body.usuarioId, body.rutaId);
            if (!ok) {
                return ResponseEntity.status(404).build();
            }

            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
}
