package com.example.listadetarefas.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listadetarefas.R;
import com.example.listadetarefas.model.Tarefa;

import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private List<Tarefa> tarefaList;

    public Adapter(List<Tarefa> t) {
        tarefaList = t;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_lista, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Tarefa t = tarefaList.get(position);
        holder.titulo.setText(t.getTarefa());
    }

    @Override
    public int getItemCount() {
        return tarefaList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView titulo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.titulo);
        }
    }
}
