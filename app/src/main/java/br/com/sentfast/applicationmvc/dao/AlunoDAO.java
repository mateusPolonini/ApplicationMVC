package br.com.sentfast.applicationmvc.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.sentfast.applicationmvc.domain.Aluno;



/**
 * Created by mateus on 12/01/2017.
 */

public class AlunoDAO extends SQLiteOpenHelper {
    public static final String NOME_BANCO = "AlunoDatabase";
    public static final int VERSAO = 1;
    public static final String NOME_TABELA = "aluno";
    public static final String _ID = "_id";
    public static final String NOME = "nome";
    public static final String EMAIL = "email";
    public static final String IDADE = "idade";
    public AlunoDAO(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+NOME_TABELA + "(" +
                _ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                NOME+" TEXT," +
                EMAIL+" TEXT," +
                IDADE+" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void adicionar(Aluno aluno){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NOME, aluno.getNome());
        values.put(EMAIL, aluno.getEmail());
        values.put(IDADE, aluno.getIdade());
        db.insert(NOME_TABELA, null, values);
    }
    public List<Aluno> listarTodos(){
        List<Aluno> listaAlunos = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+NOME_TABELA, null);
        if(cursor.moveToFirst()){
            do{
                Aluno aluno = new Aluno();
                aluno.setId(cursor.getInt(0));
                aluno.setNome(cursor.getString(1));
                aluno.setEmail(cursor.getString(2));
                aluno.setIdade(cursor.getString(3));
                listaAlunos.add(aluno);
            }while(cursor.moveToNext());
        }

        return listaAlunos;
    }

    public void removerAluno(String id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(NOME_TABELA,"id = ?",new String[]{id});

    }
}
