package com.example.listadetarefas.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.listadetarefas.R;
import com.example.listadetarefas.model.Tarefa;
import com.example.listadetarefas.DAO.TarefaDAO;

public class AdicionarTarefaActivity extends AppCompatActivity {

    private EditText editText;
    private Tarefa selectTarefa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_tarefa);

        editText = findViewById(R.id.textTarefa);
        recoverTask();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_tarefa, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.idSalvar:
                if (isEmptyField()) break;
                saveOrEdit();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void recoverTask(){
        selectTarefa = (Tarefa) getIntent().getSerializableExtra("obj");
        if (selectTarefa != null){
            editText.setText(selectTarefa.getTarefa());
        }
    }

    private void saveOrEdit(){
        if (selectTarefa != null){
            editData();
        } else {
            saveData();
        }
    }

    private void editData(){
        TarefaDAO dao = new TarefaDAO(getApplicationContext());
        Tarefa t = new Tarefa();
        t.setId(selectTarefa.getId());
        t.setTarefa(editText.getText().toString());
        if (dao.editar(t)){
            toastMessage("Tarefa atualizada");
            finish();
        } else {
            toastMessage("Erro ao atualizar tarefa");
        }
    }

    private void saveData(){
        TarefaDAO dao = new TarefaDAO(getApplicationContext());
        Tarefa t = new Tarefa();
        t.setTarefa(editText.getText().toString());
        if (dao.salvar(t)){
            toastMessage("Salvo com sucesso");
            finish();
        } else {
            toastMessage("Erro ao salvar tarrefa");
        }
    }

    private void toastMessage(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    private boolean isEmptyField(){
        String tarefaString = editText.getText().toString();
        return tarefaString.isEmpty();
    }

}