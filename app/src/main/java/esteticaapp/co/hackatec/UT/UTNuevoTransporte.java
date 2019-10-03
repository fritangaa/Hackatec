package esteticaapp.co.hackatec.UT;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import esteticaapp.co.hackatec.R;

public class UTNuevoTransporte extends AppCompatActivity {

    EditText placas,tonelaje,tipo,empresa;
    Button registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utnuevo_transporte);

        placas = (EditText) findViewById(R.id.UTPlacas);
        tonelaje = (EditText) findViewById(R.id.UTTonelaje);
        tipo = (EditText) findViewById(R.id.UTTipo);
        empresa = (EditText) findViewById(R.id.UTEmpresa);
        registrar = (Button) findViewById(R.id.UTRegistrar);

        


    }
}
