package esteticaapp.co.hackatec.UE;

public class ObjEmpresa {

    private String contrasena;
    private String nombre;
    private String correo;
    private String rfc;
    private String telefono;
    private String direccion;
    private String responsable;

    public ObjEmpresa() {
    }

    public ObjEmpresa(String contrasena, String nombre, String correo, String rfc, String telefono, String direccion, String responsable) {
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.correo = correo;
        this.rfc = rfc;
        this.telefono = telefono;
        this.direccion = direccion;
        this.responsable = responsable;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }
}
