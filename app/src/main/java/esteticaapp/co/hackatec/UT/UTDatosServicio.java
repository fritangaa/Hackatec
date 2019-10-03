package esteticaapp.co.hackatec.UT;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import esteticaapp.co.hackatec.R;

public class UTDatosServicio extends AppCompatActivity {

    TextView nombre, estado, carga, km;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utdatos_servicio);
        nombre = (TextView) findViewById(R.id.UTDatoNombre);
        estado = (TextView) findViewById(R.id.UTDatoEstado);
        carga = (TextView) findViewById(R.id.UTDatoCarga);
        km = (TextView) findViewById(R.id.UTDatoKm);

        Bundle bundle = new Bundle();
        nombre.setText(bundle.getString("NOMBRE"));
        estado.setText(bundle.getString("ESTADO"));
        carga.setText(bundle.getString("CARGA"));
        km.setText(bundle.getString("KM"));
    }
}
