package esteticaapp.co.hackatec.UE;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import esteticaapp.co.hackatec.R;
import esteticaapp.co.hackatec.clases.ItemLongClickListener;

public class ObjViajeViewHolder {

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener{

        public CardView cardView;
        public ImageView logo;
        public TextView nombre,fechaSalida,fechaLlegada,precio;

        ItemLongClickListener itemLongClickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView=(CardView)itemView.findViewById(R.id.cardview_viaje);
            logo=(ImageView)itemView.findViewById(R.id.obj_viaje_imagen);
            nombre=(TextView) itemView.findViewById(R.id.obj_viaje_nombre);
            fechaSalida=(TextView) itemView.findViewById(R.id.obj_viaje_fechaSalida);
            fechaLlegada=(TextView) itemView.findViewById(R.id.obj_viaje_fechaLlegada);
            precio=(TextView) itemView.findViewById(R.id.obj_viaje_precio);

            itemView.setOnLongClickListener(this);
        }

        public void setItemLongClickListener(ItemLongClickListener ic)
        {
            this.itemLongClickListener=ic;
        }

        @Override
        public boolean onLongClick(View v) {
            this.itemLongClickListener.onItemLongClick(v,getLayoutPosition());
            return false;
        }
    }

}
