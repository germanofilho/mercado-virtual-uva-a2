package br.com.germano.mercadovirtual;

import android.app.Application;

import java.util.List;

import br.com.germano.mercadovirtual.model.Pagamento;
import br.com.germano.mercadovirtual.model.Produto;

/**
 * Created by germano on 25/10/16.
 */

public class MercadoVirtual extends Application{

    private List<Produto> produtoList;
    private List<Produto> produtoListFull;
    private Integer totalProdutos;
    private Double totalPreco;
    private Double parcelaValor;
    private Double parcelaJuros;
    private Integer numeroParcela;

    public List<Produto> getProdutoListFull() {
        return produtoListFull;
    }

    public void setProdutoListFull(List<Produto> produtoListFull) {
        this.produtoListFull = produtoListFull;
    }

    public Integer getTotalProdutos() {
        return totalProdutos;
    }

    public void setTotalProdutos(Integer totalProdutos) {
        this.totalProdutos = totalProdutos;
    }

    public List<Produto> getProdutoList() {
        return produtoList;
    }

    public void setProdutoList(List<Produto> produtoList) {
        this.produtoList = produtoList;
    }

    public Double getTotalPreco() {
        return totalPreco;
    }

    public void setTotalPreco(Double totalPreco) {
        this.totalPreco = totalPreco;
    }

    public Double getParcelaValor() {
        return parcelaValor;
    }

    public void setParcelaValor(Double parcelaValor) {
        this.parcelaValor = parcelaValor;
    }

    public Double getParcelaJuros() {
        return parcelaJuros;
    }

    public void setParcelaJuros(Double parcelaJuros) {
        this.parcelaJuros = parcelaJuros;
    }

    public Integer getNumeroParcela() {
        return numeroParcela;
    }

    public void setNumeroParcela(Integer numeroParcela) {
        this.numeroParcela = numeroParcela;
    }
}
