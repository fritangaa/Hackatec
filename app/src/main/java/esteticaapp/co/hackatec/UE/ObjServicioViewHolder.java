package esteticaapp.co.hackatec.UE;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import esteticaapp.co.hackatec.R;
import esteticaapp.co.hackatec.clases.ItemLongClickListener;

public class ObjServicioViewHolder {

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener{

        CardView cardView;
        ImageView imagen;
        TextView servicio,costo;

        ItemLongClickListener itemLongClickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView=(CardView)itemView.findViewById(R.id.cardview_servicio);
            imagen=(ImageView)itemView.findViewById(R.id.obj_servicio_img);
            servicio=(TextView) itemView.findViewById(R.id.obj_servicio_nombre);
            costo=(TextView) itemView.findViewById(R.id.obj_servicio_costo);

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
