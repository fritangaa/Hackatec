package esteticaapp.co.hackatec;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Registro extends AppCompatActivity {

    private RadioGroup opcionRegistro;
    private RelativeLayout registroEmpresa;
    private RelativeLayout registroTransportista;
    private TextView txtTrans;
    private TextView txtEmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        opcionRegistro = (RadioGroup) findViewById(R.id.usuarioRegistro);//Grupo de opciones para armar cita
        final RadioButton regEmpresa = (RadioButton) opcionRegistro.getChildAt(1);
        final RadioButton regTransportista = (RadioButton) opcionRegistro.getChildAt(2);

        registroEmpresa = (RelativeLayout) findViewById(R.id.relativeEmpresa);
        registroTransportista = (RelativeLayout) findViewById(R.id.relativeTransportista);

        txtTrans = (TextView) findViewById(R.id.txtTransportista);
        txtEmp = (TextView) findViewById(R.id.txtEmpresa);
    }

    public void eveclik (View view) {//boton de regresar a ver el calendario, por si no fue el dia que tu querias, y quieres ver de nuev el calendario

        switch (view.getId()) {

            case R.id.registroTransportista:
                registroTransportista.setVisibility(View.VISIBLE);
                registroEmpresa.setVisibility(View.INVISIBLE);
                txtTrans.setVisibility(View.VISIBLE);
                txtEmp.setVisibility(View.INVISIBLE);
                break;

            case R.id.registroEmpresa:
                registroTransportista.setVisibility(View.INVISIBLE);
                registroEmpresa.setVisibility(View.VISIBLE);
                txtTrans.setVisibility(View.INVISIBLE);
                txtEmp.setVisibility(View.VISIBLE);
                break;

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent siguiente = new Intent(Registro.this, Login.class);//vamos a la ventana de la confirmacion
        startActivity(siguiente);
        finish();
    }
}
