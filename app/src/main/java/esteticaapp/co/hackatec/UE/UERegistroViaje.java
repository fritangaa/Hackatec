package esteticaapp.co.hackatec.UE;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import esteticaapp.co.hackatec.R;

public class UERegistroViaje extends AppCompatActivity {

    EditText origen,destino,dimension,peso,diaLlegada,diaSalida;
    Spinner tipoCarga,horaLlegada,horaSalida;
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
        horaLlegada = (Spinner) findViewById(R.id.UEHoraLlegada);
        horaSalida = (Spinner) findViewById(R.id.UEHoraSalida);
        diaLlegada = (EditText) findViewById(R.id.UEDiaLlegada);
        diaSalida = (EditText) findViewById(R.id.UEDiaSalida);
        buscar = (Button) findViewById(R.id.UEBuscarTransporte);
    }

    @Override
    public void onStart(){
        super.onStart();
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((!origen.getText().toString().isEmpty()) || (!destino.getText().toString().isEmpty())){
                    if(!tipoCarga.getSelectedItem().toString().equals("Elija una opción")){
                        if((!dimension.getText().toString().isEmpty()) || (!peso.getText().toString().isEmpty())){
                            if((!horaLlegada.getSelectedItem().toString().isEmpty()) || (!horaSalida.getSelectedItem().toString().isEmpty())){
                                if(dimension.getText().toString().matches("[0-9]{2}(\\*)[0-9]{2}(\\*)[0-9]{2}")){
                                    if((diaLlegada.getText().toString().matches("([012][1-9]|3[01])(\\/)(0[1-9]|1[012])(\\/)2019"))
                                            && (diaSalida.getText().toString().matches("([012][1-9]|3[01])(\\/)(0[1-9]|1[012])(\\/)2019"))){
                                        Intent intent = new Intent(getApplicationContext(),UEViajeDisponible.class);
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
                                        Toast.makeText(getApplicationContext(), "El formato de los días debe ser dd/mm/yyyy", Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    Toast.makeText(getApplicationContext(), "El formato de las dimensiones deben ser 00*00*00", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(getApplicationContext(),"Ingrese la hora de llegada y de salida",Toast.LENGTH_SHORT).show();
                            }
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
