package com.company.lucas.appveiculos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    EditText etMarca;
    EditText etData;
    EditText etDescricao;
    Button btnSalvar;
    Veiculo veiculo;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Bundle b = getIntent().getExtras();

        veiculo = (Veiculo) b.getSerializable("veiculo");

        etMarca = findViewById(R.id.etMarca);
        etData = findViewById(R.id.etData);
        etDescricao = findViewById(R.id.etDescricao);
        etMarca.setText(veiculo.getMarca());
        etData.setText(veiculo.getAno_lancamento());
        etDescricao.setText(veiculo.getDescricao());

        url =  Utils.url+ "/api/veiculo/update/" + String.valueOf(veiculo.getIdentificador());

        btnSalvar = findViewById(R.id.btnEdit);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                veiculo.setMarca(etMarca.getText().toString());
                veiculo.setAno_lancamento(etData.getText().toString());
                veiculo.setDescricao(etDescricao.getText().toString());

                NetworkUtils.atualizarVeiculo(url, veiculo, EditActivity.this);
            }
        });
    }
}
