package br.com.germano.mercadovirtual.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import br.com.germano.mercadovirtual.R;
import br.com.germano.mercadovirtual.model.Pagamento;
import br.com.germano.mercadovirtual.model.Produto;

/**
 * Created by germano on 26/10/16.
 */

public class PagamentoAdapter extends RecyclerView.Adapter<PagamentoAdapter.ViewHolder> {



    private List<Pagamento> pagamentoList;
    private Context context;
    private DecimalFormat df = new DecimalFormat("#.00");
    private RadioButton lastChecked = null;
    private int lastCheckedPos = 0;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        TextView parcelamento, total;
        RadioButton radio;


        public ViewHolder(View v) {
            super(v);
            parcelamento = (TextView) v.findViewById(R.id.parcelamento);
            total = (TextView) v.findViewById(R.id.total);
            radio = (RadioButton) v.findViewById(R.id.radio);
        }
    }

    public PagamentoAdapter(List<Pagamento> pagamentoList){
        this.pagamentoList = pagamentoList;
    }

    @Override
    public PagamentoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_pagamento, parent, false);
        // set the view's size, margins, paddings and layout parameters
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final PagamentoAdapter.ViewHolder holder, final int position) {
        final Pagamento pagamento = pagamentoList.get(position);
        if(position == 0){
            holder.parcelamento.setText(parcelamentoText(position));
            holder.radio.setChecked(true);
            pagamento.setSelected(true);
            lastChecked = holder.radio;
        } else {
            holder.parcelamento.setText(parcelamentoText(position) +
                    df.format(pagamento.getParcelamento()));
        }


        holder.total.setText("Total: R$" + df.format(pagamento.getTotal()));

        holder.radio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lastChecked.setChecked(false);
                pagamentoList.get(lastCheckedPos).setSelected(false);
                holder.radio.setChecked(true);
                pagamento.setSelected(true);
                pagamento.setQtdParcela(position+1);
                lastChecked = holder.radio;
                lastCheckedPos = position;
            }
        });
    }

    @Override
    public int getItemCount() {
        return pagamentoList.size();
    }

    public String parcelamentoText(int position){
        String parcelamento = null;

        switch (position){
            case 0:
                parcelamento = "À vista";
                break;
            case 1:
                parcelamento = "2x de R$";
                break;
            case 2:
                parcelamento = "3x de R$";
                break;
            case 3:
                parcelamento = "4x de R$";
                break;
            case 4:
                parcelamento = "5x de R$";
                break;
        }

        return parcelamento;
    }
}