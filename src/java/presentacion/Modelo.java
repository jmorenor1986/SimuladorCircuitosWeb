/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import logica.FachadaCompuertas;
import logica.FactoriaCompuertas;
import logica.Logica;
import logica.entidad.Entidad;
import logica.entidad.Linea;
import logica.entidad.MensajeLog;
import org.primefaces.context.RequestContext;

/**
 *
 * @author jomorenoro
 */
@ManagedBean
@ViewScoped
public class Modelo implements Runnable {

    private Entidad objeto;
    private String titulo;
    private double valorX;
    private double valorY;
    private ArrayList<Entidad> guardaObjetos;
    private FachadaCompuertas fachada;
    private int cantidad = 0;
    private int consecutivoNombre = 0;
    private String nombreObjeto;
    private MensajeLog mensaje;
    private Linea objetoLinea;
    private boolean pintaLienzo;
    private Thread hiloDibujo;
    private Logica logica;

    @PostConstruct
    public void init() {
        titulo = "TITUlo";
        if (guardaObjetos == null) {
            guardaObjetos = new ArrayList<>();
        }
        if (fachada == null) {
            fachada = new FachadaCompuertas();
        }
        pintaLienzo = true;
        hiloDibujo = new Thread(this);
        hiloDibujo.start();
        if (mensaje == null) {
            mensaje = new MensajeLog();
        }
    }

    public void seleccionaComponente(String nombreComponente) {
        objeto = FactoriaCompuertas.getEntidad(nombreComponente);
        nombreObjeto = nombreComponente;
        mensaje.setTipo("texto");
        muestraMensaje("Info", objeto.getMensaje());
    }

    public void dibujaComponente() {
        if (objeto == null) {
            muestraMensaje("Error", "Debe seleccionar un componente para dibujar");
        } else {
            if (validaComponente((int) valorX, (int) valorY)) {
                if (!"linea".equalsIgnoreCase(objeto.getTipo())) {
                    objeto.setPosicionX((int) valorX);
                    objeto.setPosicionY((int) valorY);
                    fachada.dibujar(objeto, null);
                    fachada.getObjetosRepintar().setNombre("objeto" + consecutivoNombre);
                    consecutivoNombre++;
                    guardaObjetos.add(fachada.getObjetosRepintar());
                    muestraMensaje("Info", fachada.getObjetosRepintar().getMensajeLog().getMensaje());
                    objeto = FactoriaCompuertas.getEntidad(nombreObjeto);
                } else {
                    dibujaLinea((int) valorX, (int) valorY);
                }
                repintarLienzo();
            }
        }
    }

    public void dibujaLinea(int posicionX, int posicionY) {
        if (cantidad == 0) {
            objetoLinea = (Linea) FactoriaCompuertas.getEntidad(nombreObjeto);
            objetoLinea.setPunto1X(posicionX);
            objetoLinea.setPunto1Y(posicionY);
        }
        if (cantidad == 1) {
            objetoLinea.setPunto2X(posicionX);
            objetoLinea.setPunto2Y(posicionY);
            objetoLinea.setNombreObjetoEntrada(FachadaCompuertas.nombreObjetoEntrada);
            objetoLinea.setNombreObjetoSalida(FachadaCompuertas.nombreObjetoSalida);
            fachada.dibujar(objetoLinea, null);
            fachada.getObjetosRepintar().setNombre("objeto" + consecutivoNombre);
            consecutivoNombre++;
            cantidad = -1;
            guardaObjetos.add(fachada.getObjetosRepintar());

        }
        cantidad++;
    }

    public boolean validaComponente(int posicionX, int posicionY) {
        boolean validador;
        validador = fachada.validarPosicionComponente(guardaObjetos, posicionX, posicionY, objeto.getTipo(), cantidad);
        muestraMensaje("Error", FachadaCompuertas.text);
        if (validador) {
        } else {
            try {
                if (FachadaCompuertas.text.contains("OK")) {
                    muestraMensaje("Error", "Punto invalido");
                }
            } catch (Exception e) {
                System.out.println("punto invalido");
            }

        }
        return validador;
    }

    public void muestraMensaje(String tipo, String mensaje) {
        switch (tipo) {
            case "Error":
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, mensaje));
                break;
            case "Info":
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, mensaje));
                break;
        }
    }

    @Override
    public void run() {
        while (pintaLienzo) {
            try {
                //repintarLienzo();
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void repintarLienzo() {
        RequestContext.getCurrentInstance().execute("repaintCanvas()");
        for (Entidad obj : guardaObjetos) {
            if (obj instanceof Linea) {
                RequestContext.getCurrentInstance().execute("paint('" + obj.getImagen() + "'," + ((Linea) obj).getPunto1X() + "," + ((Linea) obj).getPunto1Y() + "," + ((Linea) obj).getPunto2X() + "," + ((Linea) obj).getPunto2Y() + ")");
            } else {
                RequestContext.getCurrentInstance().execute("paint('" + obj.getImagen() + "'," + obj.getPosicionX() + "," + obj.getPosicionY() + "," + 0 + "," + 0 + ")");
            }
        }
    }

    public void validaFormaCircuito() {
        getLogica().setObjetosEntidad(guardaObjetos);
        getLogica().guardaObjetos();
        if (getLogica().getObjetoSwitch().isEmpty()) {
            muestraMensaje("Error", "En el circuito no existe por lo menos un switch");
        } else if (getLogica().getObjetoLinea().isEmpty()) {
            muestraMensaje("Error", "En el circuito no existe por lo menos una conexión");
        } else {
            Entidad ent = getLogica().verificaEstructuraCircuito();
            if (ent == null) {
                getLogica().iniciaVerificacion();
                if (getLogica().getMensajeLog().getMensaje().startsWith("OK")) {
                    muestraMensaje("Info", getLogica().getMensajeLog().getMensaje());
                }
            } else {
                muestraMensaje("Error", "Circuito mal formado revise conexión de una entidad de tipo " + ent.getTipo());
            }

        }
        guardaObjetos = getLogica().getObjetosEntidad();
        repintarLienzo();
    }

    public void nuevoLienzo() {
        RequestContext.getCurrentInstance().execute("repaintCanvas()");
        guardaObjetos = new ArrayList<>();
    }

    public void deshacer() {
        RequestContext.getCurrentInstance().execute("repaintCanvas()");
        if (guardaObjetos != null && guardaObjetos.size() > 0) {
            guardaObjetos.remove(guardaObjetos.size() - 1);
        }
        repintarLienzo();

    }

    public Entidad getObjeto() {
        return objeto;
    }

    public void setObjeto(Entidad objeto) {
        this.objeto = objeto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getValorX() {
        return valorX;
    }

    public void setValorX(double valorX) {
        this.valorX = valorX;
    }

    public double getValorY() {
        return valorY;
    }

    public void setValorY(double valorY) {
        this.valorY = valorY;
    }

    public ArrayList<Entidad> getGuardaObjetos() {
        return guardaObjetos;
    }

    public void setGuardaObjetos(ArrayList<Entidad> guardaObjetos) {
        this.guardaObjetos = guardaObjetos;
    }

    public Logica getLogica() {
        if (logica == null) {
            logica = new Logica();
        }
        return logica;
    }

}
