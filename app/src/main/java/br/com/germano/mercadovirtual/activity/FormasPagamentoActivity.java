package br.com.germano.mercadovirtual.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.germano.mercadovirtual.MercadoVirtual;
import br.com.germano.mercadovirtual.R;
import br.com.germano.mercadovirtual.adapter.PagamentoAdapter;
import br.com.germano.mercadovirtual.adapter.ProdutosAdapter;
import br.com.germano.mercadovirtual.model.Pagamento;
import br.com.germano.mercadovirtual.model.Produto;

public class FormasPagamentoActivity extends BaseActivity {

    List<Pagamento> pagamentoList;
    MercadoVirtual app;
    PagamentoAdapter mAdapter;
    RecyclerView recyclerView;
    Button btnRealizarPagamento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forma_pagamento);

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

        app = (MercadoVirtual) getApplication();
        pagamentoList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        btnRealizarPagamento = (Button) findViewById(R.id.btn_realizar_pagamento);

        populateOptionsPayment();

        mAdapter = new PagamentoAdapter(pagamentoList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        btnRealizarPagamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedPayment(pagamentoList);
                Toast.makeText(getApplicationContext(), "Pagamento realizado com sucesso!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(FormasPagamentoActivity.this, MainActivity.class));
                finish();
            }
        });
    }


    private void selectedPayment(List<Pagamento> list){
        for(int i = 0 ; i < list.size(); i++){
            if(list.get(i).isSelected()){
                app.setNumeroParcela(list.get(i).getQtdParcela());
                app.setParcelaValor(list.get(i).getParcelamento());
                app.setParcelaJuros(list.get(i).getTotal());
            }
        }
    }


    //populate List with Pagamento object
    private void populateOptionsPayment() {
        Double total = app.getTotalPreco();

        Pagamento pagamento = new Pagamento(0.0, total);
        pagamentoList.add(pagamento);

        total = app.getTotalPreco() * (1 + 0.01 * 1);
        pagamento = new Pagamento(total/2, total);
        pagamentoList.add(pagamento);

        total = app.getTotalPreco() * (1 + 0.02 * 1);
        pagamento = new Pagamento(total/3, total);
        pagamentoList.add(pagamento);

        total = app.getTotalPreco() * (1 + 0.03 * 1);
        pagamento = new Pagamento(total/4, total);
        pagamentoList.add(pagamento);

        total = app.getTotalPreco() * (1 + 0.04 * 1);
        pagamento = new Pagamento(total/5, total);
        pagamentoList.add(pagamento);
    }
}
