package br.com.sentfast.applicationmvc;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import br.com.sentfast.applicationmvc.dao.AlunoDAO;
import br.com.sentfast.applicationmvc.domain.Aluno;

public class ListaActivity extends AppCompatActivity {
    private ListView listaAluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_activity);
        listaAluno = (ListView) findViewById(R.id.listaAlunos);
        registerForContextMenu(listaAluno);
    }

    @Override
    protected void onResume() {
        super.onResume();
        AlunoDAO alunoDAO = new AlunoDAO(this);
        List<Aluno> listaAluno = alunoDAO.listarTodos();
        ArrayAdapter<Aluno> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, listaAluno);
        this.listaAluno.setAdapter(adapter);
        alunoDAO.close();
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.listar_activity_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.editar:
                Toast.makeText(this, getString(R.string.editar_dialog_mesnagem)
                        , Toast.LENGTH_SHORT).show();
                break;
            case R.id.remover:
                criarAlertaDialog();
                break;

            default:

        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_cadastro,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.adicionarAlunoMenu:
             startActivity(new Intent(getBaseContext(), AlunoCadastro.class));
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private void criarAlertaDialog() {
        AlertDialog.Builder alertaDialog = new AlertDialog.Builder(this);
        alertaDialog.setMessage(getString(R.string.dialog_message));
        alertaDialog.setPositiveButton(getString(R.string.dialog_ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

            }
        });
        alertaDialog.setNegativeButton(getString(R.string.dialog_cancelar), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

            }
        });
        alertaDialog.create().show();
    }
}
