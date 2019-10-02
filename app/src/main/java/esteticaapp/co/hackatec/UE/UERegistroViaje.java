package esteticaapp.co.hackatec.UE;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import esteticaapp.co.hackatec.R;

public class UERegistroViaje extends AppCompatActivity {

    EditText origen,destino,dimension,peso;
    DatePicker diaLlegada,diaSalida;
    TimePicker horaLlegada,horaSalida;
    Spinner tipoCarga;
    Button buscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ueregistro_viaje);
        origen = (EditText) findViewById(R.id.UEOrigen);
        destino = (EditText) findViewById(R.id.UEDestino);
        tipoCarga = (Spinner) findViewById(R.id.UETipoCarga);
        dimension = (EditText) findViewById(R.id.UEDimension);
        peso = (EditText) findViewById(R.id.UEPeso);
        horaLlegada = (TimePicker) findViewById(R.id.UEHoraLlegada);
        horaSalida = (TimePicker) findViewById(R.id.UEHoraSalida);
        diaLlegada = (DatePicker) findViewById(R.id.UEDiaLlegada);
        diaSalida = (DatePicker) findViewById(R.id.UEDiaSalida);
        buscar = (Button) findViewById(R.id.UEBuscarTransporte);
    }

    @Override
    public void onStart(){
        super.onStart();
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((!origen.getText().toString().isEmpty()) && (!destino.getText().toString().isEmpty())){
                    if(!tipoCarga.getSelectedItem().toString().equals("Elija una opción")){
                        if((!dimension.getText().toString().isEmpty()) && (!peso.getText().toString().isEmpty())){
                            Intent intent = new Intent(getApplicationContext(),UEViajesDisponibles.class);
                            intent.putExtra("ORIGEN", origen.getText().toString());
                            intent.putExtra("DESTINO",destino.getText().toString());
                            intent.putExtra("CARGA",tipoCarga.getSelectedItem().toString());
                            intent.putExtra("DIMENSION",dimension.getText().toString());
                            intent.putExtra("PESO",peso.getText().toString());
                            intent.putExtra("HORALLEGADA",horaLlegada.toString());
                            intent.putExtra("HORASALIDA",horaSalida.toString());
                            intent.putExtra("DIALLEGADA",diaLlegada.toString());
                            intent.putExtra("DIASALIDA",diaSalida.toString());
                            startActivity(intent);
                        }else{
                            Toast.makeText(getApplicationContext(),"Ingrese la dimensión y el peso del envío",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(),"Selecciona un tipo de carga",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Ingresa una dirección de origen y una de  destino",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
