package com.example.mobile_candidate.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mobile_candidate.InfoCompleta;
import com.example.mobile_candidate.Modelo.Result;
import com.example.mobile_candidate.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ApiAdapter extends RecyclerView.Adapter<ApiAdapter.ViewHolder> {
   //Se encargara de guardar el listado
    private ArrayList<Result> apipersona;
    private ArrayList<Result> apipersonaoriginal;

    //Permitira acceder al contexto del activity que muestra el recyclerView
    private Context context;

    public ApiAdapter(ArrayList<Result> apipersona, Context applicationContext) {
        this.apipersona = apipersona;
        apipersonaoriginal = new ArrayList<>();
        apipersonaoriginal.addAll(apipersona);
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
    public void onBindViewHolder(@NonNull ApiAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Glide.with(context).load(apipersona.get(position).getPicture().getLarge()).into(holder.imgPersonaMain);
        holder.Tx_Name.setText("Nombre: "+ apipersona.get(position).getName().getFullName());
        holder.Tx_Email.setText(apipersona.get(position).getEmail());
        if(apipersona.get(position).getGender().equals("female") || apipersona.get(position).getGender().equals("Mujer") ) {
            apipersona.get(position).setGender("Mujer");
            holder.Tx_Genero.setText("Genero: " + apipersona.get(position).getGender());
        }else {
            apipersona.get(position).setGender("Hombre");
            holder.Tx_Genero.setText("Genero: " + apipersona.get(position).getGender());
        }

        holder.cardView.setAnimation(AnimationUtils.loadAnimation(context, R.anim.slide));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(context, InfoCompleta.class);
                    intent.putExtra("name", apipersona.get(position).getName().getFullName());
                    intent.putExtra("picture", apipersona.get(position).getPicture().getLarge());
                    intent.putExtra("phone", apipersona.get(position).getPhone());
                    intent.putExtra("cell", apipersona.get(position).getCell());
                    intent.putExtra("location", apipersona.get(position).getLocation().getFullAddress());
                    intent.putExtra("location1", apipersona.get(position).getLocation().getCoordinates().getFullCoordenadas());
                    context.startActivity(intent);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

    }

    public void filtrado(String txtBuscar){
        int longitud = txtBuscar.length();
        if(longitud==0){
            apipersona.clear();
            apipersona.addAll(apipersonaoriginal);
        }else {
            List<Result> collection = apipersona.stream().filter(i -> i.getName().getFullName().toLowerCase().contains(txtBuscar.toLowerCase()))
                    .collect(Collectors.toList());
            apipersona.clear();
            apipersona.addAll(collection);
        }
        notifyDataSetChanged();
    }
//Especifica la cantidad de items
    @Override
    public int getItemCount() {
        return apipersona.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView Tx_Name, Tx_Email, Tx_Genero, txtvTelefono, txtvDireccion,tx_Name1,txtvVCell;
        private ImageView imgvPersona, imgPersonaMain;
        private CardView cardView,cardViewCarga1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Tx_Name = itemView.findViewById(R.id.Tx_Name);
            Tx_Email = itemView.findViewById(R.id.Tx_Email);
            Tx_Genero = itemView.findViewById(R.id.Tx_Genero);
            imgPersonaMain = itemView.findViewById(R.id.imgPersonaMain);
            txtvTelefono = itemView.findViewById(R.id.txtvTelefono);
            txtvVCell = itemView.findViewById(R.id.txtvVCell);
            txtvDireccion = itemView.findViewById(R.id.txtvDireccion);
            imgvPersona = itemView.findViewById(R.id.imgvPersona);
            tx_Name1 = itemView.findViewById(R.id.tx_Name1);
            cardView = itemView.findViewById(R.id.tarjeta);
            cardViewCarga1 = itemView.findViewById(R.id.cargatarjeta1);

            Tx_Email.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("mailto:"+ Tx_Email.getText().toString()));
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{Tx_Email.getText().toString()});
                    context.startActivity(intent);
                }
            });

          /*  txtvDireccion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:" + txtvDireccion.getText().toString()));
                    context.startActivity(intent);
                }
            });
*/

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    cardViewCarga1.setVisibility(View.GONE);
                    cardView.setVisibility(View.VISIBLE);
                }
            }, 2000);
        }

     /*   public void enviar(View v){
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:"));
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{Tx_Email.getText().toString()});
            context.startActivity(intent);
        }*/
    }

}
