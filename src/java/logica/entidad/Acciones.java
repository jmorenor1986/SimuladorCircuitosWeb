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
public interface Acciones {
    
    public void dibujar(Graphics g);
    public void eliminar();
    public void validarEstado();
}
