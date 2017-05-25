/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.ArrayList;
import logica.entidad.Compuerta;
import logica.entidad.Entidad;
import logica.entidad.Linea;
import logica.entidad.MensajeLog;
import logica.entidad.Salida;
import logica.entidad.Switch;

/**
 *
 * @author john
 */
public class Logica {

    private ArrayList<Entidad> objetosEntidad;
    private ArrayList<Linea> objetoLinea;
    private ArrayList<Switch> objetoSwitch;
    private MensajeLog mensajeLog;

    public Logica() {
        if (objetoLinea == null) {
            objetoLinea = new ArrayList<>();
        }
        if (objetoSwitch == null) {
            objetoSwitch = new ArrayList<>();
        }
        if (mensajeLog == null) {
            mensajeLog = new MensajeLog();
        }

        getObjetosEntidad();
        //llenaLista();
    }

    public void llenaLista() {
        Entidad e = new Compuerta("compuerta", "and");
        e.setNombre("objeto0");
        objetosEntidad.add(e);
        //-------------------------
        e = new Switch("switch", "on");
        e.setSalida(1);
        e.setNombre("objeto1");
        objetosEntidad.add(e);
        //-------------------------
        Linea ln = new Linea("linea", "linea");
        ln.setNombre("objeto2");
        ln.setNombreObjetoEntrada("objeto0");
        ln.setNombreObjetoSalida("objeto1");
        objetosEntidad.add(ln);
        //-------------------------
        e = new Switch("switch", "on");
        e.setSalida(1);
        e.setNombre("objeto3");
        objetosEntidad.add(e);
        //--------------------------------
        ln = new Linea("linea", "linea");
        ln.setNombre("objeto4");
        ln.setNombreObjetoEntrada("objeto0");
        ln.setNombreObjetoSalida("objeto3");
        objetosEntidad.add(ln);
        //-------------------------
        e = new Switch("switch", "on");
        e.setSalida(1);
        e.setNombre("objeto5");
        objetosEntidad.add(e);
        //-------------------------
        e = new Compuerta("compuerta", "and");
        e.setNombre("objeto6");
        objetosEntidad.add(e);
        //--------------------------------
        ln = new Linea("linea", "linea");
        ln.setNombre("objeto7");
        ln.setNombreObjetoEntrada("objeto6");
        ln.setNombreObjetoSalida("objeto5");
        objetosEntidad.add(ln);
        //--------------------------------
        ln = new Linea("linea", "linea");
        ln.setNombre("objeto8");
        ln.setNombreObjetoEntrada("objeto6");
        ln.setNombreObjetoSalida("objeto0");
        objetosEntidad.add(ln);
        //--------------------------------
        Salida sal = new Salida("", "LampOFF");
        sal.setNombre("objeto9");
        objetosEntidad.add(sal);
        //--------------------------------
        ln = new Linea("linea", "linea");
        ln.setNombre("objeto10");
        ln.setNombreObjetoEntrada("objeto9");
        ln.setNombreObjetoSalida("objeto6");
        objetosEntidad.add(ln);

    }

    public void guardaObjetos() {
        for (Entidad item : objetosEntidad) {
            if (item instanceof Linea) {
                objetoLinea.add((Linea) item);
            } else if (item instanceof Switch) {
                objetoSwitch.add((Switch) item);
            }
        }

    }

    public void iniciaVerificacion() {
        verificaLineaSwitch();
        if (!verificaTerminaCircuito()) {
            verificaEntradasVacias();
        }
        muestraResultado();
    }

    public void verificaLineaSwitch() {
        for (Switch item : objetoSwitch) {
            for (Linea linea : objetoLinea) {
                if (linea.getNombreObjetoSalida().equalsIgnoreCase(item.getNombre())) {
                    modificaObjeto(linea.getNombreObjetoEntrada(), item.getSalida());
                }
            }

        }
    }

    public void modificaObjeto(String nombreObjeto, int valor) {
        for (Entidad ent : objetosEntidad) {
            if (ent.getNombre().equalsIgnoreCase(nombreObjeto)) {
                if (ent instanceof Compuerta) {
                    if (!ent.getTipo().equalsIgnoreCase("not")) {
                        if (ent.getEntrada1() == null) {
                            ent.setEntrada1(valor);
                            break;
                        } else {
                            ent.setEntrada2(valor);
                            ((Compuerta) ent).validarEstado();
                            break;
                        }
                    } else {
                        if (ent.getEntrada1() == null) {
                            ent.setEntrada1(valor);
                            ((Compuerta) ent).validarEstado();
                            break;
                        }
                    }
                } else if (ent instanceof Salida) {
                    ((Salida) ent).setEntrada(valor);
                    ((Salida) ent).validarEstado();
                }
            }
        }

    }

    public void verificaEntradasVacias() {
        for (Entidad ent : objetosEntidad) {
            if (ent instanceof Compuerta) {
                if (!ent.getTipo().equalsIgnoreCase("not")) {

                    if (ent.getEntrada1() == null || ent.getEntrada2() == null) {
                        if (ent.getEntrada2() == null) {
                            verificaNombreLinea(ent.getNombre());
                        }
                    }
                } else {
                    if (ent.getEntrada1() == null) {
                        verificaNombreLinea(ent.getNombre());
                    }
                }
            }
            if (ent instanceof Salida) {
                if (((Salida) ent).getEntrada() == null) {
                    verificaNombreLinea(ent.getNombre());
                }
            }
        }
    }

    public void verificaNombreLinea(String nombreObjeto) {
        Integer valor = null;
        for (Linea l : objetoLinea) {
            if (l.getNombreObjetoEntrada().equalsIgnoreCase(nombreObjeto)) {
                valor = consultaObjeto(l.getNombreObjetoSalida());
                if (valor != null) {
                    modificaObjeto(nombreObjeto, valor);
                }

            } else if (l.getNombreObjetoSalida().equalsIgnoreCase(nombreObjeto)) {
                valor = consultaObjeto(l.getNombreObjetoEntrada());
                if (valor != null) {
                    modificaObjeto(nombreObjeto, valor);
                }

            }
        }
    }

    public void muestraResultado() {
        for (Entidad ent : objetosEntidad) {
            if (ent instanceof Compuerta) {
                if (ent.getSalida() != null) {
                }
            }
            if (ent instanceof Salida) {

                mensajeLog.setMensaje("OK,Salida del circuito " + ((Salida) ent).getEntrada());
                mensajeLog.setTipo("texto");
            }
        }
    }

    public Integer consultaObjeto(String nombreObjeto) {
        for (Entidad ent : objetosEntidad) {
            if (ent instanceof Compuerta) {
                if (ent.getNombre().equalsIgnoreCase(nombreObjeto)) {
                    if (ent.getSalida() != null) {
                        return ent.getSalida();
                    }
                }
            }
            if (ent instanceof Switch) {
                if (ent.getNombre().equalsIgnoreCase(nombreObjeto)) {
                    if (ent.getSalida() != null) {
                        return ent.getSalida();
                    }
                }

            }
        }
        return null;
    }

    public Entidad verificaEstructuraCircuito() {
        for (Entidad ent : objetosEntidad) {
            if (ent instanceof Compuerta) {
                if (!ent.getTipo().equalsIgnoreCase("not")) {
                    if (!ent.isIsInput1() || !ent.isIsInput2() || !ent.isIsOutput1()) {
                        return ent;
                    }
                } else {
                    if (!ent.isIsInput1() || !ent.isIsOutput1()) {
                        return ent;
                    }
                }
            }
            if (ent instanceof Salida) {
                if (!ent.isIsInput2()) {
                    return ent;
                }
            }
            if (ent instanceof Switch) {
                if (!ent.isIsOutput1()) {
                    return ent;
                }
            }
        }
        return null;
    }

    public boolean verificaTerminaCircuito() {
        for (Entidad item : objetosEntidad) {
            if (item instanceof Compuerta) {
                if (!"not".equalsIgnoreCase(item.getTipo())) {
                    if (item.getEntrada1() == null || item.getEntrada2() == null) {
                        return false;
                    }
                } else {
                    if (item.getEntrada1() == null) {
                        return false;
                    }
                }
            } else if (item instanceof Salida) {
                if (((Salida) item).getEntrada() == null) {
                    return false;
                }
            }
        }
        return true;
    }

    public ArrayList<Entidad> getObjetosEntidad() {
        if (objetosEntidad == null) {
            objetosEntidad = new ArrayList<>();
        }
        return objetosEntidad;
    }

    public void setObjetosEntidad(ArrayList<Entidad> objetosEntidad) {
        this.objetosEntidad = objetosEntidad;
    }

    public ArrayList<Linea> getObjetoLinea() {
        return objetoLinea;
    }

    public ArrayList<Switch> getObjetoSwitch() {
        return objetoSwitch;
    }

    public MensajeLog getMensajeLog() {
        return mensajeLog;
    }

}
