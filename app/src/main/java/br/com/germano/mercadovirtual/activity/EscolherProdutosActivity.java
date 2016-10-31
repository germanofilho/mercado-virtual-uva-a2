package br.com.germano.mercadovirtual.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.germano.mercadovirtual.MercadoVirtual;
import br.com.germano.mercadovirtual.R;
import br.com.germano.mercadovirtual.adapter.ProdutosAdapter;
import br.com.germano.mercadovirtual.model.Produto;

public class EscolherProdutosActivity extends BaseActivity {

    private List<Produto> produtoList;
    private RecyclerView recyclerView;
    private ProdutosAdapter mAdapter;
    private Button btnRegistrarCompra;
    private String notValidItems;
    public static String TAG = "EscolherProdutos";
    private boolean isPlural;
    MercadoVirtual app;
    private boolean selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolher_produtos);

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

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        btnRegistrarCompra = (Button) findViewById(R.id.btn_registrar_compra);
        app = (MercadoVirtual) getApplication();


        if(app.getProdutoListFull() == null){
            populateList();
        } else {
            produtoList = app.getProdutoListFull();
        }


        mAdapter = new ProdutosAdapter(produtoList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        btnRegistrarCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isItemsSelectedValue(produtoList)){

                    //Saving data on singleton
                    app.setProdutoList(verifyItensSelected(produtoList));
                    app.setTotalProdutos(app.getProdutoList().size());
                    app.setTotalPreco(precoTotal(app.getProdutoList()));
                    app.setProdutoListFull(produtoList);
                    Toast.makeText(getApplicationContext(),"Produtos salvos no carrinho com sucesso!", Toast.LENGTH_LONG).show();
                    Log.i(TAG, "Lista de produtos salva com sucesso!");
                    startActivity(new Intent(EscolherProdutosActivity.this, MainActivity.class));
                    finish();

                } else {
                    alertInvalidItem();
                }
            }
        });
    }

    //Verify if have some item with quantity empty
    private boolean isItemsSelectedValue(List<Produto> list){
        boolean isItemValid = true;
        notValidItems = "";
        int count = 0;
        for(int i = 0; i < list.size(); i++){
            Produto produto = list.get(i);
            if(produto.isSelected()){
                if(produto.getQntProduto() == null || "".equals(produto.getQntProduto())){
                    selectedItem = true;
                    count++;
                    //verify if have only one item or more
                    if(count == 1){
                        isPlural = false;
                    } else {
                        isPlural = true;
                    }
                    notValidItems += produto.getNomeProduto() + ", ";
                    isItemValid = false;

                }
            } else if(produto.getQntProduto() != null && !"".equals(produto.getQntProduto())){
                selectedItem = false;
                count++;
                if(count == 1){
                    isPlural = false;
                } else {
                    isPlural = true;
                }
                notValidItems += produto.getNomeProduto() + ", ";
                isItemValid = false;
            }
        }
        return  isItemValid;
    }

    //Show Dialog on screen
    private void alertInvalidItem(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Atenção");
                if(selectedItem){
                    if(isPlural){
                        builder.setMessage("Os produtos " + notValidItems + "estão sem a quantidade preenchida");
                    } else {
                        builder.setMessage("O produto " + notValidItems + "está sem a quantidade preenchida");
                    }

                } else {
                    if(isPlural){
                        builder.setMessage("Os produtos " + notValidItems + "não estão marcados");
                    } else {
                        builder.setMessage("O produto " + notValidItems + "não está marcado");
                    }
                }

        builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    //populate List with Produto object
    private void populateList(){
        produtoList = new ArrayList<>();

        Produto produto = new Produto("Arroz", 5.00);
        produtoList.add(produto);

        produto = new Produto("Feijão", 4.35);
        produtoList.add(produto);

        produto = new Produto("Macarrão", 2.95);
        produtoList.add(produto);

        produto = new Produto("Pão Francês", 3.90);
        produtoList.add(produto);

        produto = new Produto("Queijo Prato", 14.45);
        produtoList.add(produto);

        produto = new Produto("Mussarela", 13.95);
        produtoList.add(produto);

        produto = new Produto("Cebola", 2.99);
        produtoList.add(produto);

        produto = new Produto("Tomate", 7.15);
        produtoList.add(produto);

        produto = new Produto("Couve-Flor", 6.00);
        produtoList.add(produto);

        produto = new Produto("Brocolis", 5.85);
        produtoList.add(produto);

        produto = new Produto("Melancia", 15.00);
        produtoList.add(produto);

        produto = new Produto("Banana", 3.70);
        produtoList.add(produto);

        produto = new Produto("Morango", 5.50);
        produtoList.add(produto);

        produto = new Produto("Pêra", 4.50);
        produtoList.add(produto);

        produto = new Produto("Uva", 7.80);
        produtoList.add(produto);

        produto = new Produto("Kiwi", 6.99);
        produtoList.add(produto);

        produto = new Produto("Mamão", 4.55);
        produtoList.add(produto);

        produto = new Produto("Melão", 6.99);
        produtoList.add(produto);

        produto = new Produto("Açaí", 8.50);
        produtoList.add(produto);

        produto = new Produto("Abacate", 9.50);
        produtoList.add(produto);

        Log.i(TAG, "List has been filled!");
    }

    //put only products seleted in the list
    private List<Produto> verifyItensSelected(List<Produto> produtoList){
        List<Produto> produtos = new ArrayList<>();
        for(int i = 0; i < produtoList.size(); i++){
            if(produtoList.get(i).isSelected()){
               produtos.add(produtoList.get(i));
            }
        }
        return produtos;
    }

    //sum the price of all itens in the list
    public Double precoTotal(List<Produto> list){
        Double precoTotal = 0.0;

        for(int i = 0; i < list.size(); i++){
            Integer qntProduto = Integer.valueOf(list.get(i).getQntProduto());
            Double precoProduto = list.get(i).getPrecoProduto();
            precoTotal += precoProduto*qntProduto;
        }
        Log.i("ResumoCompras", "Preço Total:" + precoTotal);

        return precoTotal;
    }



}
