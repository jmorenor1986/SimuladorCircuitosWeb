/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import logica.entidad.Entidad;

/**
 *
 * @author Asesoftware
 */
public class LogicaCanvas {
    //validadores entrada 1 punto 1 linea

    private int maxxentrada, maxyentrada1, minyentrada1, maxyentradaNot, minyentradaNot;
    //validadores entrada 2 punto 1 linea
    private int minxentrada, maxyentrada2, minyentrada2;
    private int maxxentrada3, minxentrada3, maxyentrada3, minyentrada3;
    //validadores salida 1 punto 1 linea
    private int maxxsalida1, minxsalida1, maxysalida1, minysalida1;
    private int maxxsalida2, minxsalida2, maxysalida2, minysalida2;

    public void calcularPosiciones(int actualx, int actualy) {
        //validadores entrada 1 punto 1 linea AND
        maxxentrada = actualx + 5;
        minxentrada = actualx - 5;
        maxyentrada1 = actualy + 10;
        minyentrada1 = actualy + 5;
        //validadores entrada 2 punto 1 linea AND
        maxyentrada2 = actualy + 37;
        minyentrada2 = actualy + 27;
        //validadores salida 1 punto 1 linea AND
        maxxsalida1 = actualx + 105;
        minxsalida1 = actualx + 95;
        maxysalida1 = actualy + 25;
        minysalida1 = actualy + 15;
        //validadores salida 1 punto 1 linea SW
        maxxsalida2 = actualx + 56;
        minxsalida2 = actualx + 43;
        maxysalida2 = actualy + 25;
        minysalida2 = actualy + 15;
        //validadores entrada 1 punto 1 linea LAMP
        maxxentrada3 = actualx + 30;
        minxentrada3 = actualx + 20;
        maxyentrada3 = actualy + 48;
        minyentrada3 = actualy + 38;
        //validadores entrada 1 punto 1 linea NOT
        maxyentradaNot = actualy + 28;
        minyentradaNot = actualy + 18;

    }

    public boolean validarPosicionAnd(Entidad ent, int posicionX, int posicionY) {
        int emaxx = 0, eminx = 0, emaxy = 0, eminy = 0;
    //    if ("and".equals(ent.getTipo())) {
            emaxx = ent.getPosicionX() + 100;
            eminx = ent.getPosicionX() - 100;
            emaxy = ent.getPosicionY() + 50;
            eminy = ent.getPosicionY() - 50;
      //  }
        if ((posicionX >= eminx) && (posicionX <= emaxx) && (posicionY >= eminy) && (posicionY <= emaxy)) {
            FachadaCompuertas.text = "No se debe colocar un elemento sobre otro";
            return false;
        } else {
            return true;
        }
    }

    public boolean validarPosicionSwitch(Entidad ent, int posicionX, int posicionY) {
        int emaxx = 0, eminx = 0, emaxy = 0, eminy = 0;
        emaxx = ent.getPosicionX() + 100;
        eminx = ent.getPosicionX() - 100;
        emaxy = ent.getPosicionY() + 50;
        eminy = ent.getPosicionY() - 50;

        if ((posicionX >= eminx) && (posicionX <= emaxx) && (posicionY >= eminy) && (posicionY <= emaxy)) {
            FachadaCompuertas.text = "No se debe colocar un elemento sobre otro";
            return false;
        } else {
            return true;
        }
    }

    public boolean validarPosicionLinea(Entidad ent, int posicionX, int posicionY, int cantidad) {
        boolean validador = false;
        calcularPosiciones(ent.getPosicionX(), ent.getPosicionY());
        if ((posicionX >= minxentrada) && (posicionX <= maxxentrada) && (posicionY >= minyentrada1) && (posicionY <= maxyentrada1)) {
            validador = validarInput(ent.isIsInput1(), ent.isIsOutput1(), cantidad, ent.getNombre());
            if (!ent.isIsInput1()) {
                ent.setIsInput1(validador);
            }
            return validador;
        } else if ((posicionX >= minxentrada) && (posicionX <= maxxentrada) && (posicionY >= minyentrada2) && (posicionY <= maxyentrada2)) {
            validador = validarInput(ent.isIsInput2(), ent.isIsOutput1(), cantidad, ent.getNombre());
            if (!ent.isIsInput2()) {
                ent.setIsInput2(validador);
            }
            return validador;
        } else if ((posicionX >= minxentrada3) && (posicionX <= maxxentrada3) && (posicionY >= minyentrada3) && (posicionY <= maxyentrada3)) {
            validador = validarInput(ent.isIsInput2(), ent.isIsOutput1(), cantidad, ent.getNombre());
            if (!ent.isIsInput2()) {
                ent.setIsInput2(validador);
            }
            return validador;
        } else if ((posicionX >= minxsalida1) && (posicionX <= maxxsalida1) && (posicionY >= minysalida1) && (posicionY <= maxysalida1)) {
            validador = validarOutput(ent.isIsInput1(), ent.isIsInput2(), ent.isIsOutput1(), cantidad, ent.getNombre());
            if (!ent.isIsOutput1()) {
                ent.setIsOutput1(validador);
            }
            return validador;
        } else if ((posicionX >= minxsalida2) && (posicionX <= maxxsalida2) && (posicionY >= minysalida2) && (posicionY <= maxysalida2)) {
            validador = validarOutput(ent.isIsInput1(), ent.isIsInput2(), ent.isIsOutput1(), cantidad, ent.getNombre());
            if (!ent.isIsOutput1()) {
                ent.setIsOutput1(validador);
            }
            return validador;
        } else if ((posicionX >= minxentrada) && (posicionX <= maxxentrada) && (posicionY >= minyentradaNot) && (posicionY <= maxyentradaNot)) {
            validador = validarInput(ent.isIsInput1(), ent.isIsOutput1(), cantidad, ent.getNombre());
            if (!ent.isIsInput1()) {
                ent.setIsInput1(validador);
            }
            return validador;
        } else {
            return validador;
        }
    }

    public boolean validarInput(boolean isInput, boolean isOutput, int cantidad, String nombreObjeto) {
        if (isInput) {
            FachadaCompuertas.text = "Entrada ocupada";
            return false;
        } else {
            if (cantidad == 0) {
                FachadaCompuertas.text = "Entrada OK";
                FachadaCompuertas.estadoLineaUno = false;
                FachadaCompuertas.nombreObjeto = nombreObjeto;
                FachadaCompuertas.nombreObjetoEntrada = nombreObjeto;
                return true;
            } else {
                if (FachadaCompuertas.nombreObjeto.equals(nombreObjeto)) {
                    FachadaCompuertas.text = "No se puede conectar el mismo objeto";
                    return false;
                } else if (FachadaCompuertas.estadoLineaUno) {
                    FachadaCompuertas.text = "Conexión OK";
                    FachadaCompuertas.nombreObjetoEntrada = nombreObjeto;
                    return true;
                } else {
                    FachadaCompuertas.text = "No se puede conectar entrada con entrada";
                    return false;
                }
            }
        }
    }

    public boolean validarOutput(boolean isInput1, boolean isInput2, boolean isOutput, int cantidad, String nombreObjeto) {
        if (isOutput) {
            FachadaCompuertas.text = "Salida ocupada";
            return false;
        } else {
            if (cantidad == 0) {
                FachadaCompuertas.text = "Salida OK";
                FachadaCompuertas.nombreObjeto = nombreObjeto;
                FachadaCompuertas.nombreObjetoSalida = nombreObjeto;
                FachadaCompuertas.estadoLineaUno = true;
                return true;
            } else {
                if (FachadaCompuertas.estadoLineaUno) {
                    FachadaCompuertas.text = "No se puede conectar salida con salida";
                    return false;
                } else {
                    if (FachadaCompuertas.nombreObjeto.equals(nombreObjeto)) {
                        FachadaCompuertas.text = "No se puede conectar el mismo objeto";
                        return false;
                    } else {
                        FachadaCompuertas.text = "Conexión OK";
                        FachadaCompuertas.nombreObjetoSalida = nombreObjeto;
                        return true;
                    }
                }
            }
        }
    }
}
