package com.company.lucas.appveiculos;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

public class Utils {
    public static void exibirMensagem(Context context) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Deletar veículo");
        builder.setCancelable(false);
        builder.setMessage("Deseja deletar veículo");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {

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
