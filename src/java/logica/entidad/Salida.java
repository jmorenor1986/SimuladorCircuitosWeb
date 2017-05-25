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
public class Salida extends Entidad implements Acciones {

    private Integer entrada;

    public Salida(String mensaje, String tipo) {
        super(mensaje, tipo);
    }

    @Override
    public void dibujar(Graphics g) {
        if (entrada == null) {
            setImagen("lampOFF.png");
            getMensajeLog().setTipo("alerta");
            getMensajeLog().setMensaje("El componente no tiene conectado ninguna linea");
        } else {
            switch (entrada) {
                case 1:
                    setImagen("lampON.png");
                    getMensajeLog().setTipo("texto");
                    getMensajeLog().setMensaje("Salida ON");
                    break;
                case 0:
                    setImagen("lampOFF.png");
                    getMensajeLog().setTipo("texto");
                    getMensajeLog().setMensaje("Salida OFF");
                    break;
                default:

                    break;
            }
        }

    }

    @Override
    public void eliminar() {

    }

    @Override
    public void validarEstado() {
        if (getEntrada() == 0) {
            setImagen("lampOFF.png");
        } else {
            setImagen("lampON.png");
        }
    }

    public Integer getEntrada() {
        return entrada;
    }

    public void setEntrada(Integer entrada) {
        this.entrada = entrada;
    }


}
