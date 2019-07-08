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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Bundle b = new Bundle();

        Veiculo veiculo = (Veiculo) b.getSerializable("veiculo");

        etMarca = findViewById(R.id.etMarca);
        etData = findViewById(R.id.etData);
        etDescricao = findViewById(R.id.etDescricao);
        etMarca.setText(veiculo.getMarca());
        etData.setText(veiculo.getAno_lancamento());
        etDescricao.setText(veiculo.getDescricao());

        btnSalvar = findViewById(R.id.btnEdit);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
