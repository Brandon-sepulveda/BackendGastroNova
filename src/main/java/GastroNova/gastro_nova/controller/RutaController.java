package GastroNova.gastro_nova.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import GastroNova.gastro_nova.model.Ruta;
import GastroNova.gastro_nova.service.rutaService; // O RutaService si ya lo renombraste

@RestController
@RequestMapping("/ruta")
@CrossOrigin(origins = "*")
public class RutaController {

    @Autowired
    private rutaService rutaService; // O RutaService rutaService;

    // Registrar ruta
    @PostMapping("/register")
    public ResponseEntity<Boolean> registrarRuta(@RequestBody Ruta ruta) {
        try {
            // OJO: en tu service actual el método se llama almcanarRuta (con n)
            boolean ok = rutaService.almcanarRuta(ruta); 
            // Si lo corriges a almacenarRuta, cambia esta línea a:
            // boolean ok = rutaService.almacenarRuta(ruta);

            if (!ok) {
                // Si el nombre de la ruta ya existe
                return ResponseEntity.status(409).body(false);
            }

            return ResponseEntity.ok(true);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(false);
        }
    }

    // Listar rutas
    @GetMapping("/list")
    public ResponseEntity<List<Ruta>> listarRutas() {
        try {
            List<Ruta> rutas = rutaService.listar();
            return ResponseEntity.ok(rutas);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).build();
        }
    }
}
