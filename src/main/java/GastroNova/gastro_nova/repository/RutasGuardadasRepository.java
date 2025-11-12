package GastroNova.gastro_nova.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import GastroNova.gastro_nova.model.RutasGuardadas;
import GastroNova.gastro_nova.model.RutasGuardadasId;


public interface RutasGuardadasRepository extends JpaRepository<RutasGuardadas,RutasGuardadasId> {
    // buscar todas las rutas guardadas de un usuario
    List<RutasGuardadas> findByIdUsuarioId(int usuarioId);

    // buscar una ruta guardada específica
    Optional<RutasGuardadas> findByIdUsuarioIdAndIdRutaId(int usuarioId, int rutaId);

    // saber si existe
    boolean existsByIdUsuarioIdAndIdRutaId(int usuarioId, int rutaId);

    // borrar un favorito específico
    void deleteByIdUsuarioIdAndIdRutaId(int usuarioId, int rutaId);
}
