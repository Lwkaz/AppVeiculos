package com.company.lucas.appveiculos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CriarMarca extends AppCompatActivity {

    Button btnSalvarMarca;
    EditText etNomeMarca;
    EditText etDescricaoMarca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_marca);

        etDescricaoMarca = (EditText)findViewById(R.id.etDescricaoMarca);
        etNomeMarca = (EditText)findViewById(R.id.etNomeMarca);
        btnSalvarMarca = (Button)findViewById(R.id.btnSalvarMarca);

        btnSalvarMarca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Marca marca = new Marca();
                marca.setNome(etNomeMarca.getText().toString());
                marca.setDescricao(etDescricaoMarca.getText().toString());
                NetworkUtils.cadastrarMarca(Utils.url + "/api/marcas/store", marca, CriarMarca.this);
            }
        });
    }
}
