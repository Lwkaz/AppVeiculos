package com.company.lucas.appveiculos;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static String url = "http://192.168.1.5:83";

    public static void exibirMensagem(final Context context, final Veiculo veiculo) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Deletar veículo");
        builder.setCancelable(false);
        builder.setMessage("Deseja deletar veículo");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                NetworkUtils.deletarVeiculo(Utils.url + "/api/veiculo/deletar/" + veiculo.getIdentificador(), context);
            }
        });
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
//                Objects.requireNonNull(getActivity()).setResult(1);
//                getActivity().finish();
            }
        });

        AlertDialog alerta = builder.create();
        try {
            alerta.show();
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }



}
