package com.helio4.bancol.avaluos.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;

import com.helio4.bancol.avaluos.modelo.ComparacionMercadoLoteSinConstruccion;

public interface ComparacionMercadoLoteVentaRepository extends JpaRepository<ComparacionMercadoLoteSinConstruccion, Long> {

}
