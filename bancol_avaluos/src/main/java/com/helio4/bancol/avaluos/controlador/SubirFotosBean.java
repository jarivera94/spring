package com.helio4.bancol.avaluos.controlador;

import java.io.Externalizable;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.AjaxBehaviorListener;
import javax.faces.event.MethodExpressionActionListener;

import org.primefaces.behavior.ajax.AjaxBehavior;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.dashboard.Dashboard;
import org.primefaces.component.graphicimage.GraphicImage;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.panel.Panel;
import org.primefaces.component.panelgrid.PanelGrid;
import org.primefaces.context.RequestContext;
import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.helio4.bancol.avaluos.dominio.FotografiasController;
import com.helio4.bancol.avaluos.dominio.ParametrosController;
import com.helio4.bancol.avaluos.dto.AvaluoDTO;
import com.helio4.bancol.avaluos.dto.FotografiaDTO;
import com.helio4.bancol.avaluos.servicio.datos.AvaluoService;
import com.helio4.bancol.avaluos.servicio.util.Constantes;

@Controller
@Scope("view")
@Qualifier("subirFotosBean")
public class SubirFotosBean implements Externalizable {

    private static Logger log = LoggerFactory.getLogger(SubirFotosBean.class);
    public static String IMAGES_PATH = "";
    private String separator = File.separator;
    private String urlSeparator = "/";
    private String urlReturn;

    @Autowired
    @Qualifier("repositoryAvaluoService")
    private AvaluoService avaluoService;

    @Autowired
    private FotografiasController fotografiasController;

    @Autowired
    private ParametrosController parametrosController;

    private AvaluoDTO avaluo;

    private FotografiaDTO fotografiaDTO;

    private List<FotografiaDTO> fotografias;

    private List<Panel> panelesFotografias;

    @Autowired
    private ListadoAvaluosBean listadoAavaluosBean;


    private int orden;

    private Dashboard dashboard;
    private final int columnCount = 2;

    @PostConstruct
    public void init() {
        IMAGES_PATH = parametrosController.obtenerValor("rutaImagenes");
        avaluo = listadoAavaluosBean.getAvaluo();
        fotografias = fotografiasController.buscarFotografiasAvaluo(avaluo);
        inicializarDashboard();
        orden = fotografias.size();
    }

    private void inicializarDashboard() {
        /** Inicializar el componente Dashboard y hacerlo dínamico */
        FacesContext fc = FacesContext.getCurrentInstance();
        Application application = fc.getApplication();
        dashboard = new Dashboard();
        dashboard = (Dashboard) application.createComponent(fc,
                "org.primefaces.component.Dashboard",
                "org.primefaces.component.DashboardRenderer");
        dashboard.setId("dashBoardFotografias");
        dashboard = crearDashboardDinamico();
    }
    

    public Dashboard crearDashboardDinamico() {
        DashboardModel model = new DefaultDashboardModel();
        for (int i = 0; i < columnCount; i++) {
            DashboardColumn column = new DefaultDashboardColumn();
            model.addColumn(column);
        }
        int contador = 0;
        for (FotografiaDTO fotografia : fotografias) {
            model.getColumn((fotografia.getOrden().intValue() & 1) == 0 ? 1 : 0)
                    .addWidget(
                            crearPanelFotografia(contador++, fotografia)
                                    .getId());
        }
        dashboard.setModel(model);
        return dashboard;
    }

    private Panel crearPanelFotografia(int indice, FotografiaDTO fotografiaDTO) {
        FacesContext fc = FacesContext.getCurrentInstance();
        Application application = fc.getApplication();
        ExpressionFactory expressionFactory = application
                .getExpressionFactory();
        Panel panel = (Panel) application.createComponent(fc,
                "org.primefaces.component.Panel",
                "org.primefaces.component.PanelRenderer");
        panel.setId("fotografia_"+indice);
        panel.setHeader(fotografiaDTO.getDescripcion());
        panel.setStyleClass("panelDash");

        PanelGrid panelGrid = (PanelGrid) application.createComponent(fc,
                "org.primefaces.component.PanelGrid",
                "org.primefaces.component.PanelGridRenderer");
        panelGrid.setId("pGrid_" + fotografiaDTO.getId());
        panelGrid.setColumns(1);
        panelGrid.setStyleClass("pGridDash");

        GraphicImage graphicImage = new GraphicImage();
        graphicImage.setId("fotografi_imagen_" + indice);
        graphicImage.setUrl(crearURLFotografia(fotografiaDTO.getId(),
                fotografiaDTO.getRutaUbicacion()));
        graphicImage.setWidth("100px");
        graphicImage.setHeight("100px");
        graphicImage.setStyle("margin: 0px 25%");
        panelGrid.getChildren().add(graphicImage);

        InputText text = new InputText();
        text.setId("txtFoto_" + indice);
        text.setValue(fotografiaDTO.getDescripcion());
        text.setStyle("margin: 0px 18%;");
        AjaxBehavior ajaxBehavior = (AjaxBehavior)FacesContext.getCurrentInstance().getApplication().createBehavior(AjaxBehavior.BEHAVIOR_ID);
        ajaxBehavior.addAjaxBehaviorListener(new InputTextAjaxBehavior());
        text.addClientBehavior("change", ajaxBehavior);
        panelGrid.getChildren().add(text);

        CommandButton button = new CommandButton();
        button.setId("boton_eliminar_" + indice);
        button.setAjax(true);
        button.setProcess("@form");
        button.setType("submit");
        MethodExpression methodExpression = expressionFactory
                .createMethodExpression(fc.getELContext(),
                        "#{subirFotosBean.eliminarFotografia}", null,
                        new Class[] { ActionEvent.class });
        button.addActionListener(new MethodExpressionActionListener(
                methodExpression));
        button.setIcon("ui-icon-trash");
        button.setStyle("margin: 0px 40%;");
        panelGrid.getChildren().add(button);
        panel.getChildren().add(panelGrid);
        dashboard.getChildren().add(panel);
        return panel;
    }

    /**
     * Convierte la ruta de la imagen en una ruta que puede acceder el servidor
     * @param id
     * @param rutaAnterior
     * @return
     */
    public String crearURLFotografia(Long id, String rutaAnterior) {
        return urlSeparator + "images" + urlSeparator
                + avaluo.getId() + urlSeparator + avaluo.getId() + "_"
                + String.format("%010d", id) + ".jpg";
    }

    public void subirFoto(FileUploadEvent event) {
        orden++;
        fotografiaDTO = fotografiasController.subirFoto(event, IMAGES_PATH,
                separator, avaluo, new Long(orden));
        fotografias.add(fotografiaDTO);
        /**
         * Si es la primera vez que se suben fotos
         * De otra forma añadir la foto al dashboard
         */
        if (dashboard.getModel() == null) {
            dashboard = crearDashboardDinamico();
        } else {
            dashboard
                .getModel()
                .getColumn((dashboard.getChildCount() & 1) == 0 ? 0 : 1)
                .addWidget(
                        crearPanelFotografia(
                            fotografias.indexOf(fotografiaDTO),
                            fotografiaDTO).getId());

            
        }
        if(avaluo.getEstado().getEstado().key()
                == Constantes.Estado.Aprobado.key()){
            String url = IMAGES_PATH + "\\" + avaluo.getId() + ".pdf";
            new File(url).delete();
        }
    }

    public void actualizarDescripcion(AjaxBehaviorEvent event) {
        try {
            int indice = Integer.parseInt(event.getComponent()
                    .getId().split("_")[1]);
            String texto = (String) ((InputText)event.getSource()).getValue();
            FotografiaDTO fotografia = fotografias.get(indice);
            fotografia.setDescripcion(texto);
            fotografiasController.actualizarFotografia(fotografia);
        } catch (NumberFormatException e) {
            log.error("Ocurrio un error al convertir {} a un int",
                    event.getComponent().getId().split("_")[1]);
        }
        dashboard.getChildren().clear();
        dashboard = crearDashboardDinamico();
        RequestContext.getCurrentInstance().update("formFotos");
    }

    public void eliminarFotografia(ActionEvent event) {
        FotografiaDTO fotoEliminada = null;
        try {
            int indiceFotografia = Integer.parseInt(event.getComponent()
                    .getId().split("_")[2]);
            fotoEliminada = fotografias.get(indiceFotografia);
            fotografias.remove(indiceFotografia);
            fotografiasController.eliminarFotografia(fotoEliminada.getId());
            int indiceActualizarOrden =
                    fotoEliminada.getOrden().intValue() - 1;
            for (int i = indiceActualizarOrden; i < fotografias.size() ; i++) {
                FotografiaDTO fotografia = fotografias.get(i);
                fotografia.setOrden(new Long(i+1));
                fotografiasController.actualizarFotografia(fotografia);
            }
        } catch (NumberFormatException e) {
            log.error("Ocurrio un error al convertir {} a un int",
                    event.getComponent().getId().split("_")[2]);
        }
        Collections.sort(fotografias, new FotografiasComparator());
        dashboard.getChildren().clear();
        dashboard = crearDashboardDinamico();
        
        if(avaluo.getEstado().getEstado().key()==Constantes.Estado.Aprobado.key()){
        	String url = IMAGES_PATH + "\\" + avaluo.getId() + ".pdf";
        	new File(url).delete();
        }
        
        FacesMessage message = new FacesMessage();
        message.setSeverity(FacesMessage.SEVERITY_INFO);
        message.setSummary("Eliminó: " + fotoEliminada.getDescripcion());
        FacesContext.getCurrentInstance().addMessage(null, message);
        RequestContext.getCurrentInstance().update("formFotos");
    }

    public void cancelar() {
        FacesContext fc = FacesContext.getCurrentInstance();
        String uri = fc.getExternalContext().encodeActionURL(
                FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+listadoAavaluosBean.getUrlReturn());
        
        try {
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect(uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleReorder(DashboardReorderEvent event) {
        FacesMessage message = new FacesMessage();
        message.setSeverity(FacesMessage.SEVERITY_INFO);
        message.setSummary("Movio: " + event.getWidgetId().split("_")[1]);
        message.setDetail("Fila: " + event.getItemIndex() + ", Columna: "
                + event.getColumnIndex());
        FacesContext.getCurrentInstance().addMessage(null, message);

        int columna = event.getColumnIndex();
        int fila = event.getItemIndex();
        int indiceNuevo = (columna + fila) + fila;
        try {
            int indiceFotografia = Integer
                .parseInt(event.getWidgetId().split("_")[1]);
            FotografiaDTO fotoMovida = fotografias.get(indiceFotografia);
            int posicionActual = indiceFotografia;
            fotografias = fotografiasController
                .moverFoto(fotoMovida, posicionActual,
                    indiceNuevo, fotografias);
            log.info("fotografias {}", fotografias);
        } catch (NumberFormatException e) {
            log.error("Ocurrio un error al convertir {} a un Long",
                    event.getWidgetId().split("_")[1]);
        }
        Collections.sort(fotografias, new FotografiasComparator());
        dashboard.getChildren().clear();
        dashboard = crearDashboardDinamico();
        RequestContext.getCurrentInstance().update("formFotos");
    }
    

    public AvaluoDTO getAvaluo() {
        return avaluo;
    }

    public void setAvaluo(AvaluoDTO avaluo) {
        this.avaluo = avaluo;
    }

    public FotografiaDTO getFotografiaDTO() {
        return fotografiaDTO;
    }

    public void setFotografiaDTO(FotografiaDTO fotografiaDTO) {
        this.fotografiaDTO = fotografiaDTO;
    }

    public List<FotografiaDTO> getFotografias() {
        return fotografias;
    }

    public void setFotografias(List<FotografiaDTO> fotografias) {
        this.fotografias = fotografias;
    }

    public ListadoAvaluosBean getListadoAavaluosBean() {
        return listadoAavaluosBean;
    }

    public void setListadoAavaluosBean(ListadoAvaluosBean listadoAavaluosBean) {
        this.listadoAavaluosBean = listadoAavaluosBean;
    }

    public Dashboard getDashboard() {
        return dashboard;
    }

    public void setDashboard(Dashboard dashboard) {
        this.dashboard = dashboard;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public List<Panel> getFotografiasOrdenadas() {
        return panelesFotografias;
    }

    public void setPanelesFotografias(List<Panel> panelesFotografias) {
        this.panelesFotografias = panelesFotografias;
    }
    
    public class FotografiasComparator implements Comparator<FotografiaDTO> {
        @Override
        public int compare(FotografiaDTO o1, FotografiaDTO o2) {
            log.info("o1 {}, o2 {}", o1, o2);
            if (o1 != null && o2 != null) {
                if (o1.getOrden() > o2.getOrden()) {
                    return 1;
                }
                if (o1.getOrden() < o2.getOrden()) {
                    return -1;
                }
            }
            return 0;
        }
    }
    
    public class InputTextAjaxBehavior implements AjaxBehaviorListener, Externalizable {

        @Override
        public void writeExternal(ObjectOutput out) throws IOException {}

        @Override
        public void readExternal(ObjectInput in) throws IOException,
                ClassNotFoundException {}

        @Override
        public void processAjaxBehavior(AjaxBehaviorEvent event)
                throws AbortProcessingException {
            actualizarDescripcion(event);
        }
        
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.write(orden);
        out.writeObject(avaluo);
        out.writeObject(fotografias);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException,
            ClassNotFoundException {
        this.orden = in.readInt();
        this.avaluo = (AvaluoDTO) in.readObject();
        this.fotografias = (List<FotografiaDTO>) in.readObject();
    }

}
