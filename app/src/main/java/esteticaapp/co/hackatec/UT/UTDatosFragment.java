package esteticaapp.co.hackatec.UT;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import esteticaapp.co.hackatec.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link UTDatosFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link UTDatosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UTDatosFragment extends Fragment {


    private View view;

    private DatabaseReference datosDatabase;

    private FirebaseRecyclerAdapter<ObjSensores,ObjSensoresViewHolder.ViewHolder> adapterListaViajeDatos;
    private RecyclerView listaViajesDatos;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public UTDatosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UTDatosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UTDatosFragment newInstance(String param1, String param2) {
        UTDatosFragment fragment = new UTDatosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_utdatos, container, false);

        // Inflate the layout for this fragment
        datosDatabase = FirebaseDatabase.getInstance().getReference().child("/sensores/");

        listaViajesDatos = view.findViewById(R.id.listaTransportesDatos);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        listaViajesDatos.setLayoutManager(linearLayoutManager);


        adapterListaViajeDatos=new FirebaseRecyclerAdapter<ObjSensores, ObjSensoresViewHolder.ViewHolder>(
                ObjSensores.class,
                R.layout.obj_lista_viaje_datos,
                ObjSensoresViewHolder.ViewHolder.class,
                datosDatabase
        ) {
            @Override
            protected void populateViewHolder(final ObjSensoresViewHolder.ViewHolder viewHolder,
                                              final ObjSensores model, final int position) {
                viewHolder.nombre.setText("Viaje: "+model.getIdViaje());
                viewHolder.temperatura.setText("Temperatura en caja: "+String.valueOf(model.getTemperatura()));
                viewHolder.proximidad.setText("Distancia entre cargamento: "+String.valueOf(model.getProximidad()));
                viewHolder.humedad.setText("Humedad en caja: "+String.valueOf(model.getHumedad()));

                viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                    }
                });

            }
        };

        listaViajesDatos.setAdapter(adapterListaViajeDatos);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
