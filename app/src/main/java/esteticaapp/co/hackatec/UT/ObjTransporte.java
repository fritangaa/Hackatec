package esteticaapp.co.hackatec.UT;

public class ObjTransporte {
    private String placa;
    private int tonelaje;
    private String tipo;
    private String empresa;

    public ObjTransporte(String empresa,String placa, String tipo, int tonelaje){
        this.placa = placa;
        this.tonelaje = tonelaje;
        this.tipo = tipo;
        this.empresa = empresa;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getTonelaje() {
        return tonelaje;
    }

    public void setTonelaje(int tonelaje) {
        this.tonelaje = tonelaje;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
}
