/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.entidad;

import java.awt.Color;

/**
 *
 * @author john
 */
public class MensajeLog {

    private String mensaje;
    private Color color;
    private String tipo;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    
    public Color getColor() {
        if ("alerta".equalsIgnoreCase(tipo)) {
            color = new Color(253, 253, 0);
        } else if ("texto".equalsIgnoreCase(tipo)) {
            color = new Color(255, 255, 255);
            } else if ("error".equalsIgnoreCase(tipo)) {
            color = new Color(254, 45, 0);
        }else{
            color = new Color(255, 255, 255);
        }
        return color;
    }



    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
