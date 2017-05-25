
package logica.entidad;


public class Entidad {

    private String estado;
    private String nombre;
    private String tipo;
    private String mensaje;
    private int posicionX;
    private boolean isInput1;
    private boolean isInput2;
    private boolean isOutput1;
    private int posicionY;
    private MensajeLog mensajeLog;
    private Integer entrada1;
    private Integer entrada2;
    private Integer salida;
    private String imagen;


    public boolean isIsInput1() {
        return isInput1;
    }

    public void setIsInput1(boolean isInput1) {
        this.isInput1 = isInput1;
    }

    public boolean isIsInput2() {
        return isInput2;
    }

    public void setIsInput2(boolean isInput2) {
        this.isInput2 = isInput2;
    }

    public boolean isIsOutput1() {
        return isOutput1;
    }

    public void setIsOutput1(boolean isOutput1) {
        this.isOutput1 = isOutput1;
    }


    public Entidad(String mensaje, String tipo) {
        this.mensaje = mensaje;
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getPosicionX() {
        return posicionX;
    }

    public void setPosicionX(int posicionX) {
        this.posicionX = posicionX;
    }

    public int getPosicionY() {
        return posicionY;
    }

    public void setPosicionY(int posicionY) {
        this.posicionY = posicionY;
    }

    public MensajeLog getMensajeLog() {
        if (mensajeLog == null) {
            mensajeLog = new MensajeLog();
        }
        return mensajeLog;
    }

    public void setMensajeLog(MensajeLog mensajeLog) {
        this.mensajeLog = mensajeLog;
    }

    public Integer getEntrada1() {
        return entrada1;
    }

    public void setEntrada1(Integer entrada1) {
        this.entrada1 = entrada1;
    }

    public Integer getEntrada2() {
        return entrada2;
    }

    public void setEntrada2(Integer entrada2) {
        this.entrada2 = entrada2;
    }

    public Integer getSalida() {
        return salida;
    }

    public void setSalida(Integer salida) {
        this.salida = salida;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    
    



}
