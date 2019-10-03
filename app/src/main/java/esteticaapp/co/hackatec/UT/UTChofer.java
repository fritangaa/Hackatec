package esteticaapp.co.hackatec.UT;

public class UTChofer {
    private String nombre;
    private String app;
    private String apm;
    private String nickname;
    private String email;
    private String pass;
    private String cuenta;
    private String tel;
    private String empresa;
    private String nss;
    private String sexo;

    public UTChofer(String nombre, String app, String apm,
                    String nickname, String email, String pass, String cuenta, String tel,
                    String empresa, String nss, String sexo) {
        this.nombre = nombre;
        this.app = app;
        this.apm = apm;
        this.nickname = nickname;
        this.email = email;
        this.pass = pass;
        this.cuenta = cuenta;
        this.tel = tel;
        this.empresa = empresa;
        this.nss = nss;
        this.sexo = sexo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getApm() {
        return apm;
    }

    public void setApm(String apm) {
        this.apm = apm;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getNss() {
        return nss;
    }

    public void setNss(String nss) {
        this.nss = nss;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
