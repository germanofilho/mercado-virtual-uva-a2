package br.com.germano.mercadovirtual.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.germano.mercadovirtual.MercadoVirtual;
import br.com.germano.mercadovirtual.R;
import br.com.germano.mercadovirtual.adapter.CarrinhoAdapter;
import br.com.germano.mercadovirtual.model.Produto;

public class ResumoComprasActivity extends BaseActivity {

    TextView totalProdutos, totalPreco;
    Button btnVoltar;
    CarrinhoAdapter mAdapter;
    MercadoVirtual app;
    RecyclerView recyclerView;
    List<Produto> produtoList = new ArrayList<>();
    DecimalFormat df = new DecimalFormat("#.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo_compras);

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

        totalProdutos = (TextView) findViewById(R.id.total_produtos);
        totalPreco = (TextView) findViewById(R.id.total_preco);
        btnVoltar = (Button) findViewById(R.id.btn_voltar_resumo);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        //get list from Singleton
        app = (MercadoVirtual) getApplication();
        produtoList = app.getProdutoList();

        mAdapter = new CarrinhoAdapter(produtoList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        //shows result on the screen
        totalProdutos.setText("Total de Produtos: " + app.getTotalProdutos());
        totalPreco.setText("Preço Total:  R$" + df.format(app.getTotalPreco()));


        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResumoComprasActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}
