package br.com.germano.mercadovirtual.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;

import br.com.germano.mercadovirtual.MercadoVirtual;
import br.com.germano.mercadovirtual.R;

public class RelatorioPagamentoActivity extends BaseActivity {
    TextView totalProdutos, totalPreco, totalParcelas, valorParcela, valorParcelado;
    DecimalFormat df = new java.text.DecimalFormat("#.00");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio_pagamento);

        //ToolBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Mercado Virtual");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        MercadoVirtual app = (MercadoVirtual) getApplication();
        totalProdutos = (TextView) findViewById(R.id.total_produtos);
        totalPreco = (TextView) findViewById(R.id.total_preco);
        totalParcelas = (TextView) findViewById(R.id.total_parcelas);
        valorParcela = (TextView) findViewById(R.id.valor_parcela);
        valorParcelado = (TextView) findViewById(R.id.valor_parcelado);

        totalProdutos.setText("Total de Produtos: " + app.getTotalProdutos());
        totalPreco.setText("Preço Total: R$" + df.format(app.getTotalPreco()));

        if(app.getNumeroParcela() == 0){
            totalParcelas.setText("Total Parcelas: À vista");
            valorParcela.setVisibility(View.GONE);
        } else {
            totalParcelas.setText("Total Parcelas: " + app.getNumeroParcela());
            valorParcela.setVisibility(View.VISIBLE);
            valorParcela.setText("Valor da Parcela: R$" + df.format(app.getParcelaValor()));
        }

        valorParcelado.setText("Total Parcelado:" + df.format(app.getParcelaJuros()));

    }
}
