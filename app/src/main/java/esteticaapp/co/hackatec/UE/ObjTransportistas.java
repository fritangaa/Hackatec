package esteticaapp.co.hackatec.UE;

public class ObjTransportistas {

    private String nombre;
    private String origen;
    private String destino;
    private String tipoCarga;
    private Double volumen;
    private Double peso;
    private String diaSalida;
    private String horaSalida;
    private String diaLlegada;
    private String horaLlegada;
    private String ruta;
    private String empresa;
    private String correo;
    private Double precio;

    public ObjTransportistas() {
    }

    public ObjTransportistas(String nombre, String origen, String destino, String tipoCarga, Double volumen, Double peso, String diaSalida, String horaSalida, String diaLlegada, String horaLlegada, String ruta, String empresa, String correo, Double precio) {
        this.nombre = nombre;
        this.origen = origen;
        this.destino = destino;
        this.tipoCarga = tipoCarga;
        this.volumen = volumen;
        this.peso = peso;
        this.diaSalida = diaSalida;
        this.horaSalida = horaSalida;
        this.diaLlegada = diaLlegada;
        this.horaLlegada = horaLlegada;
        this.ruta = ruta;
        this.empresa = empresa;
        this.correo = correo;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getTipoCarga() {
        return tipoCarga;
    }

    public void setTipoCarga(String tipoCarga) {
        this.tipoCarga = tipoCarga;
    }

    public Double getVolumen() {
        return volumen;
    }

    public void setVolumen(Double volumen) {
        this.volumen = volumen;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public String getDiaSalida() {
        return diaSalida;
    }

    public void setDiaSalida(String diaSalida) {
        this.diaSalida = diaSalida;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getDiaLlegada() {
        return diaLlegada;
    }

    public void setDiaLlegada(String diaLlegada) {
        this.diaLlegada = diaLlegada;
    }

    public String getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(String horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
