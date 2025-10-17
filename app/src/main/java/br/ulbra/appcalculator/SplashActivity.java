package br.ulbra.appcalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 1000; // 5 segundos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Handler para esperar alguns segundos antes de abrir a tela principal
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Redireciona para a MainActivity
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
                finish(); // fecha a Splash para não voltar
            }
        }, SPLASH_TIME_OUT);
    }
}
