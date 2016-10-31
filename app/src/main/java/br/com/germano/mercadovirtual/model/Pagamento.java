package br.com.germano.mercadovirtual.model;

/**
 * Created by germano on 26/10/16.
 */

public class Pagamento {
    private Double parcelamento;
    private Double total;
    private int qtdParcela;
    private boolean isSelected;

    public Pagamento(Double parcelamento, Double total) {
        this.parcelamento = parcelamento;
        this.total = total;
    }


    public int getQtdParcela() {
        return qtdParcela;
    }

    public void setQtdParcela(int qtdParcela) {
        this.qtdParcela = qtdParcela;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public Double getParcelamento() {
        return parcelamento;
    }

    public void setParcelamento(Double parcelamento) {
        this.parcelamento = parcelamento;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}





