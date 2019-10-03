package esteticaapp.co.hackatec.UT;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import esteticaapp.co.hackatec.R;

public class UTNuevoChofer extends AppCompatActivity {

    EditText nombre, app, apm, nickname,email, pass, pass2,cuenta, tel, empresa, nss;
    Spinner sexo;
    Button registrar;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utnuevo_chofer);

        reference = FirebaseDatabase.getInstance().getReference();

        nombre = findViewById(R.id.UTChoferNombre);
        app = findViewById(R.id.UTChoferApellidoP);
        apm = findViewById(R.id.UTChoferApellidoM);
        nickname = findViewById(R.id.UTChoferNickname);
        email = findViewById(R.id.UTChoferEmail);
        pass = findViewById(R.id.UTChoferContrasenia);
        pass2 = findViewById(R.id.UTChoferContrasenia2);
        cuenta = findViewById(R.id.UTChoferCuenta);
        tel = findViewById(R.id.UTChoferTel);
        empresa = findViewById(R.id.UTChoferEmpresa);
        nss = findViewById(R.id.UTChoferNSS);
        sexo = findViewById(R.id.UTChoferSexo);
        registrar = findViewById(R.id.UTChoferRegistrar);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((!nombre.getText().toString().isEmpty()) || (!app.getText().toString().isEmpty()) || (!apm.getText().toString().isEmpty())
                || (!nickname.getText().toString().isEmpty()) || (!email.getText().toString().isEmpty()) || (!pass.getText().toString().isEmpty())
                || (!pass2.getText().toString().isEmpty()) || (!cuenta.getText().toString().isEmpty()) || (!tel.getText().toString().isEmpty())
                || (!empresa.getText().toString().isEmpty()) || (!nss.getText().toString().isEmpty()) || (!sexo.getSelectedItem().toString().equalsIgnoreCase("Elija una opción"))){
                    UTChofer chofer = new UTChofer(nombre.getText().toString(),app.getText().toString(),apm.getText().toString(),
                            nickname.getText().toString(),email.getText().toString(),pass.getText().toString(),cuenta.getText().toString(),tel.getText().toString(),
                            empresa.getText().toString(),nss.getText().toString(),sexo.getSelectedItem().toString());
                    reference.child("Chofer").push().setValue(chofer);
                    Intent siguiente = new Intent(UTNuevoChofer.this, UTInicio.class);
                    Toast.makeText(getApplicationContext(),"Registro exitoso",Toast.LENGTH_SHORT).show();
                    startActivity(siguiente);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"Llene todos los campos",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
