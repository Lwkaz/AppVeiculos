package com.company.lucas.appveiculos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class AddActivity extends AppCompatActivity {
    EditText marca;
    EditText ano_lancamento;
    EditText descricao;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        marca = findViewById(R.id.editText);
        ano_lancamento = findViewById(R.id.editText2);
        descricao = findViewById(R.id.editText3);
        //spinner = findViewById(R.id.spinner);
    }

    public void salvarVeiculo(View view) {
        Intent replyIntent = new Intent();
        if(TextUtils.isEmpty(marca.getText()) ||
                TextUtils.isEmpty(ano_lancamento.getText()) ||
                TextUtils.isEmpty(descricao.getText())) {
            setResult(RESULT_CANCELED, replyIntent);
        } else {
            Veiculo veiculo = new Veiculo();
            veiculo.setMarca(marca.getText().toString());
            veiculo.setAno_lancamento(ano_lancamento.getText().toString());
            veiculo.setDescricao(descricao.getText().toString());
            String url = Utils.url + "/api/veiculo/store";

            NetworkUtils.criarVeiculo(url, veiculo, AddActivity.this);
        }
        finish();
    }
}
