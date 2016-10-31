package br.com.germano.mercadovirtual.model;

/**
 * Created by germano on 24/10/16.
 */

public class Produto {

    private String nomeProduto, qntProduto;
    private Boolean selected = false;
    private Double precoProduto;

    public Produto(String nomeProduto, Double precoProduto){

        this.nomeProduto = nomeProduto;
        this.precoProduto = precoProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Boolean isSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public String getQntProduto() {
        return qntProduto;
    }

    public void setQntProduto(String qntProduto) {
        this.qntProduto = qntProduto;
    }

    public Double getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(Double precoProduto) {
        this.precoProduto = precoProduto;
    }
}
