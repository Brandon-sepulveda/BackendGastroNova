package GastroNova.gastro_nova.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import GastroNova.gastro_nova.repository.RutaRepository;
import GastroNova.gastro_nova.repository.RutasGuardadasRepository;

@Service
public class RutaGuardadaService {

    @Autowired
    private RutasGuardadasRepository rutasGuardadasRepository;

    @Autowired
    private RutaRepository rutaRepository;




}
