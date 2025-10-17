package br.ulbra.appcalculator;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText edNome, edLogin, edSenha;
    private PessoaDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        edNome = findViewById(R.id.edNome);
        edLogin = findViewById(R.id.edLogin);
        edSenha = findViewById(R.id.edSenha);
        dao = new PessoaDAO(this);

        findViewById(R.id.btCadastra).setOnClickListener(view -> cadastrar());
    }

    private void cadastrar() {
        String nome = edNome.getText().toString();
        String login = edLogin.getText().toString();
        String senha = edSenha.getText().toString();

        if (senha.length() < 6) {
            Toast.makeText(this, "Senha deve ter pelo menos 6 caracteres", Toast.LENGTH_SHORT).show();
            return;
        }

        Pessoa p = new Pessoa();
        p.setNome(nome);
        p.setLogin(login);
        p.setSenha(senha);
        dao.inserir(p);
        Toast.makeText(this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
        finish();
    }
}
