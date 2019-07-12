package com.company.lucas.appveiculos;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton floatingActionButton;
    public static final int ADD_VEICULO_ACTIVITY_REQUEST_CODE = 1;
    public static final int EDIT_VEICULO_ACTIVITY_REQUEST_CODE = 2;
    public static final int DELETED = 3;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        final ListaVeiculoAdapter adapter = new ListaVeiculoAdapter(this);

        String url = Utils.url+ "/api/listarVeiculos";

        NetworkUtils.listarVeiculos(url, MainActivity.this, adapter, recyclerView);

//        Veiculo veiculo = new Veiculo();
//        veiculo.setMarca("Volkswagem");
//        veiculo.setAno_lancamento("2012");
//        veiculo.setDescricao("Um carro ótimo!");
//        veiculo.setTipo_veiculo("Carro");
//        veiculo.setIdentificador(1);
//        veiculo.setImagem("image");
//        List<Veiculo> veiculos = new ArrayList<Veiculo>();
//        veiculos.add(veiculo);
//        adapter.setVeiculos(veiculos);
//
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addVeiculo(v);
            }
        });
    }

    private void addVeiculo(View view) {
        Intent requestIntent = new Intent(MainActivity.this, AddActivity.class);
        startActivityForResult(requestIntent, ADD_VEICULO_ACTIVITY_REQUEST_CODE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.itens_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.criar_marca:
               Intent i = new Intent(this, CriarMarca.class);
               startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}
