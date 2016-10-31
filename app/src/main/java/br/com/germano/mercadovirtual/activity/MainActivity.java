package br.com.germano.mercadovirtual.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import br.com.germano.mercadovirtual.MercadoVirtual;
import br.com.germano.mercadovirtual.R;

public class MainActivity extends AppCompatActivity {
    public Button escolherProdutos, resumoCompras, formasPagamento, relatorioPagamento, resetarDados;
    ImageView imgInfo;
    TextView title;
    MercadoVirtual app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        app = (MercadoVirtual) getApplication();

        escolherProdutos = (Button) findViewById(R.id.btn_escolher_produtos);
        resumoCompras = (Button) findViewById(R.id.btn_resumo_das_compras);
        formasPagamento = (Button) findViewById(R.id.btn_formas_pagamento);
        relatorioPagamento = (Button) findViewById(R.id.btn_relatorio_pagamento);
        resetarDados = (Button) findViewById(R.id.btn_resetar_dados);
        title = (TextView) findViewById(R.id.txt_title);
        imgInfo = (ImageView) findViewById(R.id.img_info);

        //ToolBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        title.setText("Mercado Virtual");

        imgInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogOk("Aluno: Germano Oliveira", "Este app foi desenvolvido com fins acadêmicos para Universidade Veiga de Almeida, referente ao Trabalho da A2 da matéria Tópicos Especiais para Dispositivos Móveis ministrada pelo professor Ricardo Quintão.");
            }
        });

        escolherProdutos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, EscolherProdutosActivity.class));
                finish();
            }
        });

        resumoCompras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(app.getProdutoList() != null){
                    startActivity(new Intent(MainActivity.this, ResumoComprasActivity.class));
                    finish();

                } else {
                    alertDialogOk("Atenção", "Não há produtos no carrinho, acesse Escolher Produtos para adicionar produtos.");
                }

            }
        });

        formasPagamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(app.getTotalPreco() != null){
                    startActivity(new Intent(MainActivity.this, FormasPagamentoActivity.class));
                    finish();
                } else {
                    alertDialogOk("Atenção", "Não há produtos no carrinho, acesse Escolher Produtos para adicionar produtos.");
                }
            }
        });


        relatorioPagamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(app.getTotalPreco() != null){
                    if(app.getParcelaJuros() != null){
                        startActivity(new Intent(MainActivity.this, RelatorioPagamentoActivity.class));
                        finish();
                    } else {
                        alertDialogOk("Atenção", "Ainda não foi definido uma forma de pagamento, acesse Forma de Pagamento.");
                    }
                } else {
                    alertDialogOk("Atenção", "Não há produtos no carrinho, acesse Escolher Produtos para adicionar produtos.");
                }
            }
        });


        resetarDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertResetPopup();
            }
        });

    }

    @Override
    public void onBackPressed() {
        alertDialogPopup("Mercado Virtual", "Deseja sair da aplicação?");
    }

    //Show Dialog emptyList
    private void alertDialogOk(String title, String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(msg);

        builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    //Show Dialog leave application
    private void alertDialogPopup(String title, String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(msg);

        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    //Show Dialog reset data
    private void alertResetPopup(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Atenção")
                .setMessage("Deseja apagar os dados da compra atual?");

        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                app.setParcelaJuros(null);
                app.setTotalProdutos(null);
                app.setNumeroParcela(null);
                app.setParcelaValor(null);
                app.setProdutoList(null);
                app.setTotalPreco(null);
                app.setProdutoListFull(null);
                Toast.makeText(getApplicationContext(), "Dados apagados com sucesso!", Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }





}
