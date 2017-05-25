/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.entidad;

import java.awt.Graphics;

/**
 *
 * @author john
 */
public class Linea extends Entidad implements Acciones {

    private int punto1X, punto1Y, punto2X, punto2Y;
    private String nombreObjetoEntrada;
    private String nombreObjetoSalida;

    public Linea(String mensaje, String tipo) {
        super(mensaje, tipo);
    }

    @Override
    public void dibujar(Graphics g) {
        setImagen("linea");
        if ("on".equalsIgnoreCase(getEstado())) {
        } else if ("off".equalsIgnoreCase(getEstado())) {
        } else {
        }
    }

    public int getPunto1X() {
        return punto1X;
    }

    public void setPunto1X(int punto1X) {
        this.punto1X = punto1X;
    }

    public int getPunto1Y() {
        return punto1Y;
    }

    public void setPunto1Y(int punto1Y) {
        this.punto1Y = punto1Y;
    }

    public int getPunto2X() {
        return punto2X;
    }

    public void setPunto2X(int punto2X) {
        this.punto2X = punto2X;
    }

    public int getPunto2Y() {
        return punto2Y;
    }

    public void setPunto2Y(int punto2Y) {
        this.punto2Y = punto2Y;
    }
    public String getNombreObjetoEntrada() {
        return nombreObjetoEntrada;
    }

    public void setNombreObjetoEntrada(String nombreObjetoEntrada) {
        this.nombreObjetoEntrada = nombreObjetoEntrada;
    }

    public String getNombreObjetoSalida() {
        return nombreObjetoSalida;
    }

    public void setNombreObjetoSalida(String nombreObjetoSalida) {
        this.nombreObjetoSalida = nombreObjetoSalida;
    }

    
    @Override
    public void eliminar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void validarEstado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
