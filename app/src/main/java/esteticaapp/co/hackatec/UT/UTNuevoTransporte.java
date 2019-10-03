package esteticaapp.co.hackatec.UT;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import esteticaapp.co.hackatec.R;

public class UTNuevoTransporte extends AppCompatActivity {

    EditText placas,tonelaje,tipo,empresa;
    Button registrar;

    private DatabaseReference reference;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utnuevo_transporte);

        reference = FirebaseDatabase.getInstance().getReference();

        placas = (EditText) findViewById(R.id.UTPlacas);
        tonelaje = (EditText) findViewById(R.id.UTTonelaje);
        tipo = (EditText) findViewById(R.id.UTTipo);
        empresa = (EditText) findViewById(R.id.UTEmpresa);
        registrar = (Button) findViewById(R.id.UTRegistrar);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((!placas.getText().toString().isEmpty()) || (placas.getText().toString().length() != 7)){
                    if((!tonelaje.getText().toString().isEmpty()) && (!tipo.getText().toString().isEmpty()) && (!empresa.getText().toString().isEmpty())){
                        ObjTransporte transporte = new ObjTransporte(empresa.getText().toString(), placas.getText().toString()
                                , tipo.getText().toString(),Integer.parseInt(tonelaje.getText().toString()));
                        reference.child("Vehiculo").push().setValue(transporte);
                        Intent siguiente = new Intent(UTNuevoTransporte.this, UTInicio.class);
                        Toast.makeText(getApplicationContext(),"Registro exitoso",Toast.LENGTH_SHORT).show();
                        startActivity(siguiente);
                        finish();
                    }else{
                        Toast.makeText(getApplicationContext(),"Llene todos los campos",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Ingrese los 7 digitos de placa",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
