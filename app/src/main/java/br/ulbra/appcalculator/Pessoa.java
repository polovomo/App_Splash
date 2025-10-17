package br.ulbra.appcalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pessoa implements Serializable {
    private Integer id;
    private String nome;
    private String login;
    private String senha;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {this.nome = nome;}

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }





    @Override
    public String toString() {

        return nome;
    }

    public class PessoaDAO {
        private Conexao conexao;
        private SQLiteDatabase banco;
        public PessoaDAO(Context context){
            conexao = new Conexao(context);
            banco = conexao.getWritableDatabase();
        }
        //Create - C
        public long inserir(Pessoa pessoa){
            ContentValues values = new ContentValues();
            values.put("nome", pessoa.getNome());
            values.put("login", pessoa.getLogin());
            values.put("senha", pessoa.getSenha());
            return banco.insert("pessoa", null,values);
        }
        //Read - R
        public List<Pessoa> obterTodos() {
            List<Pessoa> pessoas = new ArrayList<>();
            Cursor cursor = banco.query("pessoa", new String[]{"id", "nome", "login", "senha"},
                    null, null, null, null, null);
            while (cursor.moveToNext()) {
                Pessoa p = new Pessoa();
                p.setId(cursor.getInt(0));
                p.setNome(cursor.getString(1));
                p.setLogin(cursor.getString(2));
                p.setSenha(cursor.getString(3));
                pessoas.add(p);
            }
            return pessoas;
        }


        //Update - U
        public void atualizar(Pessoa pessoa){
            ContentValues values = new ContentValues();
            values.put("nome", pessoa.getNome());
            values.put("login", pessoa.getLogin());
            values.put("senha", pessoa.getSenha());
            banco.update("pessoa", values, "id = ?",new String[]{pessoa.getId().toString()});
        }
    }
}
