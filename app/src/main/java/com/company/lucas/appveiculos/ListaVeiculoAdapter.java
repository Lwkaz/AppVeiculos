package com.company.lucas.appveiculos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class ListaVeiculoAdapter extends RecyclerView.Adapter<ListaVeiculoAdapter.ListaVeiculoHolder> {
    public class ListaVeiculoHolder extends RecyclerView.ViewHolder {
        private final TextView marca;
        private final TextView ano_lancamento;
        private final TextView descricao;
        private final Button editarVeiculo;
        private final Button deletarVeiculo;

        public ListaVeiculoHolder(View itemView) {
            super(itemView);
            marca = itemView.findViewById(R.id.marca);
            ano_lancamento = itemView.findViewById(R.id.ano_lancamento);
            descricao = itemView.findViewById(R.id.descricao);
            editarVeiculo = itemView.findViewById(R.id.editarVeiculo);
            deletarVeiculo = itemView.findViewById(R.id.deletarVeiculo);
        }
    }

    LayoutInflater layoutInflater;
    public List<Veiculo> veiculos;
    public Context context;

    ListaVeiculoAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    public void setVeiculos(List<Veiculo> veiculos) {
        this.veiculos = veiculos;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListaVeiculoHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = layoutInflater.inflate(R.layout.recyclerview_item,
                viewGroup, false);
        return new ListaVeiculoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListaVeiculoHolder holder, int position) {
        if(veiculos != null) {
            Veiculo current = veiculos.get(position);
            holder.marca.setText(current.getMarca());
            holder.ano_lancamento.setText(current.getAno_lancamento());
            holder.descricao.setText(current.getDescricao());
        } else {
            holder.marca.setText("Sem dados");
            holder.ano_lancamento.setText("Sem dados");
            holder.descricao.setText("Sem dados");
        }

        holder.editarVeiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(veiculos != null) {
                    Veiculo current = veiculos.get(holder.getAdapterPosition());
                    Intent intent = new Intent(context, EditActivity.class);
                    Bundle b = new Bundle();
                    b.putSerializable("veiculo", current);
                    intent.putExtras(b);
                    ((Activity)context).startActivityForResult(intent, MainActivity.EDIT_VEICULO_ACTIVITY_REQUEST_CODE);
                }
            }
        });

        holder.deletarVeiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
