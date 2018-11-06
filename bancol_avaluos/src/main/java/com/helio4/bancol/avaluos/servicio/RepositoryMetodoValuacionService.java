package com.helio4.bancol.avaluos.servicio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.ComparacionMercadoLoteConstruccionRentaDTO;
import com.helio4.bancol.avaluos.dto.ComparacionMercadoLoteConstruccionVentaDTO;
import com.helio4.bancol.avaluos.dto.ComparacionMercadoLoteVentaDTO;
import com.helio4.bancol.avaluos.dto.ComparacionMercadoNOPHDTO;
import com.helio4.bancol.avaluos.dto.ComparacionMercadoPHDTO;
import com.helio4.bancol.avaluos.dto.ComparacionMercadoPHRentaDTO;
import com.helio4.bancol.avaluos.dto.ComparacionMercadoPHVentaDTO;
import com.helio4.bancol.avaluos.dto.MetodoValuacionDTO;
import com.helio4.bancol.avaluos.dto.OfertaDTO;
import com.helio4.bancol.avaluos.ensamblador.ComparacionMercadoNOPHEnsamblador;
import com.helio4.bancol.avaluos.ensamblador.ComparacionMercadoPHEnsamblador;
import com.helio4.bancol.avaluos.ensamblador.ComparacionMercadoPHVentaEnsamblador;
import com.helio4.bancol.avaluos.ensamblador.MetodoValuacionEnsamblador;
import com.helio4.bancol.avaluos.ensamblador.OfertaEnsamblador;
import com.helio4.bancol.avaluos.ensamblador.OfertaLoteConstruccionRentaEnsamblador;
import com.helio4.bancol.avaluos.ensamblador.OfertaLoteConstruccionVentaEnsamblador;
import com.helio4.bancol.avaluos.ensamblador.OfertaLoteSinConstruccionEnsamblador;
import com.helio4.bancol.avaluos.ensamblador.OfertaPHEnsamblador;
import com.helio4.bancol.avaluos.exception.ComparacionMercadoLoteConstruccionRentaNotFoundException;
import com.helio4.bancol.avaluos.exception.ComparacionMercadoLoteConstruccionVentaNotFoundException;
import com.helio4.bancol.avaluos.exception.ComparacionMercadoLoteVentaNotFoundException;
import com.helio4.bancol.avaluos.exception.ComparacionMercadoNOPHNotFoundException;
import com.helio4.bancol.avaluos.exception.ComparacionMercadoPHNotFoundException;
import com.helio4.bancol.avaluos.exception.ComparacionMercadoPHRentaNotFoundException;
import com.helio4.bancol.avaluos.exception.ComparacionMercadoPHVentaNotFoundException;
import com.helio4.bancol.avaluos.modelo.Avaluo_;
import com.helio4.bancol.avaluos.modelo.ComparacionMercadoNoPh;
import com.helio4.bancol.avaluos.modelo.ComparacionMercadoPh;
import com.helio4.bancol.avaluos.modelo.ComparacionMercadoPhVenta;
import com.helio4.bancol.avaluos.modelo.MetodoValuacion;
import com.helio4.bancol.avaluos.modelo.MetodoValuacion_;
import com.helio4.bancol.avaluos.persistencia.MetodoValuacionRepository;
import com.helio4.bancol.avaluos.servicio.datos.ComparacionMercadoLoteConstruccionRentaService;
import com.helio4.bancol.avaluos.servicio.datos.ComparacionMercadoLoteConstruccionVentaService;
import com.helio4.bancol.avaluos.servicio.datos.ComparacionMercadoLoteVentaService;
import com.helio4.bancol.avaluos.servicio.datos.ComparacionMercadoNOPHService;
import com.helio4.bancol.avaluos.servicio.datos.ComparacionMercadoPHRentaService;
import com.helio4.bancol.avaluos.servicio.datos.ComparacionMercadoPHService;
import com.helio4.bancol.avaluos.servicio.datos.ComparacionMercadoPHVentaService;
import com.helio4.bancol.avaluos.servicio.datos.MetodoValuacionService;
import com.helio4.bancol.avaluos.servicio.datos.OfertaService;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.MetodoValuacionNotFoundException;

@Component(value="repositoryMetodoValuacionService")
@Transactional(readOnly = true)
public class RepositoryMetodoValuacionService implements MetodoValuacionService {

	private static Logger log = LoggerFactory.getLogger( RepositoryMetodoValuacionService.class );

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private MetodoValuacionRepository metodoValuacionRepository;
	
	@Autowired
	private MetodoValuacionEnsamblador metodoValuacionEnsamblador;
	
	
	@Autowired
	private OfertaEnsamblador ofertaEnsamblador;
	
	@Autowired
	private OfertaPHEnsamblador ofertaPHEnsamblador;
	
	@Autowired
	private OfertaLoteSinConstruccionEnsamblador ofertaLoteSinConstruccionEnsamblador;
	
	@Autowired
	private OfertaLoteConstruccionVentaEnsamblador ofertaLoteConstruccionVentaEnsamblador;
	
	@Autowired
	private OfertaLoteConstruccionRentaEnsamblador ofertaLoteConstruccionRentaEnsamblador;
	
	@Autowired
	private OfertaService ofertaService;
	
	@Autowired
	private ComparacionMercadoPHService comparacionPHService;
	
	@Autowired
	private ComparacionMercadoPHVentaService comparacionPHVentaService;
	
	@Autowired
	private ComparacionMercadoPHRentaService comparacionPHRentaService;
	
	@Autowired
	private ComparacionMercadoLoteConstruccionVentaService comparacionMercadoLoteConstruccionVentaService;
	
	@Autowired
	private ComparacionMercadoLoteConstruccionRentaService comparacionMercadoLoteConstruccionRentaService;
	
	@Autowired
	private ComparacionMercadoLoteVentaService comparacionLoteVentaService;
	
	@Autowired
	private ComparacionMercadoNOPHService comparacionNOPHService;
	
	@Autowired
	private ComparacionMercadoPHEnsamblador comparacionPHEnsamblador;
	
	@Autowired
	private ComparacionMercadoNOPHEnsamblador comparacionNOPHEnsamblador;
	
	@Autowired
	private ComparacionMercadoPHVentaEnsamblador comparacionPHVentaEnsamblador;

	@Transactional
	@Override
	public MetodoValuacionDTO crear(MetodoValuacionDTO metodoValuacionDTO) {
		if( metodoValuacionDTO.getClass().equals( ComparacionMercadoPHDTO.class) ){
			 metodoValuacionDTO = this.comparacionPHService.crear((ComparacionMercadoPHDTO)metodoValuacionDTO);
		
		}
		else if( metodoValuacionDTO.getClass().equals( ComparacionMercadoNOPHDTO.class) ){
			metodoValuacionDTO  =  this.comparacionNOPHService.crear( (ComparacionMercadoNOPHDTO)metodoValuacionDTO);
		}else if(metodoValuacionDTO.getClass().equals( ComparacionMercadoPHVentaDTO.class)){
			metodoValuacionDTO = this.comparacionPHVentaService.crear((ComparacionMercadoPHVentaDTO)metodoValuacionDTO);
		}else if(metodoValuacionDTO.getClass().equals( ComparacionMercadoPHRentaDTO.class)){
			metodoValuacionDTO = this.comparacionPHRentaService.crear((ComparacionMercadoPHRentaDTO)metodoValuacionDTO);
		}else if(metodoValuacionDTO.getClass().equals( ComparacionMercadoLoteVentaDTO.class)){
			metodoValuacionDTO = this.comparacionLoteVentaService.crear((ComparacionMercadoLoteVentaDTO)metodoValuacionDTO);
		}else if(metodoValuacionDTO.getClass().equals( ComparacionMercadoLoteConstruccionVentaDTO.class)){
			metodoValuacionDTO = this.comparacionMercadoLoteConstruccionVentaService.crear((ComparacionMercadoLoteConstruccionVentaDTO)metodoValuacionDTO);
		}else if(metodoValuacionDTO.getClass().equals( ComparacionMercadoLoteConstruccionRentaDTO.class)){
			metodoValuacionDTO = this.comparacionMercadoLoteConstruccionRentaService.crear((ComparacionMercadoLoteConstruccionRentaDTO)metodoValuacionDTO);
		}
		else{
            log.debug("Creando un nuevo detalle de metodoValuacion {}",
                    metodoValuacionDTO);
			MetodoValuacion metodoValuacion;
			try {
				metodoValuacion = metodoValuacionEnsamblador.crearMetodoValuacion(metodoValuacionDTO);
			} catch (AvaluoNotFoundException e) {
                log.debug("AvaluoNotFoundException: no se encontró el avaluo asociado al metodoValuacion {}",
                        metodoValuacionDTO);
				return null;
			}
			metodoValuacionRepository.save(metodoValuacion);
			metodoValuacionDTO.setId( metodoValuacion.getId() );
			
		}
		//le indico a cada oferta  a que metodo pertenece.
		if( metodoValuacionDTO.getAvaluoId() !=null && metodoValuacionDTO.getOfertas()!=null  ){
			for( OfertaDTO ofertaDTO : metodoValuacionDTO.getOfertas() ){
				ofertaDTO.setMetodoValuacionId( metodoValuacionDTO.getId() );
			} 
			this.ofertaService.crearList( metodoValuacionDTO.getOfertas(), metodoValuacionDTO );
		}
		return metodoValuacionDTO;
	}

	@Transactional(rollbackFor = MetodoValuacionNotFoundException.class)
	@Override
	public MetodoValuacionDTO eliminar(MetodoValuacionDTO metodoValuacionDTO) throws MetodoValuacionNotFoundException {
        log.debug("Eliminando el metodoValuacion con id:  {}",
                metodoValuacionDTO.getId() );

		MetodoValuacion metodoValuacion = metodoValuacionRepository.findOne(metodoValuacionDTO.getId());
		if(metodoValuacionDTO.getClass().equals( ComparacionMercadoPHDTO.class) ){
			try {
				this.comparacionPHService.eliminar( metodoValuacionDTO.getId() );
				return this.comparacionPHEnsamblador.escribirDTO( (ComparacionMercadoPh)metodoValuacion);
			} catch (ComparacionMercadoPHNotFoundException e) {
				e.printStackTrace();
			}
		}
		else if( metodoValuacionDTO.getClass().equals( ComparacionMercadoNOPHDTO.class) ){
			try {
				this.comparacionNOPHService.eliminar( metodoValuacionDTO.getId() );
				return this.comparacionNOPHEnsamblador.escribirDTO( (ComparacionMercadoNoPh)metodoValuacion );
			} catch (ComparacionMercadoNOPHNotFoundException e) {
				e.printStackTrace();
			}
		}
		else if( metodoValuacionDTO.getClass().equals( ComparacionMercadoPHVentaDTO.class) ){
			try {
				this.comparacionPHVentaService.eliminar( metodoValuacionDTO.getId() );
				return this.comparacionPHVentaEnsamblador.escribirDTO( (ComparacionMercadoPhVenta)metodoValuacion );
			} catch (ComparacionMercadoPHVentaNotFoundException e) {
				e.printStackTrace();
			}
		}
		else if( metodoValuacionDTO.getClass().equals( ComparacionMercadoPHRentaDTO.class) ){
			try {
				this.comparacionPHRentaService.eliminar( metodoValuacionDTO.getId() );
				return null;
			}  catch (ComparacionMercadoPHRentaNotFoundException e) {
				e.printStackTrace();
			}
		}
		else if( metodoValuacionDTO.getClass().equals( ComparacionMercadoLoteVentaDTO.class) ){
			try {
				this.comparacionLoteVentaService.eliminar( metodoValuacionDTO.getId() );
				return null;
			} catch (ComparacionMercadoLoteVentaNotFoundException e) {
				e.printStackTrace();
			}
		}
		else if( metodoValuacionDTO.getClass().equals( ComparacionMercadoLoteConstruccionVentaDTO.class) ){
			try {
				this.comparacionMercadoLoteConstruccionVentaService.eliminar( metodoValuacionDTO.getId() );
				return null;
			}  catch (ComparacionMercadoLoteConstruccionVentaNotFoundException e) {
				e.printStackTrace();
			}
		}
		else if( metodoValuacionDTO.getClass().equals( ComparacionMercadoLoteConstruccionRentaDTO.class) ){
			try {
				this.comparacionMercadoLoteConstruccionRentaService.eliminar( metodoValuacionDTO.getId() );
				return null;
			} catch (ComparacionMercadoLoteConstruccionRentaNotFoundException e) {
				e.printStackTrace();
			}
		}
		else{
			if (metodoValuacion == null) {
				throw new MetodoValuacionNotFoundException();
			}
			metodoValuacionRepository.delete(metodoValuacion);
			
		}
		return metodoValuacionEnsamblador.escribirDTO(metodoValuacion);
		
	}

	@Transactional(readOnly = true)
	@Override
	public List<MetodoValuacionDTO> encontrarTodos() {
		log.debug("Encontrando todas las metodoValuacions");
		return metodoValuacionEnsamblador.escribirListaDTO(metodoValuacionRepository.findAll());
	}

	@Transactional(readOnly = true)
	@Override
	public MetodoValuacionDTO encontrarPorId(Long id) {
		return metodoValuacionEnsamblador.escribirDTO(metodoValuacionRepository.findOne(id));
	}

	@Transactional(rollbackFor = MetodoValuacionNotFoundException.class)
	@Override
	public MetodoValuacionDTO actualizar(MetodoValuacionDTO actualizado)
	throws MetodoValuacionNotFoundException {
		if( actualizado.getClass().equals( ComparacionMercadoPHDTO.class) ){
			try {
				this.comparacionPHService.actualizar( (ComparacionMercadoPHDTO) actualizado);
			} catch (ComparacionMercadoPHNotFoundException e) {
                log.debug("ComparacionMercadoPHNotFoundException: no se encontró La comparación de Mercado PH:  {}",
                        actualizado);
				e.printStackTrace();
			}
		}
		else if( actualizado.getClass().equals( ComparacionMercadoNOPHDTO.class) ){
			try {
				this.comparacionNOPHService.actualizar( (ComparacionMercadoNOPHDTO)actualizado );
			} catch (ComparacionMercadoNOPHNotFoundException e) {
                log.debug("ComparacionMercadoNOPHNotFoundException: no se encontró La comparación de Mercado NO  PH:  {}",
                        actualizado);
				e.printStackTrace();
			}
		}else if( actualizado.getClass().equals( ComparacionMercadoPHVentaDTO.class) ){
			try {
				this.comparacionPHVentaService.actualizar( (ComparacionMercadoPHVentaDTO)actualizado );
				//enviar ofertas PH asociadas
				ofertaPHEnsamblador.actualizarOfertas(((ComparacionMercadoPHVentaDTO)actualizado).getOfertasPH(), actualizado);
				
			} catch (ComparacionMercadoPHVentaNotFoundException e) {
                log.debug("ComparacionMercadoPHVentaNotFoundException: no se encontró La comparación de Mercado PH Venta:  {}",
                        actualizado);
				e.printStackTrace();
			}
		}else if( actualizado.getClass().equals( ComparacionMercadoPHRentaDTO.class) ){
			try {
				this.comparacionPHRentaService.actualizar( (ComparacionMercadoPHRentaDTO)actualizado );
				//enviar ofertas PH asociadas
				ofertaPHEnsamblador.actualizarOfertas(((ComparacionMercadoPHRentaDTO)actualizado).getOfertasPH(), actualizado);
				
			} catch (ComparacionMercadoPHRentaNotFoundException e) {
                log.debug("ComparacionMercadoPHRentaNotFoundException: no se encontró La comparación de Mercado PH Renta:  {}",
                        actualizado);
				e.printStackTrace();
			}
		}else if( actualizado.getClass().equals( ComparacionMercadoLoteVentaDTO.class) ){
			try {
				this.comparacionLoteVentaService.actualizar( (ComparacionMercadoLoteVentaDTO)actualizado );
				//enviar ofertas PH asociadas
				ofertaLoteSinConstruccionEnsamblador.actualizarOfertas(((ComparacionMercadoLoteVentaDTO)actualizado).getOfertasLoteSinConstruccion(), actualizado);
				
			} catch (ComparacionMercadoLoteVentaNotFoundException e) {
                log.debug("ComparacionMercadoPHRentaNotFoundException: no se encontró La comparación de Mercado PH Renta:  {}",
                        actualizado);
				e.printStackTrace();
			}
		}else if( actualizado.getClass().equals( ComparacionMercadoLoteConstruccionVentaDTO.class) ){
			try {
				this.comparacionMercadoLoteConstruccionVentaService.actualizar( (ComparacionMercadoLoteConstruccionVentaDTO)actualizado );
				//enviar ofertas PH asociadas
				ofertaLoteConstruccionVentaEnsamblador.actualizarOfertas(((ComparacionMercadoLoteConstruccionVentaDTO)actualizado).getOfertasConstruccionVenta(), actualizado);
				
			} catch (ComparacionMercadoLoteConstruccionVentaNotFoundException e) {
                log.debug("ComparacionMercadoPHRentaNotFoundException: no se encontró La comparación de Mercado PH Renta:  {}",
                        actualizado);
				e.printStackTrace();
			}
		}else if( actualizado.getClass().equals( ComparacionMercadoLoteConstruccionRentaDTO.class) ){
			try {
				this.comparacionMercadoLoteConstruccionRentaService.actualizar( (ComparacionMercadoLoteConstruccionRentaDTO)actualizado );
				//enviar ofertas PH asociadas
				ofertaLoteConstruccionRentaEnsamblador.actualizarOfertas(((ComparacionMercadoLoteConstruccionRentaDTO)actualizado).getOfertasConstruccionRenta(), actualizado);
				
			} catch (ComparacionMercadoLoteConstruccionRentaNotFoundException e) {
                log.debug("ComparacionMercadoPHRentaNotFoundException: no se encontró La comparación de Mercado PH Renta:  {}",
                        actualizado);
				e.printStackTrace();
			}
		}
		else{
			try {
				metodoValuacionEnsamblador.actualizarMetodoValuacion(actualizado.getId(), actualizado);				
			} catch (AvaluoNotFoundException e) {
                log.debug("AvaluoNotFoundException: no se encontró el avaluo asociado al metodoValuacion {}",
                        actualizado);
				return null;
			}
		}
		if( actualizado.getOfertas()!=null && !actualizado.getOfertas().isEmpty() ){
			List<OfertaDTO> ofertasDTO  = actualizado.getOfertas();
			for( OfertaDTO ofertaDTO : ofertasDTO ){
				ofertaDTO.setMetodoValuacionId( actualizado.getId() );
			}
			this.ofertaEnsamblador.actualizarOfertas(ofertasDTO, actualizado);
		}
		return actualizado;
	}

	@Transactional(readOnly = true)
	@Override
	public List<MetodoValuacionDTO> encontrarPorAvaluoId(Long id) {
		List<MetodoValuacion> metodos = this.metodoValuacionRepository.encontrarPorAvaluoId(id);
        return this.metodoValuacionEnsamblador.escribirListaDTO(metodos);
	}

    @Override
    public void eliminarPor(Long avaluoId) {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();

        // create delete
        CriteriaDelete<MetodoValuacion> delete = cb.
                createCriteriaDelete(MetodoValuacion.class);

        // set the root class
        Root<MetodoValuacion> e = delete.from(MetodoValuacion.class);

        // set where clause
        delete.where(cb.equal(e.get(MetodoValuacion_.avaluo).get(Avaluo_.id), avaluoId));

        // perform update
        this.em.createQuery(delete).executeUpdate();
    }

}
