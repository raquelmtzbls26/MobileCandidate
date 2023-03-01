package com.example.mobile_candidate.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_candidate.InfoCompleta;
import com.example.mobile_candidate.Modelo.Result;
import com.example.mobile_candidate.R;

import java.util.List;

public class ApiAdapter extends RecyclerView.Adapter<ApiAdapter.ViewHolder> {
   //Se encargara de guardar el listado
    private List<Result> apipersona;
    //Permitira acceder al contexto del activity que muestra el recyclerView
    private Context context;

    public ApiAdapter(List<Result> apipersona, Context applicationContext) {
        this.apipersona = apipersona;
    }

    public ApiAdapter(Context context) {
        this.context = context;
    }

    //Se encarga de especificar el xml
    @NonNull
    @Override
    public ApiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_persona, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }
//Funcion de colocar los componentes
    @Override
    public void onBindViewHolder(@NonNull ApiAdapter.ViewHolder holder, int position) {
        holder.Tx_Name.setText("Nombre: "+ apipersona.get(position).getName().getFullName());
        holder.Tx_Email.setText("Email: "+apipersona.get(position).getEmail());
        holder.Tx_Genero.setText("Genero: "+apipersona.get(position).getGender());

        holder.cardView.setAnimation(AnimationUtils.loadAnimation(context, R.anim.slide));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(context, InfoCompleta.class);
                    intent.putExtra("name", apipersona.get(position).getName().getFullName());
                    intent.putExtra("picture", apipersona.get(position).getPicture().getLarge());
                    intent.putExtra("phone", apipersona.get(position).getCell());
                    intent.putExtra("location", apipersona.get(position).getLocation().getFullAddress());
                    context.startActivity(intent);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

    }
//Especifica la cantidad de items
    @Override
    public int getItemCount() {
        return apipersona.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView Tx_Name, Tx_Email, Tx_Genero, txtvTelefono, txtvDireccion,tx_Name1;
        private ImageView imgvPersona;
        private CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Tx_Name = itemView.findViewById(R.id.Tx_Name);
            Tx_Email = itemView.findViewById(R.id.Tx_Email);
            Tx_Genero = itemView.findViewById(R.id.Tx_Genero);
            txtvTelefono = itemView.findViewById(R.id.txtvTelefono);
            txtvDireccion = itemView.findViewById(R.id.txtvDireccion);
            imgvPersona = itemView.findViewById(R.id.imgvPersona);
            tx_Name1 = itemView.findViewById(R.id.tx_Name1);
            cardView = itemView.findViewById(R.id.tarjeta);
        }
    }
}
