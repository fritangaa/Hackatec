package esteticaapp.co.hackatec.UE;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import esteticaapp.co.hackatec.R;
import esteticaapp.co.hackatec.UT.UTInicio;

public class UEViajeDisponible extends AppCompatActivity {

    private DatabaseReference serviciosDatabase;

    private FirebaseRecyclerAdapter<ObjViaje,ObjServicioViewHolder.ViewHolder> adapterListaServicios;
    private RecyclerView listaServicios;

    private ImageView imagenServicio;
    private TextView empresaServicio;
    private TextView origendestinoServicio;
    private TextView salidaServicio;
    private TextView llegadaServicio;
    private TextView rutaServicio;
    private TextView costoServicio;

    private Button Registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ueviaje_disponible);

        imagenServicio = (ImageView) findViewById(R.id.imagenServicio);
        empresaServicio = (TextView) findViewById(R.id.empresaServicio);
        origendestinoServicio = (TextView) findViewById(R.id.origendestinoServicio);
        salidaServicio = (TextView) findViewById(R.id.salidaServicio);
        llegadaServicio = (TextView) findViewById(R.id.llegadaServicio);
        rutaServicio = (TextView) findViewById(R.id.rutaServicio);
        costoServicio = (TextView) findViewById(R.id.costoServicio);

        Registrar  = (Button) findViewById(R.id.agregarServicio);

        serviciosDatabase = FirebaseDatabase.getInstance().getReference().child("/viajes/");

        listaServicios = findViewById(R.id.listaViajesDisponibles);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(UEViajeDisponible.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        listaServicios.setLayoutManager(linearLayoutManager);


        Registrar.setOnClickListener(new View.OnClickListener() {//pasar a la siguiente pantalla
            @Override
            public void onClick(View v) {//el evento de click para confirmar la cita
                //databaseReference.child("Cliente").child("-LZ7qvRwVs31M-cE2G8D").child("promociones").push().child("nombre").setValue(txtCorreo.getText().toString());
                Toast.makeText(UEViajeDisponible.this, "Servicio contratado", Toast.LENGTH_SHORT).show();
                Intent intencion = new Intent(getApplication(), UEInicio.class);
                startActivity(intencion);
                finish();
            }
        });

        adapterListaServicios=new FirebaseRecyclerAdapter<ObjViaje, ObjServicioViewHolder.ViewHolder>(
                ObjViaje.class,
                R.layout.obj_lista_servicio_disponible,
                ObjServicioViewHolder.ViewHolder.class,
                serviciosDatabase
        ) {
            @Override
            protected void populateViewHolder(final ObjServicioViewHolder.ViewHolder viewHolder,
                                              final ObjViaje model, final int position) {
                viewHolder.servicio.setText(model.getNombreChofer());
                //viewHolder.imagen
                viewHolder.costo.setText(String.valueOf(model.getCosto()));

                viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        empresaServicio.setText(model.getEmpresa());
                        origendestinoServicio.setText(model.getOrigen()+" a "+model.getDestino());
                        salidaServicio.setText(model.getDiaSalida());
                        llegadaServicio.setText(model.getDiaLlegada());
                        rutaServicio.setText(model.getRuta());
                        costoServicio.setText(String.valueOf(model.getCosto()));

                    }
                });

            }
        };

        listaServicios.setAdapter(adapterListaServicios);
    }


}
