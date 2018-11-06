package com.helio4.bancol.avaluos.dominio;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.helio4.bancol.avaluos.dominio.comparador.GeneradorModificaciones;
import com.helio4.bancol.avaluos.dto.ModificacionDTO;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;
import com.helio4.bancol.avaluos.dto.AreaDTO.UnidadDeMedida;
import com.helio4.bancol.avaluos.servicio.util.MathUtils;

public class Comparador {
	
	/**
	 * @param firstInstance
	 * @param secondInstance
	 * @param idAvaluo
	 * @param usuario 
	 * @param funcionesIgnorar, parametro con la lista de funciones a ignorar en el proceso de verificacion.
	 * */
    public static List<ModificacionDTO> comparar(Object firstInstance, Object secondInstance, Long idAvaluo, UsuarioDTO usuario, Set<String> funcionesIgnorar) {
        List<ModificacionDTO> modificaciones = new ArrayList<ModificacionDTO>();
        try {
            Class firstClass = firstInstance.getClass();
            Method[] firstClassMethodsArr = firstClass.getMethods();

            Class secondClass = secondInstance.getClass();
            Method[] secondClassMethodsArr = secondClass.getMethods();

            if (funcionesIgnorar == null) {
            	funcionesIgnorar = new LinkedHashSet<String>();
            	funcionesIgnorar.add("Id");
            } 
            if (funcionesIgnorar !=null && !funcionesIgnorar.isEmpty() ) {
            	boolean hasId = funcionesIgnorar.contains("Id");
            	if ( !hasId) {
            		funcionesIgnorar.add("Id");
            	}
            }

            for (int i = 0; i < firstClassMethodsArr.length; i++) {
                Method firstClassMethod = firstClassMethodsArr[i];
                // target getter methods.
                if(firstClassMethod.getName().startsWith("get") 
                        && ((firstClassMethod.getParameterTypes()).length == 0)
                        && (!(firstClassMethod.getName().equals("getClass")))
                  ){

                    Object firstValue;
                    firstValue = firstClassMethod.invoke(firstInstance);
                   
                    
                    for (int j = 0; j < secondClassMethodsArr.length; j++) {
                        Method secondClassMethod = secondClassMethodsArr[j];
                        
                        if(secondClassMethod.getName().equals(firstClassMethod.getName())) {
                            Object secondValue = secondClassMethod.invoke(secondInstance);
                            
                            
                            if((firstValue==null && secondValue!=null) || (firstValue!=null && secondValue==null)) {
                                String metodo = secondClassMethod.getName().substring(3);
                                if ( !funcionesIgnorar.contains(metodo) ) {
                                	ModificacionDTO modificacion = llenar(metodo, modificaciones, firstValue, secondValue, idAvaluo, usuario);
                                	modificaciones.add(modificacion);
                                }
                            }else {
                            		if( firstValue instanceof BigDecimal) {
                            			BigDecimal bigDecimalFirstValue = MathUtils.getBigDecimal(firstValue);
                                    	BigDecimal bigDecimalSecondValue = MathUtils.getBigDecimal(secondValue);
                                		//si los valores son instancias de bigdecimal se debe comparar con el metodo compareTo
                                    	if (bigDecimalFirstValue !=null &&  bigDecimalSecondValue != null) {
                                        	if (bigDecimalFirstValue.compareTo(bigDecimalSecondValue)!=0 ) {
                                        		String metodo = secondClassMethod.getName().substring(3);
              	                                if ( !funcionesIgnorar.contains(metodo) ) {
              	                                	ModificacionDTO modificacion  = llenar(metodo, modificaciones, bigDecimalFirstValue, bigDecimalSecondValue, idAvaluo, usuario);
              	                                	modificaciones.add(modificacion);
              	                                }
                                        	}
                                        }
                            		}
                            		else {
                            			if ( firstValue!=null && secondValue!=null ) {
                                    		String metodo = secondClassMethod.getName().substring(3);
                            				if( !firstValue.equals(secondValue)) {
                            					if ( !funcionesIgnorar.contains(metodo) ) {
                            						ModificacionDTO modificacion = llenar(metodo, modificaciones, firstValue, secondValue, idAvaluo, usuario);
                            						modificaciones.add(modificacion);
                            					}
                            				}
                            			} 
                            		}
                            }
                        }
                    }
                  }
            }
        } catch (IllegalArgumentException e ) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return modificaciones;
    }

    private static ModificacionDTO llenar(String metodo, List<ModificacionDTO> modificaciones, Object anterior, Object nuevo, Long idAvaluo, UsuarioDTO usuario){
        String campo = "";

        for (int x=0;x<metodo.length(); x++)
        {
            if (x>0 && Character.isUpperCase(metodo.charAt(x))) {
                campo = campo+" "+metodo.substring(x, x+1);
            } else {
                campo = campo + metodo.substring(x, x+1);
            }
        }
        GeneradorModificaciones  builder =  new GeneradorModificaciones(campo, anterior, nuevo, idAvaluo, usuario);
        return builder.getModificacion();
    }
}
