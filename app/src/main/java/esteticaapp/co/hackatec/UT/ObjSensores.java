package esteticaapp.co.hackatec.UT;

public class ObjSensores {

    private String idViaje;
    private Double temperatura;
    private Double proximidad;
    private Double humedad;

    public ObjSensores() {
    }

    public ObjSensores(String idViaje, Double temperatura, Double proximidad, Double humedad) {
        this.idViaje = idViaje;
        this.temperatura = temperatura;
        this.proximidad = proximidad;
        this.humedad = humedad;
    }

    public String getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(String idViaje) {
        this.idViaje = idViaje;
    }

    public Double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

    public Double getProximidad() {
        return proximidad;
    }

    public void setProximidad(Double proximidad) {
        this.proximidad = proximidad;
    }

    public Double getHumedad() {
        return humedad;
    }

    public void setHumedad(Double humedad) {
        this.humedad = humedad;
    }
}
