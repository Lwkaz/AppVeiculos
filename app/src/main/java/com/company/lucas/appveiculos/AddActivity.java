package com.company.lucas.appveiculos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AddActivity extends AppCompatActivity {
    EditText marca;
    EditText ano_lancamento;
    EditText descricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        marca = findViewById(R.id.editText);
        ano_lancamento = findViewById(R.id.editText2);
        descricao = findViewById(R.id.editText3);
    }

    public void salvarVeiculo(View view) {
        Intent replyIntent = new Intent();
        if(TextUtils.isEmpty(marca.getText()) ||
                TextUtils.isEmpty(ano_lancamento.getText()) ||
                TextUtils.isEmpty(descricao.getText())) {
            setResult(RESULT_CANCELED, replyIntent);
        } else {
            String marca_texto = marca.getText().toString();
            String ano_lancamento_texto = ano_lancamento.getText().toString();
            String descricao_texto = descricao.getText().toString();





            replyIntent.putExtra("marca", marca_texto);
            replyIntent.putExtra("ano_lancamento", ano_lancamento_texto);
            replyIntent.putExtra("descricao", descricao_texto);
            setResult(RESULT_OK, replyIntent);
        }
        finish();
    }
}
