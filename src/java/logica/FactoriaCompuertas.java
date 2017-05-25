/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import logica.entidad.Compuerta;
import logica.entidad.Entidad;
import logica.entidad.Linea;
import logica.entidad.Salida;
import logica.entidad.Switch;

/**
 *
 * @author john
 */
public class FactoriaCompuertas {

    public static Entidad getEntidad(String tipo) {
        switch (tipo) {
            case "objeto1":
                return new Linea("La linea sirve para unir dos componentes", "linea");
            case "objeto2":
                return new Compuerta("Componente AND, tiene dos entradas y una salida", "and");
            case "objeto3":
                return new Compuerta("Componente OR, tiene dos entradas y una salida", "or");
            case "objeto4":
                return new Compuerta("Componente NOT, tiene una entrada y una salida", "not");
            case "objeto5":
                return new Switch("Componente ON, Envia un 1 de corriente", "on");
            case "objeto6":
                return new Switch("Componente OFF, Envia un 0 de corriente", "off");
            case "objeto7":
                return new Salida("Componente Salida, Muestra cual es la salida del circuito", "lampOFF");
            case "objeto8":
                return new Compuerta("Componente XOR, tiene dos entradas y una salida", "xor");
            default:
                return null;
        }
    }
}
