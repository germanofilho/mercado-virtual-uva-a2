package br.com.germano.mercadovirtual.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import br.com.germano.mercadovirtual.R;
import br.com.germano.mercadovirtual.model.Produto;

/**
 * Created by germano on 24/10/16.
 */

public class ProdutosAdapter extends RecyclerView.Adapter<ProdutosAdapter.ViewHolder> {

    private List<Produto> produtoList;
    private Context context;
    private DecimalFormat df = new DecimalFormat("#.00");

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        public CheckBox mCheckBox;
        public TextView produto, preco;
        public EditText editQtd;

        public ViewHolder(View v) {
            super(v);
            mCheckBox = (CheckBox) v.findViewById(R.id.checkbox_produto);
            produto = (TextView) v.findViewById(R.id.produto_name);
            preco = (TextView) v.findViewById(R.id.produto_preco);
            editQtd = (EditText) v.findViewById(R.id.edit_qtd);
        }
    }

    public ProdutosAdapter(List<Produto> produtoList){
        this.produtoList = produtoList;

    }

    @Override
    public ProdutosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_produtos, parent, false);
        // set the view's size, margins, paddings and layout parameters
        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final ProdutosAdapter.ViewHolder holder, int position) {
        final Produto produto = produtoList.get(position);
        holder.produto.setText(produto.getNomeProduto());
        holder.preco.setText(df.format(produto.getPrecoProduto()));
        holder.mCheckBox.setOnCheckedChangeListener(null);
        holder.mCheckBox.setChecked(produto.isSelected());
        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                produto.setSelected(isChecked);
            }
        });

        //sorry about that :(
        holder.setIsRecyclable(false);

        holder.editQtd.setText(produto.getQntProduto());
        //save edittext input and save it on produto object
        holder.editQtd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if(holder.editQtd.getText() != null || "".equals(holder.editQtd.getText().toString()))
                produto.setQntProduto(holder.editQtd.getText().toString());
            }
        });

    }

    @Override
    public int getItemCount() {
        return produtoList.size();
    }

}
