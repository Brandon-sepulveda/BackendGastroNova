package GastroNova.gastro_nova.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import GastroNova.gastro_nova.model.Empresa;



@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {



}
