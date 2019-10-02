package esteticaapp.co.hackatec.UE;

public class ObjViaje {

    private String nombreChofer;
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
    private Double latitud;
    private Double longitud;
    private Double kilometraje;
    private Double costo;

    public ObjViaje() {
    }

    public ObjViaje(String nombreChofer, String origen, String destino, String tipoCarga, Double volumen, Double peso, String diaSalida, String horaSalida, String diaLlegada, String horaLlegada, String ruta, String empresa, Double latitud, Double longitud, Double kilometraje, Double costo) {
        this.nombreChofer = nombreChofer;
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
        this.latitud = latitud;
        this.longitud = longitud;
        this.kilometraje = kilometraje;
        this.costo = costo;
    }

    public String getNombreChofer() {
        return nombreChofer;
    }

    public void setNombreChofer(String nombreChofer) {
        this.nombreChofer = nombreChofer;
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

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public Double getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(Double kilometraje) {
        this.kilometraje = kilometraje;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }
}
