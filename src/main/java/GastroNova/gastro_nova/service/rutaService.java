package GastroNova.gastro_nova.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import GastroNova.gastro_nova.model.Ruta;
import GastroNova.gastro_nova.repository.RutaRepository;

@Service
public class rutaService {

    @Autowired
    private RutaRepository rutaRepository;

    //Guardar ruta
    public boolean almcanarRuta(Ruta ruta){
        if (rutaRepository.findByNombre(ruta.getNombre()).isPresent()){
            return false;
        }else{
            rutaRepository.save(ruta);
            return true;
        }
    }

    //Listar ruta
    public List<Ruta> listar(){
        return rutaRepository.findAll();
    }

}
