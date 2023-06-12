/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author caio
 */
public class Vendas {
    private int id;
    private String cliente_nome;
    private String cliente_celular;
    private String data_venda;
    private Double total_venda;
    private String obs;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCliente_nome() {
        return cliente_nome;
    }

    public void setCliente_nome(String cliente_nome) {
        this.cliente_nome = cliente_nome;
    }
    
    public String getCliente_celular() {
        return cliente_nome;
    }

    public void setCliente_celular(String cliente_celular) {
        this.cliente_celular = cliente_celular;
    }

    public String getData_venda() {
        return data_venda;
    }

    public void setData_venda(String data_venda) {
        this.data_venda = data_venda;
    }

    public Double getTotal_venda() {
        return total_venda;
    }

    public void setTotal_venda(Double total_venda) {
        this.total_venda = total_venda;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    
}
