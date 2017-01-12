package br.com.sentfast.applicationmvc;

import android.app.Activity;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import br.com.sentfast.applicationmvc.dao.AlunoDAO;
import br.com.sentfast.applicationmvc.domain.Aluno;

import static android.content.DialogInterface.*;

/**
 * Created by mateus on 12/01/2017.
 */
public class AlunoCadastro extends Activity {
    private EditText nomeEditText;
    private EditText emailEditText;
    private EditText idadeEditText;
    private Button cadastroButton;
    private List<Aluno> listaAlunos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_activity);
        cadastroButton = (Button) findViewById(R.id.cadastroButton);
        this.referenciarElementosTela();

        final AlunoDAO alunoDAO = new AlunoDAO(AlunoCadastro.this);
        cadastroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Aluno aluno = new Aluno();
                aluno.setNome(nomeEditText.getText().toString());
                aluno.setEmail(emailEditText.getText().toString());
                aluno.setIdade(idadeEditText.getText().toString());
                alunoDAO.adicionar(aluno);
                alunoDAO.close();
                finish();


            }
        });

    }
    public void referenciarElementosTela(){
        nomeEditText = (EditText) findViewById(R.id.nomeEditText);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        idadeEditText = (EditText) findViewById(R.id.idadeEditText);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
