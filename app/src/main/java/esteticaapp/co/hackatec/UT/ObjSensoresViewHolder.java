package esteticaapp.co.hackatec.UT;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import esteticaapp.co.hackatec.R;
import esteticaapp.co.hackatec.clases.ItemLongClickListener;

public class ObjSensoresViewHolder {

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener{

        CardView cardView;
        TextView nombre,humedad,proximidad,temperatura;

        ItemLongClickListener itemLongClickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView=(CardView)itemView.findViewById(R.id.cardview_viaje_datos);
            nombre=(TextView) itemView.findViewById(R.id.obj_viaje_nombre);
            humedad=(TextView) itemView.findViewById(R.id.obj_viaje_humedad);
            proximidad=(TextView) itemView.findViewById(R.id.obj_viaje_proximidad);
            temperatura=(TextView) itemView.findViewById(R.id.obj_viaje_temperatura);

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
