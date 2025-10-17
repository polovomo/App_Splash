package br.ulbra.appcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText edLogin, edPass;
    private PessoaDAO dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);// Tela de login



        edLogin = findViewById(R.id.edLogin);
        edPass = findViewById(R.id.edPass);
        dao = new PessoaDAO(this);



        findViewById(R.id.btEntrar).setOnClickListener(view -> login());
        findViewById(R.id.btCadastra).setOnClickListener(view -> {
            Intent it = new Intent(this, RegisterActivity.class);
            startActivity(it);
        });
    }


    private void login() {
        String login = edLogin.getText().toString();
        String senha = edPass.getText().toString();

        List<Pessoa> usuarios = dao.obterTodos();

        for (Pessoa u : usuarios) {
            if (u.getLogin().equals(login) && u.getSenha().equals(senha)) {
                Intent it = new Intent(this, CalculadorActivity.class);
                it.putExtra("usuario", u);
                startActivity(it);
                finish();
                return;
            }
        }

        Toast.makeText(this, "Login ou senha incorretos", Toast.LENGTH_SHORT).show();
    }
}
