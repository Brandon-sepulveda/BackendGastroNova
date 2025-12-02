package GastroNova.gastro_nova.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import GastroNova.gastro_nova.model.Ruta;
import GastroNova.gastro_nova.model.RutasGuardadas;
import GastroNova.gastro_nova.model.Usuario;
import GastroNova.gastro_nova.repository.RutaRepository;
import GastroNova.gastro_nova.repository.RutasGuardadasRepository;
import GastroNova.gastro_nova.repository.UsuarioRepository;

@Service
public class RutaGuardadaService {

    @Autowired
    private RutasGuardadasRepository rutasGuardadasRepository;

    @Autowired
    private RutaRepository rutaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Guarda una ruta como favorita para un usuario.
     * Devuelve:
     *  - true  si se guardó correctamente
     *  - false si el usuario/ruta no existen o ya estaba guardada
     */
    public boolean guardarRutaFavorita(int usuarioId, int rutaId) {

        // 1) Verificar que existan usuario y ruta
        if (!usuarioRepository.existsById(usuarioId) || !rutaRepository.existsById(rutaId)) {
            return false;
        }

        // 2) Verificar que no esté ya guardada
        if (rutasGuardadasRepository.existsByIdUsuarioIdAndIdRutaId(usuarioId, rutaId)) {
            return false;
        }

        // 3) Cargar las entidades
        Usuario usuario = usuarioRepository.findById(usuarioId).get();
        Ruta ruta = rutaRepository.findById(rutaId).get();

        // 4) Crear la entidad de unión
        RutasGuardadas rg = new RutasGuardadas(usuario, ruta);

        // 5) Guardar en la BD
        rutasGuardadasRepository.save(rg);

        return true;
    }

    /**
     * Lista todas las rutas favoritas de un usuario.
     * Devuelve directamente una lista de Ruta para que el front la pinte fácil.
     */
    public List<Ruta> listarFavoritasPorUsuario(int usuarioId) {
        List<RutasGuardadas> registros = rutasGuardadasRepository.findByIdUsuarioId(usuarioId);

        // Mapeamos cada registro a su Ruta
        return registros.stream()
                .map(RutasGuardadas::getRuta)
                .collect(Collectors.toList());
    }

    /**
     * Elimina una ruta favorita de un usuario.
     * Devuelve:
     *  - true  si se eliminó
     *  - false si no existía
     */
    public boolean eliminarRutaFavorita(int usuarioId, int rutaId) {

        if (!rutasGuardadasRepository.existsByIdUsuarioIdAndIdRutaId(usuarioId, rutaId)) {
            return false;
        }

        rutasGuardadasRepository.deleteByIdUsuarioIdAndIdRutaId(usuarioId, rutaId);
        return true;
    }
}
