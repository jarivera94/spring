package com.helio4.bancol.avaluos.dominio;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.Normalizer;
import java.util.List;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.helio4.bancol.avaluos.dto.AvaluoDTO;
import com.helio4.bancol.avaluos.dto.FotografiaDTO;
import com.helio4.bancol.avaluos.servicio.excepciones.FotografiaNotFoundException;
import com.helio4.bancol.avaluos.servicio.datos.FotografiaService;
//import com.helio4.bancol.avaluos.servicio.util.ImageCompressor;

@Controller
public class FotografiasController {

    private static Logger log = LoggerFactory
        .getLogger( FotografiasController.class );

    @Autowired
    @Qualifier("repositoryFotografiaService")
    private FotografiaService fotografiaService;

	public FotografiaDTO subirFoto(FileUploadEvent event, String IMAGES_PATH, String separator, AvaluoDTO avaluo, Long orden) {
		log.debug("Subiendo fotografía al servidor {}",
                event.getFile().getFileName());
		UploadedFile file = event.getFile();
		InputStream inputStream = null;
		OutputStream outputStream = null;
		
		String fileName = normalizar(file.getFileName());
		fileName = fileName.toUpperCase().charAt(0)+fileName.substring(1).toLowerCase();
		
		String descripcion = null;
		String archivo = null;
		
		FotografiaDTO fotografiaDTO = null;
		
		if(fileName.lastIndexOf(".")>-1){
			descripcion = fileName.substring(0, fileName.lastIndexOf("."));
		}else{
			descripcion = fileName;
		}
		
		try {
			inputStream = event.getFile().getInputstream();
			if (!(new File(IMAGES_PATH+separator+avaluo.getId())).exists()) {
				(new File(IMAGES_PATH+separator+avaluo.getId())).mkdirs();
			}			
			fotografiaDTO = new FotografiaDTO(null, descripcion, avaluo.getId(), orden);
			fotografiaDTO = guardarFotografia(fotografiaDTO);
			
			archivo =  avaluo.getId()+"_"+String.format("%010d", fotografiaDTO.getId())+".jpg"; 
			/*String pathCompresed = IMAGES_PATH+separator+avaluo.getId()+separator 
					+ avaluo.getId()+"_"+String.format("%010d", fotografiaDTO.getId())+"_compressed.jpg";*/
			
			
			fotografiaDTO.setRutaUbicacion(IMAGES_PATH+separator+avaluo.getId()+separator+archivo);
			guardarRutaFotografia(fotografiaDTO);
			String path = fotografiaDTO.getRutaUbicacion();
			
		
			File file2 = new File(path);
			FileUtils.copyInputStreamToFile(inputStream, file2);
			tratarImagen(path);
			//ImageCompressor.resizeQuality(path, pathCompresed);
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(inputStream);
			IOUtils.closeQuietly(outputStream);
		}
		
		fotografiaDTO.setRutaUbicacion("/"+"images"+"/"+avaluo.getId()+"/"+archivo);
		return fotografiaDTO;
	}
	
	public FotografiaDTO subirAnexo(FileUploadEvent event, String IMAGES_PATH, String separator, AvaluoDTO avaluo, Long orden) {
		log.debug("Subiendo anexo al servidor {}",
                event.getFile().getFileName());
		UploadedFile file = event.getFile();
		InputStream inputStream = null;
		OutputStream outputStream = null;

		String fileName = normalizar(file.getFileName());
		fileName = fileName.toUpperCase().charAt(0)+fileName.substring(1).toLowerCase();

		String descripcion = null;
		String archivo = null;

		FotografiaDTO fotografiaDTO = null;

		if(fileName.lastIndexOf(".")>-1){
			descripcion = fileName.substring(0, fileName.lastIndexOf("."));
		}else{
			descripcion = fileName;
		}

		try {
			inputStream = event.getFile().getInputstream();
			if (!(new File(IMAGES_PATH+separator+avaluo.getId())).exists()) {
				(new File(IMAGES_PATH+separator+avaluo.getId())).mkdirs();
			}
			fotografiaDTO = new FotografiaDTO(null, descripcion, avaluo.getId(), orden);
			fotografiaDTO = guardarAnexo(fotografiaDTO);

			archivo =  avaluo.getId()+"_"+String.format("%010d", fotografiaDTO.getId())+".jpg";
			/*String pathCompresed = IMAGES_PATH+separator+avaluo.getId()+separator
					+ avaluo.getId()+"_"+String.format("%010d", fotografiaDTO.getId())+"_compressed.jpg";*/


			fotografiaDTO.setRutaUbicacion(IMAGES_PATH+separator+avaluo.getId()+separator+archivo);
			guardarRutaAnexo(fotografiaDTO);
			String path = fotografiaDTO.getRutaUbicacion();


			File file2 = new File(path);
			FileUtils.copyInputStreamToFile(inputStream, file2);
			tratarImagen(path);
			//ImageCompressor.resizeQuality(path, pathCompresed);



		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(inputStream);
			IOUtils.closeQuietly(outputStream);
		}
		
		fotografiaDTO.setRutaUbicacion("/"+"anexos"+"/"+avaluo.getId()+"/"+archivo);
		return fotografiaDTO;
	}
	
	public FotografiaDTO subirCroquis(FileUploadEvent event, String IMAGES_PATH, String separator, AvaluoDTO avaluo, Long orden) {
		log.debug("Subiendo croquis al servidor {}",
                event.getFile().getFileName());
		UploadedFile file = event.getFile();
		InputStream inputStream = null;
		OutputStream outputStream = null;

		String fileName = normalizar(file.getFileName());
		fileName = fileName.toUpperCase().charAt(0)+fileName.substring(1).toLowerCase();

		String descripcion = null;
		String archivo = null;

		FotografiaDTO fotografiaDTO = null;

		if(fileName.lastIndexOf(".")>-1){
			descripcion = fileName.substring(0, fileName.lastIndexOf("."));
		}else{
			descripcion = fileName;
		}

		try {
			inputStream = event.getFile().getInputstream();
			if (!(new File(IMAGES_PATH+separator+avaluo.getId())).exists()) {
				(new File(IMAGES_PATH+separator+avaluo.getId())).mkdirs();
			}
			fotografiaDTO = new FotografiaDTO(null, descripcion, avaluo.getId(), orden);
			fotografiaDTO = guardarCroquis(fotografiaDTO);

			archivo =  avaluo.getId()+"_"+String.format("%010d", fotografiaDTO.getId())+".jpg";
			/*String pathCompresed = IMAGES_PATH+separator+avaluo.getId()+separator
					+ avaluo.getId()+"_"+String.format("%010d", fotografiaDTO.getId())+"_compressed.jpg";*/


			fotografiaDTO.setRutaUbicacion(IMAGES_PATH+separator+avaluo.getId()+separator+archivo);
			guardarRutaCroquis(fotografiaDTO);
			String path = fotografiaDTO.getRutaUbicacion();


			File file2 = new File(path);
			FileUtils.copyInputStreamToFile(inputStream, file2);
			tratarImagen(path);
			//ImageCompressor.resizeQuality(path, pathCompresed);



		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(inputStream);
			IOUtils.closeQuietly(outputStream);
		}
		
		fotografiaDTO.setRutaUbicacion("/"+"croquis"+"/"+avaluo.getId()+"/"+archivo);
		return fotografiaDTO;
	}

    /*
     * Reorganiza una lista de fotos de tal forma que cuando
     * mueva una fotografía a una nueva posición las demás llenen los
     * espacios vacios que dejó mover la fotografia.
     * Ejemplo: Si muevo la fotografía de la posición 1 a la 4
     * la fotografía en la posición 2 debe pasar a la posición 1
     * la 3 a la 2, la 4 a la 3, la 1 a la 4 y las demás permanecen
     * en la mmisma posición.
     * Ejemplo 2: Si muevo la última fotografía a la primer posición
     * la última fotografía queda en la posición 1 y la primera en la posición
     * 2 las demas se corren de acuerdo a este movimiento.
     */
    public List<FotografiaDTO> moverFoto(FotografiaDTO fotoMovida,
            int posicionActual, int nuevaPosicion,
            List<FotografiaDTO> fotografias) {
        log.info("posicionActual {}, nuevaPosicion {}", posicionActual,
                nuevaPosicion);
        
        //Corrige la posición a la máxima permitida en la lista
        if(nuevaPosicion>=fotografias.size()){
        	nuevaPosicion = fotografias.size()-1;
        }
        
        /*
         * Si la posicionActual es mayor que la nuevaPosicion
         * quiere decir que se debe llenar la posición vacia
         * hacia abajo de otra forma hacia arriba.
         * Se mueven las fotografias al "espacio vacio"
         * ya que tenemos en la variable fotoMovida de otra
         * forma perderiamos la referencia a otra foto
         */
        if (posicionActual > nuevaPosicion) {
            for (int i = posicionActual; i > nuevaPosicion; i--) {
                FotografiaDTO fotoAnterior = fotografias
                    .get(i - 1);
                fotografias.set(i, fotoAnterior);
                fotoAnterior.setOrden(new Long(i+1));
                fotoAnterior = actualizarFotografia(fotoAnterior);
            }
        } else {
            for (int i = posicionActual; i < nuevaPosicion; i++) {
                FotografiaDTO fotoSiguiente = fotografias
                    .get(i + 1);
                fotografias.set(i, fotoSiguiente);
                fotoSiguiente.setOrden(new Long(i + 1));
                fotoSiguiente = actualizarFotografia(fotoSiguiente);
            }
        }
        fotografias.set(nuevaPosicion, fotoMovida);
        fotoMovida.setOrden(new Long(nuevaPosicion + 1));
        fotoMovida = actualizarFotografia(fotoMovida);
        return fotografias;
    }
    
    public List<FotografiaDTO> moverAnexo(FotografiaDTO fotoMovida,
            int posicionActual, int nuevaPosicion,
            List<FotografiaDTO> fotografias) {
        log.info("posicionActual {}, nuevaPosicion {}", posicionActual,
                nuevaPosicion);

        //Corrige la posición a la máxima permitida en la lista
        if(nuevaPosicion>=fotografias.size()){
        	nuevaPosicion = fotografias.size()-1;
        }

        /*
         * Si la posicionActual es mayor que la nuevaPosicion
         * quiere decir que se debe llenar la posición vacia
         * hacia abajo de otra forma hacia arriba.
         * Se mueven las fotografias al "espacio vacio"
         * ya que tenemos en la variable fotoMovida de otra
         * forma perderiamos la referencia a otra foto
         */
        if (posicionActual > nuevaPosicion) {
            for (int i = posicionActual; i > nuevaPosicion; i--) {
                FotografiaDTO fotoAnterior = fotografias
                    .get(i - 1);
                fotografias.set(i, fotoAnterior);
                fotoAnterior.setOrden(new Long(i+1));
                fotoAnterior = actualizarAnexo(fotoAnterior);
            }
        } else {
            for (int i = posicionActual; i < nuevaPosicion; i++) {
                FotografiaDTO fotoSiguiente = fotografias
                    .get(i + 1);
                fotografias.set(i, fotoSiguiente);
                fotoSiguiente.setOrden(new Long(i + 1));
                fotoSiguiente = actualizarAnexo(fotoSiguiente);
            }
        }
        fotografias.set(nuevaPosicion, fotoMovida);
        fotoMovida.setOrden(new Long(nuevaPosicion + 1));
        fotoMovida = actualizarAnexo(fotoMovida);
        return fotografias;
    }
    
    public List<FotografiaDTO> moverCroquis(FotografiaDTO fotoMovida,
            int posicionActual, int nuevaPosicion,
            List<FotografiaDTO> fotografias) {
        log.info("posicionActual {}, nuevaPosicion {}", posicionActual,
                nuevaPosicion);

        //Corrige la posición a la máxima permitida en la lista
        if(nuevaPosicion>=fotografias.size()){
        	nuevaPosicion = fotografias.size()-1;
        }

        /*
         * Si la posicionActual es mayor que la nuevaPosicion
         * quiere decir que se debe llenar la posición vacia
         * hacia abajo de otra forma hacia arriba.
         * Se mueven las fotografias al "espacio vacio"
         * ya que tenemos en la variable fotoMovida de otra
         * forma perderiamos la referencia a otra foto
         */
        if (posicionActual > nuevaPosicion) {
            for (int i = posicionActual; i > nuevaPosicion; i--) {
                FotografiaDTO fotoAnterior = fotografias
                    .get(i - 1);
                fotografias.set(i, fotoAnterior);
                fotoAnterior.setOrden(new Long(i+1));
                fotoAnterior = actualizarCroquis(fotoAnterior);
            }
        } else {
            for (int i = posicionActual; i < nuevaPosicion; i++) {
                FotografiaDTO fotoSiguiente = fotografias
                    .get(i + 1);
                fotografias.set(i, fotoSiguiente);
                fotoSiguiente.setOrden(new Long(i + 1));
                fotoSiguiente = actualizarCroquis(fotoSiguiente);
            }
        }
        fotografias.set(nuevaPosicion, fotoMovida);
        fotoMovida.setOrden(new Long(nuevaPosicion + 1));
        fotoMovida = actualizarCroquis(fotoMovida);
        return fotografias;
    }

    private FotografiaDTO guardarRutaFotografia(FotografiaDTO fotografiaDTO) {
        FotografiaDTO fotografia = null;
        try {
            fotografia = fotografiaService.guardarRutaFotografia(fotografiaDTO);
        } catch (FotografiaNotFoundException e) {
            return null;
        }
        return fotografia;
    }
    
    private FotografiaDTO guardarRutaAnexo(FotografiaDTO fotografiaDTO) {
        FotografiaDTO fotografia = null;
        try {
            fotografia = fotografiaService.guardarRutaAnexo(fotografiaDTO);
        } catch (FotografiaNotFoundException e) {
            return null;
        }
        return fotografia;
    }
    
    private FotografiaDTO guardarRutaCroquis(FotografiaDTO fotografiaDTO) {
        FotografiaDTO fotografia = null;
        try {
            fotografia = fotografiaService.guardarRutaCroquis(fotografiaDTO);
        } catch (FotografiaNotFoundException e) {
            return null;
        }
        return fotografia;
    }

    public static String normalizar(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\P{ASCII}");
        return pattern.matcher(normalized).replaceAll("");
    }

    public static void tratarImagen(String path){
        ImageIcon icono = new ImageIcon(path);
        int height = icono.getImage().getHeight(icono.getImageObserver());
        int width = icono.getImage().getWidth(icono.getImageObserver());

        Double heightD = new Double(height);
        Double widthD = new Double(width);

        if ((height > 600) || (width > 600)) {
          double factorHeight = height / 600.0D;
          double factorWidth = width / 600.0D;
          double factorPromedio = (factorHeight + factorWidth) / 2.0D;

          heightD = Double.valueOf(height / factorPromedio);
          widthD = Double.valueOf(width / factorPromedio);
        }
        Image imagen = icono.getImage().getScaledInstance(widthD.intValue(), heightD.intValue(), 2);

        ImageIcon iconoNuevo = new ImageIcon(imagen);

        BufferedImage bi = new BufferedImage(widthD.intValue(), heightD.intValue(), 1);
        Graphics2D g2 = bi.createGraphics();
        g2.drawImage(imagen, 0, 0, null);
        g2.dispose();
        try
        {
          ImageIO.write(bi, "jpg", new File(path));
        } catch (IOException e) {
          e.printStackTrace();
        }
    }

    public FotografiaDTO guardarFotografia(FotografiaDTO fotografiaDTO) {
        return fotografiaService.crear(fotografiaDTO);
    }
    
    public FotografiaDTO guardarAnexo(FotografiaDTO fotografiaDTO) {
        return fotografiaService.crearAnexo(fotografiaDTO);
    }
    
    public FotografiaDTO guardarCroquis(FotografiaDTO fotografiaDTO) {
        return fotografiaService.crearCroquis(fotografiaDTO);
    }

    public FotografiaDTO actualizarFotografia(FotografiaDTO fotografiaDTO) {
        FotografiaDTO fotografia = null;
        try {
            fotografia = fotografiaService.actualizar(fotografiaDTO);
        } catch (FotografiaNotFoundException e) {
            return null;
        }
        return fotografia;
    }
    
    public FotografiaDTO actualizarAnexo(FotografiaDTO fotografiaDTO) {
        FotografiaDTO fotografia = null;
        try {
            fotografia = fotografiaService.actualizarAnexo(fotografiaDTO);
        } catch (FotografiaNotFoundException e) {
            return null;
        }
        return fotografia;
    }
    
    public FotografiaDTO actualizarCroquis(FotografiaDTO fotografiaDTO) {
        FotografiaDTO fotografia = null;
        try {
            fotografia = fotografiaService.actualizarCroquis(fotografiaDTO);
        } catch (FotografiaNotFoundException e) {
            return null;
        }
        return fotografia;
    }

    public List<FotografiaDTO> buscarFotografiasAvaluo(AvaluoDTO avaluo) {
        return fotografiaService.buscarFotografiasAvaluo(avaluo.getId());
    }
    
    public List<FotografiaDTO> buscarAnexosAvaluo(AvaluoDTO avaluo) {
        return fotografiaService.buscarAnexosAvaluo(avaluo.getId());
    }
    
    public List<FotografiaDTO> buscarCroquisAvaluo(AvaluoDTO avaluo) {
        return fotografiaService.buscarCroquisAvaluo(avaluo.getId());
    }

    public void eliminarFotografia(Long fotografiaId){
        try {
            fotografiaService.eliminar(fotografiaId);
        } catch (FotografiaNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public void eliminarAnexo(Long fotografiaId){
        try {
            fotografiaService.eliminarAnexo(fotografiaId);
        } catch (FotografiaNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public void eliminarCroquis(Long fotografiaId){
        try {
            fotografiaService.eliminarCroquis(fotografiaId);
        } catch (FotografiaNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setFotografiaService(FotografiaService fotografiaService) {
        this.fotografiaService = fotografiaService;
    }
}
