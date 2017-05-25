/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.entidad;

import java.awt.Graphics;
import java.util.Objects;

/**
 *
 * @author john
 */
public class Compuerta extends Entidad implements Acciones {

    public Compuerta(String mensaje, String tipo) {
        super(mensaje, tipo);
    }

    @Override
    public void dibujar(Graphics g) {
        if (!"linea".equalsIgnoreCase(getTipo())) {
            if (getTipo().equalsIgnoreCase("and")) {
                setImagen("and.png");
            }
            if (getTipo().equalsIgnoreCase("or")) {
                setImagen("or.png");
            }
            if (getTipo().equalsIgnoreCase("not")) {
                setImagen("not.png");
            }
        }
    }

    @Override
    public void eliminar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void validarEstado() {
        if (getTipo().equalsIgnoreCase("and")) {
            if (Objects.equals(getEntrada1(), getEntrada2())) {
                setSalida(getEntrada1());
            } else {
                setSalida(0);
            }
        }
        if (getTipo().equalsIgnoreCase("or")) {
            if (getEntrada1() == 0 && getEntrada2() == 0) {
                setSalida(0);
            } else {
                setSalida(1);
            }
        }
        if (getTipo().equalsIgnoreCase("not")) {
            if (getEntrada1() == 0) {
                setSalida(1);
            }
            if (getEntrada1() == 1) {
                setSalida(0);
            }
        }
    }

}
