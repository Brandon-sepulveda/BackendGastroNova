package GastroNova.gastro_nova.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import GastroNova.gastro_nova.dto.RestaurantDto;
import GastroNova.gastro_nova.dto.RutaDto;
import GastroNova.gastro_nova.model.Restaurant;
import GastroNova.gastro_nova.model.Ruta;
import GastroNova.gastro_nova.model.RutaSugerida;
import GastroNova.gastro_nova.model.RutasGuardadas;
import GastroNova.gastro_nova.model.Usuario;
import GastroNova.gastro_nova.repository.RutaRepository;
import GastroNova.gastro_nova.repository.RutasGuardadasRepository;
import GastroNova.gastro_nova.repository.UsuarioRepository;

@Service
public class RutaGuardadaService {

    @Autowired private RutasGuardadasRepository rutasGuardadasRepository;
    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private RutaRepository rutaRepository;

    @Transactional
    public boolean guardarRutaFavorita(int usuarioId, int rutaId) {

        if (!usuarioRepository.existsById(usuarioId) || !rutaRepository.existsById(rutaId)) {
            return false;
        }

        if (rutasGuardadasRepository.existsFavorito(usuarioId, rutaId)) {
            return false;
        }

        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow();
        Ruta ruta = rutaRepository.findById(rutaId).orElseThrow();

        // ✅ ahora sí existe y no revienta por tipos
        RutasGuardadas rg = new RutasGuardadas(usuario, ruta);
        rutasGuardadasRepository.save(rg);

        return true;
    }

    @Transactional(readOnly = true)
    public List<RutaDto> listarFavoritasPorUsuario(int usuarioId) {

        List<RutasGuardadas> registros = rutasGuardadasRepository.findAllByUsuarioId(usuarioId);

        if (registros.isEmpty()) {
            return List.of(); // ✅ 200 []
        }

        List<Integer> ids = registros.stream()
                .map(rg -> rg.getId().getRutaId())
                .distinct()
                .collect(Collectors.toList());

        List<Ruta> rutas = rutaRepository.findAllWithRestaurantesByIds(ids);

        return rutas.stream()
                .map(this::mapRutaToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public boolean eliminarRutaFavorita(int usuarioId, int rutaId) {

        if (!rutasGuardadasRepository.existsFavorito(usuarioId, rutaId)) {
            return false;
        }

        rutasGuardadasRepository.deleteFavorito(usuarioId, rutaId);
        return true;
    }

    // =========================
    //        MAPEOS DTO
    // =========================
    private RutaDto mapRutaToDto(Ruta ruta) {

        List<RestaurantDto> restaurantes = ruta.getRestaurantesOrdenados()
                .stream()
                .map(RutaSugerida::getRestaurant)
                .filter(r -> r != null)
                .distinct()
                .map(this::mapRestaurantToDto)
                .collect(Collectors.toList());

        return new RutaDto(
                ruta.getId(),
                ruta.getNombre(),
                ruta.getDescripcion(),
                restaurantes
        );
    }

    private RestaurantDto mapRestaurantToDto(Restaurant restaurant) {
        String direccion = restaurant.getDireccionText();
        if (direccion == null || direccion.isBlank()) {
            direccion = "Dirección no especificada";
        }

        return new RestaurantDto(
                restaurant.getId(),
                restaurant.getNombre(),
                direccion,
                restaurant.getDescripcion()
        );
    }
}
