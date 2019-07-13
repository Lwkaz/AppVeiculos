package com.company.lucas.appveiculos;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NetworkUtils {
    public static void listarVeiculos(String url, final Context context, final ListaVeiculoAdapter adapter, final RecyclerView recyclerView) {
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            List<Veiculo> veiculos = new ArrayList<Veiculo>();
                            Veiculo veiculo;

                            for (int i = 0; i < response.length(); i++) {
                                veiculo = new Veiculo();
                                JSONObject jo = response.getJSONObject(i);
                                veiculo.setMarca(jo.getString("nome_marca"));
                                veiculo.setAno_lancamento(jo.getString("ano_lancamento"));
                                veiculo.setDescricao(jo.getString("descricao"));
                                veiculo.setTipo_veiculo(jo.getString("tipo_veiculo"));
                                veiculo.setIdentificador(jo.getInt("id"));
                                veiculo.setImagem(jo.getString("imagem"));
                                veiculo.setId_marca(jo.getInt("marca"));
                                veiculos.add(veiculo);
                            }

                            adapter.setVeiculos(veiculos);

                            recyclerView.setAdapter(adapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                        } catch (Exception e) {
                            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);
    }

    public static void atualizarVeiculo(String url, final Veiculo veiculo, final Context context) {
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.POST,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Toast.makeText(context, "Atualizado com sucesso", Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Erro ao atualizar", Toast.LENGTH_LONG).show();
                    }
                })
        {
//            public byte[] getBody() throws AuthFailureError {
//                //        Map<String, String> params = getParams();
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("ano_lancamento",veiculo.getAno_lancamento());
//                params.put("marca", "myname");
//                if (params != null && params.size() > 0) {
//                    return encodeParameters(params, getParamsEncoding());
//                }
//                return null;
//
//            }
            @Override
            public byte[] getBody(){
                String body =
                        "{\"ano_lancamento\":\""+ veiculo.getAno_lancamento() + "\"," +
                                "\"marca\":"+ String.valueOf(veiculo.getId_marca()) + "," +
                                "\"descricao\":\""+ veiculo.getDescricao() + "\"," +
                                "\"tipo_veiculo\":"+ String.valueOf(veiculo.getTipo_veiculo()) + "," +
                                "\"imagem\":\""+ veiculo.getImagem() + "\"," +
                                "\"nome_marca\":\""+ veiculo.getMarca() + "\"}";

                return body.getBytes();
            }
        };
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);
    }

    public static void criarVeiculo(String url, final Veiculo veiculo, final Context context) {
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.POST,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Toast.makeText(context, "Atualizado com sucesso", Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Erro ao atualizar", Toast.LENGTH_LONG).show();
                    }
                })
        {
            @Override
            public byte[] getBody(){
                String body =
                        "\"ano_lancamento\":\""+ veiculo.getAno_lancamento() +"\",\n" +
                                "\t\"marca\":"+ veiculo.getId_marca() +",\n" +
                                "\t\"descricao\":\""+ veiculo.getDescricao() +"\",\n" +
                                "\t\"tipo_veiculo\":\""+ veiculo.getTipo_veiculo() +"\",\n" +
                                "\t\"imagem\":\""+ veiculo.getImagem() +"\"";

                return body.getBytes();
            }
        };
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);
    }

    public static void deletarVeiculo(String url, final Context context) {
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.POST,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Toast.makeText(context, "Deletado com sucesso", Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Erro ao deletar", Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);
    }

    public static void cadastrarMarca(String url, final Marca marca, final Context context) {
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.POST,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Toast.makeText(context, "Marca cadastrada com sucesso", Toast.LENGTH_LONG).show();
                        ((Activity)context).finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Erro ao cadastrar marca", Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            public byte[] getBody(){
                String body =
                        "{\n" +
                                "\t\"nome\":\""+ marca.getNome() +"\",\n" +
                                "\t\"descricao\":\""+ marca.getDescricao() +"\"\n" +
                                "}";

                return body.getBytes();
            }
        };;

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);
    }
}
