package GastroNova.gastro_nova.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import GastroNova.gastro_nova.model.RutasGuardadas;
import GastroNova.gastro_nova.model.RutasGuardadasId;

@Repository
public interface RutasGuardadasRepository extends JpaRepository<RutasGuardadas, RutasGuardadasId> {

    @Query("""
           select (count(rg) > 0)
           from RutasGuardadas rg
           where rg.id.usuarioId = :usuarioId and rg.id.rutaId = :rutaId
           """)
    boolean existsFavorito(@Param("usuarioId") int usuarioId,
                           @Param("rutaId") int rutaId);

    @Query("""
           select rg
           from RutasGuardadas rg
           where rg.id.usuarioId = :usuarioId
           """)
    List<RutasGuardadas> findAllByUsuarioId(@Param("usuarioId") int usuarioId);

    @Modifying
    @Transactional
    @Query("""
           delete from RutasGuardadas rg
           where rg.id.usuarioId = :usuarioId and rg.id.rutaId = :rutaId
           """)
    int deleteFavorito(@Param("usuarioId") int usuarioId,
                       @Param("rutaId") int rutaId);
}
