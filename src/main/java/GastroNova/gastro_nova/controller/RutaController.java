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

import GastroNova.gastro_nova.dto.RutaCreateRequest;
import GastroNova.gastro_nova.dto.RutaDto;
import GastroNova.gastro_nova.service.RutaService;

@RestController
@RequestMapping("/ruta")
@CrossOrigin(origins = "*")
public class RutaController {

    @Autowired
    private RutaService rutaService;

    @GetMapping("/list")
    public ResponseEntity<List<RutaDto>> listarRutas() {
        try {
            List<RutaDto> rutas = rutaService.listarRutasConRestaurantes();
            return ResponseEntity.ok(rutas);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<RutaDto> crearRuta(@RequestBody RutaCreateRequest request) {
        try {
            RutaDto dto = rutaService.crearRutaConRestaurantes(request);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
}
