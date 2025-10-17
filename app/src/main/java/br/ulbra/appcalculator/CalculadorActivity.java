package br.ulbra.appcalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class CalculadorActivity extends AppCompatActivity {

    private EditText edSalario, edFilhos;
    private RadioGroup rgSexo;
    private TextView tvResultado;
    private Button btCalcular,btVoltar;
    private Pessoa usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculadora_activity);

        btVoltar = findViewById(R.id.btVoltar);
        edSalario = findViewById(R.id.edSalario);
        edFilhos = findViewById(R.id.edFilhos);
        rgSexo = findViewById(R.id.rgSexo);
        tvResultado = findViewById(R.id.tvResultado);
        btCalcular = findViewById(R.id.btCalcular);

        usuario = (Pessoa) getIntent().getSerializableExtra("usuario");


        btCalcular.setOnClickListener(v -> calcular());

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_main);
            }
        });
    }

    private void calcular() {
        double salario = Double.parseDouble(edSalario.getText().toString());
        int filhos = Integer.parseInt(edFilhos.getText().toString());
        String sexo = ((RadioButton) findViewById(rgSexo.getCheckedRadioButtonId())).getText().toString();
        String tratamento = sexo.equalsIgnoreCase("Feminino") ? "Sra." : "Sr.";

        double inss, ir, familia;

        if (salario <= 1212) inss = salario * 0.075;
        else if (salario <= 2427.35) inss = salario * 0.09;
        else if (salario <= 3641.03) inss = salario * 0.12;
        else inss = salario * 0.14;

        if (salario <= 1903.98) ir = 0.0;
        else if (salario <= 2826.65) ir = salario * 0.075;
        else if (salario <= 3751.05) ir = salario * 0.15;
        else ir = salario * 0.225;

        familia = (salario <= 1212) ? filhos * 56.47 : 0.0;

        double liquido = salario - inss - ir + familia;

        StringBuilder resultado = new StringBuilder();
        resultado.append(tratamento).append(" ").append(usuario.getNome()).append("\n");
        resultado.append("Salário Bruto: R$ ").append(String.format("%.2f", salario)).append("\n");
        resultado.append("Desconto INSS: R$ ").append(String.format("%.2f", inss)).append("\n");
        resultado.append("Desconto IR: R$ ").append(String.format("%.2f", ir)).append("\n");
        if (familia > 0) resultado.append("Salário-Família: R$ ").append(String.format("%.2f", familia)).append("\n");
        resultado.append("Salário Líquido: R$ ").append(String.format("%.2f", liquido)).append("\n");

        tvResultado.setText(resultado.toString());
    }
}
