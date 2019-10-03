package esteticaapp.co.hackatec.UT;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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
import com.google.firebase.database.Query;

import java.util.Random;

import esteticaapp.co.hackatec.R;
import esteticaapp.co.hackatec.UE.ObjViaje;
import esteticaapp.co.hackatec.UE.ObjViajeViewHolder;

public class UTInicio extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, UTDatosFragment.OnFragmentInteractionListener, UTNotificacionesFragment.OnFragmentInteractionListener, OnMapReadyCallback {

    private GoogleMap mMap;
    private MapView mMapView;

    private DatabaseReference viajesDatabase;
    private Query consulta;

    private FirebaseRecyclerAdapter<ObjViaje, ObjViajeViewHolder.ViewHolder> adapterListaViaje;
    private RecyclerView listaViajes;

    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utinicio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mMapView = findViewById(R.id.mapaUbicacionTransportista);
        if (mMapView != null) {
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }

        viajesDatabase = FirebaseDatabase.getInstance().getReference().child("/usuarioTransportistas/");

        listaViajes = findViewById(R.id.listaTransportista);

        if (random.nextBoolean()) {
            consulta = viajesDatabase.child("empresa").equalTo("dhl");
        } else {
            consulta = viajesDatabase.child("empresa").equalTo("estafeta");
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(UTInicio.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        listaViajes.setLayoutManager(linearLayoutManager);


        adapterListaViaje = new FirebaseRecyclerAdapter<ObjViaje, ObjViajeViewHolder.ViewHolder>(
                ObjViaje.class,
                R.layout.obj_lista_viaje,
                ObjViajeViewHolder.ViewHolder.class,
                consulta
        ) {
            @Override
            protected void populateViewHolder(final ObjViajeViewHolder.ViewHolder viewHolder,
                                              final ObjViaje model, final int position) {
                viewHolder.nombre.setText(model.getNombreChofer());
                viewHolder.fechaLlegada.setText(model.getDiaLlegada() + " - " + model.getHoraLlegada());
                viewHolder.fechaSalida.setText(model.getDiaSalida() + " - " + model.getHoraSalida());
                viewHolder.precio.setText(String.valueOf(model.getCosto()));

                viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        LatLng puntoViaje = new LatLng(model.getLatitud(), model.getLongitud());
                        mMap.addMarker(new MarkerOptions()
                                .position(puntoViaje)
                                .title(model.getNombreChofer())
                                .snippet(model.getLatitud() + "," + model.getLongitud())
                                .icon(bitmapDescriptorFromVector(UTInicio.this, R.drawable.ic_transporte))
                        );
                        CameraPosition cameraPosition = new CameraPosition.Builder().target(puntoViaje).zoom(15).build();
                        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            System.out.println("PAUSA");
                        }
                        ;

                        Intent intent = new Intent(getApplicationContext(), UTDatosServicio.class);
                        intent.putExtra("NOMBRE", model.getNombreChofer());
                        intent.putExtra("ESTADO", model.getDestino());
                        intent.putExtra("CARGA", model.getTipoCarga());
                        intent.putExtra("KM", model.getKilometraje());
                        startActivity(intent);
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
        if (ContextCompat.checkSelfPermission(UTInicio.this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(UTInicio.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Mostrar diálogo explicativo
            } else {
                // Solicitar permiso
                ActivityCompat.requestPermissions(
                        UTInicio.this,
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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.utinicio, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment miFragment = null;
        boolean fragmentSeleccionado = false;


        if (id == R.id.nav_notificaciones) {
            miFragment = new UTNotificacionesFragment();
            fragmentSeleccionado = true;
        } else if (id == R.id.nav_datos) {
            miFragment = new UTDatosFragment();
            fragmentSeleccionado = true;
        } else if (id == R.id.navigation_new) {
            Intent intent = new Intent(getApplicationContext(), UTNuevoTransporte.class);
            startActivity(intent);
        } else if (id == R.id.navigation_driver) {
            Intent intent = new Intent(getApplicationContext(), UTNuevoChofer.class);
            startActivity(intent);

        }

        if (fragmentSeleccionado == true) {
            getSupportFragmentManager().beginTransaction().replace(R.id.hola, miFragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onFragmentInteraction (Uri uri){

    }

    @Override
    public void onPointerCaptureChanged ( boolean hasCapture){

    }
}

