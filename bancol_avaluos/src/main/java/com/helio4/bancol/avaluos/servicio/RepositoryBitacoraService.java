package com.helio4.bancol.avaluos.servicio;

import java.util.ArrayList;
import java.util.List;

import com.helio4.bancol.avaluos.servicio.datos.BitacoraService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.BitacoraDTO;
import com.helio4.bancol.avaluos.ensamblador.AvaluoEnsamblador;
import com.helio4.bancol.avaluos.modelo.Bitacora;
import com.helio4.bancol.avaluos.persistencia.BitacoraRepository;

@Component(value = "repositoryBitacoraService")
@Transactional(readOnly = true)
public class RepositoryBitacoraService implements BitacoraService {

    private static Logger log = LoggerFactory
            .getLogger(RepositoryBitacoraService.class);

    @Autowired
    private BitacoraRepository bitacoraRepository;

    @Autowired
    private AvaluoEnsamblador avaluoEnsamblador;

    @Transactional(readOnly = true)
    @Override
    public List<BitacoraDTO> encontrarBitacoraPorAvaluo(Long id) {
        List<BitacoraDTO> lista = bitacoraRepository
                .encontrarBitacoraPorAvaluo(id);
        return lista == null ? new ArrayList<BitacoraDTO>() : lista;
    }

    @Transactional
    @Override
    public BitacoraDTO guardar(BitacoraDTO bitacoraDTO) {
        Bitacora bitacora = bitacoraRepository.save(avaluoEnsamblador
                .crearBitacora(bitacoraDTO));
        bitacoraDTO.setId(bitacora.getId());
        return bitacoraDTO;
    }

}
