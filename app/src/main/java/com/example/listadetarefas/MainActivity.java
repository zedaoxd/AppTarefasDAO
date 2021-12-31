package com.example.listadetarefas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.listadetarefas.activity.AdicionarTarefaActivity;
import com.example.listadetarefas.adapter.Adapter;
import com.example.listadetarefas.model.Tarefa;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;


import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listadetarefas.databinding.ActivityMainBinding;
import com.example.listadetarefas.utils.RecyclerItemClickListener;
import com.example.listadetarefas.DAO.TarefaDAO;

import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private List<Tarefa> tarefaList = new ArrayList<>();
    private Tarefa selectTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        clickFAB();
        clickList();
    }

    @Override
    protected void onStart() {
        super.onStart();
        settingsRecyclerView();
    }

    private void clickFAB(){
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdicionarTarefaActivity.class);
                startActivity(intent);
            }
        });
    }

    private void settingsRecyclerView(){
        TarefaDAO dao = new TarefaDAO(getApplicationContext());
        tarefaList = dao.listar();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        binding.include.recyclerListaTarefas.setLayoutManager(layoutManager);
        binding.include.recyclerListaTarefas.setHasFixedSize(true);
        binding.include.recyclerListaTarefas.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        binding.include.recyclerListaTarefas.setAdapter(new Adapter(tarefaList));
    }

    private void clickList(){
        binding.include.recyclerListaTarefas.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        binding.include.recyclerListaTarefas,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                itemClick(position);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                longItemClick(position);
                            }

                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            }
                        }
                )
        );
    }

    private void longItemClick(int position){
        selectTask = tarefaList.get(position);
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setTitle("Confirmar exclusão? ");
        alert.setMessage("Deseja excluir a tarefa: " + selectTask.getTarefa() + " ?");
        alert.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                TarefaDAO dao = new TarefaDAO(getApplicationContext());
                if (dao.deletar(selectTask)){
                    toasMessage("Tarefa deletada");
                    tarefaList.remove(selectTask);
                    settingsRecyclerView();
                } else {
                    toasMessage("Erro ao deletar tarefa");
                }
            }
        });
        alert.setNegativeButton("Não", null);
        alert.create();
        alert.show();
    }

    private void itemClick(int position){
        Tarefa tarefa = tarefaList.get(position);
        Intent intent = new Intent(getApplicationContext(), AdicionarTarefaActivity.class);
        intent.putExtra("obj", tarefa);
        startActivity(intent);
    }

    private void toasMessage(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
}