package esteticaapp.co.hackatec.UE;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import esteticaapp.co.hackatec.R;

public class UEInicio extends AppCompatActivity implements OnMapReadyCallback{

    private GoogleMap mMap;
    private MapView mMapView;

    private DatabaseReference transportistasDatabase;

    private FirebaseRecyclerAdapter<ObjTransportistas,ObjTransportistasViewHolder.ViewHolder> adapterListaTransportistas;
    private RecyclerView listaTransportistas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ueinicio);


        mMapView = findViewById(R.id.mapaUbicacionTransporte);
        if (mMapView != null) {
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }


        transportistasDatabase= FirebaseDatabase.getInstance().getReference().child("/transportistas/");

        listaTransportistas = findViewById(R.id.listaTransportes);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(UEInicio.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        listaTransportistas.setLayoutManager(linearLayoutManager);


        adapterListaTransportistas=new FirebaseRecyclerAdapter<ObjTransportistas, ObjTransportistasViewHolder.ViewHolder>(
                ObjTransportistas.class,
                R.layout.obj_lista_transportistas,
                ObjTransportistasViewHolder.ViewHolder.class,
                transportistasDatabase
        ) {
            @Override
            protected void populateViewHolder(final ObjTransportistasViewHolder.ViewHolder viewHolder,
                                              final ObjTransportistas model, final int position) {
                viewHolder.nombre.setText(model.getNombre());
                viewHolder.fechaLlegada.setText(model.getDiaLlegada()+" - "+model.getHoraLlegada());
                viewHolder.fechaSalida.setText(model.getDiaSalida()+" - "+model.getHoraSalida());
                viewHolder.precio.setText(String.valueOf(model.getPrecio()));

                viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        viewHolder.cardView.setBackgroundColor(ContextCompat.getColor(UEInicio.this, R.color.colorGrisClaro));
                    }
                });

            }
        };

        listaTransportistas.setAdapter(adapterListaTransportistas);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        // Controles UI
        if (ContextCompat.checkSelfPermission(UEInicio.this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(UEInicio.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Mostrar diálogo explicativo
            } else {
                // Solicitar permiso
                ActivityCompat.requestPermissions(
                        UEInicio.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        1);
            }
        }

        mMap.getUiSettings().setZoomControlsEnabled(true);

        LatLng ediTics = new LatLng(19.257010, -99.579551);
        mMap.setMyLocationEnabled(true);
        CameraPosition cameraPosition = new CameraPosition.Builder().target(ediTics).zoom(15).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


    }
}
