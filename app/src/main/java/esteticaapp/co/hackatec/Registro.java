package esteticaapp.co.hackatec;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Calendar;

import esteticaapp.co.hackatec.UE.ObjTransportistas;
import esteticaapp.co.hackatec.UT.UTInicio;

public class Registro extends AppCompatActivity {

    private DatabaseReference databaseReference;

    private RadioGroup opcionRegistro;
    private RelativeLayout registroEmpresa;
    private RelativeLayout registroTransportista;
    private TextView txtTrans;
    private TextView txtEmp;

    private static final String CERO = "0";
    private static final String BARRA = "/";

    //Calendario para obtener fecha & hora
    public final Calendar c = Calendar.getInstance();

    //Variables para obtener la fecha
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);
    private int anioSeleccion;
    private int diaSeleccionado;
    private int mesSeleccionado;

    private MaterialDialog dialog;

    private Button botonRegistrarTransportista;

    private EditText textoTNombre;
    private EditText textoTTelefono;
    private EditText textoTRFC;
    private EditText textoTCorreo;
    private EditText textoTContrasena;

    private FirebaseAuth auth;
    private StorageReference mStorage;

    private ImageView imagenRPerfil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        databaseReference= FirebaseDatabase.getInstance().getReference();

        opcionRegistro = (RadioGroup) findViewById(R.id.usuarioRegistro);//Grupo de opciones para armar cita
        final RadioButton regEmpresa = (RadioButton) opcionRegistro.getChildAt(1);
        final RadioButton regTransportista = (RadioButton) opcionRegistro.getChildAt(2);

        registroEmpresa = (RelativeLayout) findViewById(R.id.relativeEmpresa);
        registroTransportista = (RelativeLayout) findViewById(R.id.relativeTransportista);

        txtTrans = (TextView) findViewById(R.id.txtTransportista);
        txtEmp = (TextView) findViewById(R.id.txtEmpresa);


        auth = FirebaseAuth.getInstance();
        mStorage= FirebaseStorage.getInstance().getReference();

        imagenRPerfil = (ImageView) findViewById(R.id.imagenTPerfil);

        textoTNombre = (EditText) findViewById(R.id.textoTNombre);
        textoTTelefono = (EditText) findViewById(R.id.textoTTelefono);
        textoTRFC = (EditText) findViewById(R.id.textoTRFC);
        textoTCorreo = (EditText) findViewById(R.id.textoTCorreo);
        textoTContrasena = (EditText) findViewById(R.id.textoTContrasena);

        botonRegistrarTransportista = (Button) findViewById(R.id.botonTGuardar);

        MaterialDialog.Builder builder = new MaterialDialog.Builder(this)
                .title("Registrando")
                .content("Espere...")
                .cancelable(false)
                .progress(true, 0);
        dialog = builder.build();

        botonRegistrarTransportista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validaUsuario();

            }
        });

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

    private void validaUsuario(){

        //Obtenemos el email y la contraseña desde las cajas de texto
        final String password  = textoTContrasena.getText().toString().trim();
        final String nombre = textoTNombre.getText().toString();
        final String correo = textoTCorreo.getText().toString();
        final String rfc = textoTRFC.getText().toString();
        final String telefono = textoTTelefono.getText().toString();

        //Verificamos que las cajas de texto no esten vacías


        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Falta ingresar la contraseña",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(nombre)){
            Toast.makeText(this,"Falta ingresar el nombre",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(telefono)){
            Toast.makeText(this,"Falta ingresar el telefono",Toast.LENGTH_SHORT).show();
            return;
        }else if(telefono.length()<10){
            Toast.makeText(this,"El telefono no esta completo",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(correo)){
            Toast.makeText(this,"Falta ingresar el correo",Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(rfc)){
            Toast.makeText(this,"Falta seleccionar imagen de perfil",Toast.LENGTH_SHORT).show();
            return;
        }

        dialog.show();
        //creating a new user
        auth.createUserWithEmailAndPassword(correo, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder()
                            .setDisplayName(nombre)
                            .build();
                    FirebaseUser user = auth.getCurrentUser();
                    if (user!=null)
                        user.updateProfile(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    ObjTransportistas nuevoTransportista = new ObjTransportistas(nombre,correo,password, rfc, telefono);

                                    databaseReference.child("usuarioTransportistas").push().setValue(nuevoTransportista);

                                    Intent siguiente = new Intent(Registro.this, UTInicio.class);//vamos a la ventana de la confirmacion
                                    dialog.dismiss();
                                    startActivity(siguiente);
                                    finish();

                                }else if (task.getException()!=null){
                                    Toast.makeText(Registro.this,"No se pudo registrar el usuario ",Toast.LENGTH_LONG).show();
                                }
                                dialog.dismiss();
                            }
                        });
                }else if (task.getException()!=null){
                    Toast.makeText(Registro.this,"Error en correo o contraseña",Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }


            }
        });


    }

    private void obtenerFecha(){
        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                final int mesActual = month + 1;
                //Formateo el día obtenido: antepone el 0 si son menores de 10
                String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                //Formateo el mes obtenido: antepone el 0 si son menores de 10
                String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);
                //Muestro la fecha con el formato deseado
                //textoREdadUM.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);
                anioSeleccion = year;
                mesSeleccionado = Integer.parseInt(mesFormateado);
                diaSeleccionado = Integer.parseInt(diaFormateado);

            }
            //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
            /**
             *También puede cargar los valores que usted desee
             */
        },anio, mes, dia);
        //Muestro el widget
        recogerFecha.show();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent siguiente = new Intent(Registro.this, Login.class);//vamos a la ventana de la confirmacion
        startActivity(siguiente);
        finish();
    }
}
