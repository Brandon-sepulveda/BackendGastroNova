package GastroNova.gastro_nova.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import GastroNova.gastro_nova.model.Ruta;

@Repository
public interface RutaRepository extends JpaRepository<Ruta, Integer> {

    @Query("""
           select distinct r
           from Ruta r
           left join fetch r.restaurantesOrdenados rs
           left join fetch rs.restaurant
           """)
    List<Ruta> findAllWithRestaurantes();

    @Query("""
           select distinct r
           from Ruta r
           left join fetch r.restaurantesOrdenados rs
           left join fetch rs.restaurant
           where r.id in :ids
           """)
    List<Ruta> findAllWithRestaurantesByIds(@Param("ids") List<Integer> ids);
}
