package esteticaapp.co.hackatec.UE;

import android.Manifest;
import android.content.Intent;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
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
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.widget.Button;

import esteticaapp.co.hackatec.R;

public class UEInicio extends AppCompatActivity implements OnMapReadyCallback{

    private GoogleMap mMap;
    private MapView mMapView;

    private DatabaseReference viajesDatabase;

    private FirebaseRecyclerAdapter<ObjViaje,ObjViajeViewHolder.ViewHolder> adapterListaViaje;
    private RecyclerView listaViajes;
    
    Button Registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ueinicio);
        
        Registrar = (Button) findViewById(R.id.Registrar);
        
        Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UERegistroViaje.class);
                startActivity(intent);
            }
        });

        mMapView = findViewById(R.id.mapaUbicacionTransporte);
        if (mMapView != null) {
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }


        viajesDatabase = FirebaseDatabase.getInstance().getReference().child("/viajes/");

        listaViajes = findViewById(R.id.listaTransportes);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(UEInicio.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        listaViajes.setLayoutManager(linearLayoutManager);


        adapterListaViaje=new FirebaseRecyclerAdapter<ObjViaje, ObjViajeViewHolder.ViewHolder>(
                ObjViaje.class,
                R.layout.obj_lista_viaje,
                ObjViajeViewHolder.ViewHolder.class,
                viajesDatabase
        ) {
            @Override
            protected void populateViewHolder(final ObjViajeViewHolder.ViewHolder viewHolder,
                                              final ObjViaje model, final int position) {
                viewHolder.nombre.setText(model.getNombreChofer());
                viewHolder.fechaLlegada.setText(model.getDiaLlegada()+" - "+model.getHoraLlegada());
                viewHolder.fechaSalida.setText(model.getDiaSalida()+" - "+model.getHoraSalida());
                viewHolder.precio.setText(String.valueOf(model.getCosto()));

                viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        LatLng puntoViaje = new LatLng(model.getLatitud(), model.getLongitud());
                        mMap.addMarker(new MarkerOptions()
                                .position(puntoViaje)
                                .title(model.getNombreChofer())
                                .snippet(model.getLatitud()+","+model.getLongitud())
                                .icon(bitmapDescriptorFromVector(UEInicio.this,R.drawable.ic_transporte))
                        );
                        CameraPosition cameraPosition = new CameraPosition.Builder().target(puntoViaje).zoom(15).build();
                        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                    }
                });

            }
        };

        listaViajes.setAdapter(adapterListaViaje);
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
        mMap.setMyLocationEnabled(true);

    }
    private BitmapDescriptor bitmapDescriptorFromVector(Context context, @DrawableRes int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
}
