package br.com.germano.mercadovirtual.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import br.com.germano.mercadovirtual.R;
import br.com.germano.mercadovirtual.model.Produto;

/**
 * Created by germano on 25/10/16.
 */

public class CarrinhoAdapter extends RecyclerView.Adapter<CarrinhoAdapter.ViewHolder> {

    private List<Produto> produtoList;
    private Context context;
    private DecimalFormat df = new DecimalFormat("#.00");

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        TextView produto, valor, qtd;

        public ViewHolder(View v) {
            super(v);
            produto = (TextView) v.findViewById(R.id.produto_name);
            valor = (TextView) v.findViewById(R.id.produto_valor);
            qtd = (TextView) v.findViewById(R.id.qtd);
        }
    }

    public CarrinhoAdapter(List<Produto> produtoList){
        this.produtoList = produtoList;
    }

    @Override
    public CarrinhoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_carrinho, parent, false);
        // set the view's size, margins, paddings and layout parameters
        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final CarrinhoAdapter.ViewHolder holder, int position) {
        final Produto produto = produtoList.get(position);
        Double valorTotalProduto = Double.valueOf(produto.getQntProduto())*produto.getPrecoProduto();

        holder.produto.setText(produto.getNomeProduto());
        holder.qtd.setText("Quantidade: " + produto.getQntProduto());
        holder.valor.setText("Valor: R$" + df.format(valorTotalProduto));

    }

    @Override
    public int getItemCount() {
        return produtoList.size();
    }
}
