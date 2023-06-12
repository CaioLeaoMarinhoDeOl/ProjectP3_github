/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import connections.ConnectionFactory;
import javax.swing.JOptionPane;
import model.Produtos;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author caio
 */
public class ProdutosDAO {
    
    private Connection con;
    
    
    public ProdutosDAO(){
    
    this.con = (Connection) new ConnectionFactory().getConnection();
    
}
    
    public void cadastrarProduto(Produtos obj){
        try {
String sql = "insert into tb_produtos(codigo,referencia,custo,quantidade,fornecedor,modelo,tipo,tamanho,cor) "
           + "values(?,?,?,?,?,?,?,?,?)";

                PreparedStatement stmt = (PreparedStatement) con.prepareStatement(sql);
                
                
                stmt.setString(1, obj.getCodigo());
                stmt.setString   (2, obj.getReferencia());
                stmt.setDouble(3, obj.getCusto());
                stmt.setInt (4, obj.getQuantidade());
                stmt.setString    (5, obj.getFornecedor());
                stmt.setString(6, obj.getModelo());
                stmt.setString(7, obj.getTipo());
                stmt.setString(8, obj.getTamanho());
                stmt.setString(9, obj.getCor());
                
                
            stmt.execute();
            stmt.close();
                    JOptionPane.showMessageDialog(null,"Cadastro Realizado com SUCESSO!");
        } catch (SQLException erro) {
                    JOptionPane.showMessageDialog(null,"Erro em cadastrarProduto: " + erro);
        }
    }
    
    public void alterarProduto(Produtos obj){
        
        try {
String sql = "update tb_produtos set codigo=?,referencia=?,custo=?,quantidade=?,fornecedor=?,modelo=?,tipo=?,tamanho=?,cor=? where id=?";

                PreparedStatement stmt = (PreparedStatement) con.prepareStatement(sql);
                
                stmt.setString(1, obj.getCodigo());
                stmt.setString   (2, obj.getReferencia());
                stmt.setDouble(3, obj.getCusto());
                stmt.setInt (4, obj.getQuantidade());
                stmt.setString   (5, obj.getFornecedor());
                stmt.setString (6, obj.getModelo());
                stmt.setString(7, obj.getTipo());
                stmt.setString(8, obj.getTamanho());
                stmt.setString (9, obj.getCor());
                stmt.setInt (10, obj.getId());
                
            stmt.execute();
            stmt.close();
                    JOptionPane.showMessageDialog(null,"Cadastro Alterado com SUCESSO!");
        } catch (SQLException erro) {
                    JOptionPane.showMessageDialog(null,"Erro: " + erro);
        }
        
    }
    
    public void excluirProduto(Produtos obj){
        
        try {
String sql = "delete from tb_produtos where id = ?";

                PreparedStatement stmt = (PreparedStatement) con.prepareStatement(sql);
                stmt.setInt(1, obj.getId());
                
            stmt.execute();
            stmt.close();
                    JOptionPane.showMessageDialog(null,"Cadastro Excluído com SUCESSO!");
        } catch (SQLException erro) {
                    JOptionPane.showMessageDialog(null,"Erro: " + erro);
        }
        
    }
    
    public List<Produtos> listarProdutos(){
        
        try {
            
            List<Produtos> lista = new ArrayList<>();
        String sql = "select p.id,p.codigo,p.referencia,p.custo,p.quantidade,p.fornecedor,p.modelo,p.tipo,p.tamanho,p.cor from tb_produtos as p ";
        PreparedStatement stmt = (PreparedStatement) con.prepareStatement(sql);
        
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()){
            Produtos obj = new Produtos();
            
            
            obj.setId(rs.getInt("p.id"));
            obj.setCodigo(rs.getString("p.codigo"));
            obj.setReferencia(rs.getString("p.referencia"));
            obj.setCusto(rs.getDouble("p.custo"));
            obj.setQuantidade(rs.getInt("p.quantidade"));
            obj.setFornecedor(rs.getString("p.fornecedor"));
            obj.setModelo(rs.getString("p.modelo"));
            obj.setTipo(rs.getString("p.tipo"));
            obj.setTamanho(rs.getString("p.tamanho"));
            obj.setCor(rs.getString("p.cor"));
            
            
            
            
        lista.add(obj);
            
            
        }
        return lista;
        
            
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "listarProdutos: "+erro);
            return null;
    }    
}        
    
    public List<Produtos> buscaProdutosPorFiltro(String referencia,String modelo,String tamanho,String cor){
        
        try {
            
            List<Produtos> lista = new ArrayList<>();
        String sql = "select p.id,p.codigo,p.referencia,p.custo,p.quantidade,p.fornecedor,p.modelo,p.tipo,p.tamanho,p.cor from tb_produtos as p "
                   + "where p.referencia like ? or p.modelo like ? or p.tamanho like ? or p.cor like ?";
        PreparedStatement stmt = (PreparedStatement) con.prepareStatement(sql);
        stmt.setString(1,referencia);
        stmt.setString(2,modelo);
        stmt.setString(3,tamanho);
        stmt.setString(4,cor);
        
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()){
            Produtos obj = new Produtos();
            
            
            obj.setId(rs.getInt("id"));
            obj.setCodigo    (rs.getString("p.codigo"));
            obj.setReferencia(rs.getString("p.referencia"));
            obj.setCusto(rs.getDouble("p.custo"));
            obj.setQuantidade(rs.getInt("p.quantidade"));
            obj.setFornecedor(rs.getString("p.fornecedor"));
            obj.setModelo(rs.getString("p.modelo"));
            obj.setTipo(rs.getString("p.tipo"));
            obj.setTamanho(rs.getString("p.tamanho"));
            obj.setCor(rs.getString("p.cor"));
         
            

            
            
        lista.add(obj);
            
            
        }
        return lista;
        
            
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "buscaProdutosPorReferencia: "+erro);
            return null;
        }
        
    }   
    
    public Produtos consultaProdutosPorReferencia(String referencia){
        try {
            
            String sql = "select p.codigo,p.referencia,p.custo,p.quantidade,p.fornecedor,p.modelo,p.tipo,p.tamanho,p.cor from tb_produtos as p "
                   + "where p.referencia=?";
        PreparedStatement stmt = (PreparedStatement) con.prepareStatement(sql);
        stmt.setString(1, referencia);
        
        ResultSet rs = stmt.executeQuery();
        Produtos obj = new Produtos();
        
        if (rs.next()){
            
            
            obj.setCodigo(rs.getString("p.codigo"));
            obj.setReferencia(rs.getString("p.referencia"));
            obj.setCusto(rs.getDouble("p.custo"));
            obj.setQuantidade(rs.getInt("p.quantidade"));
            obj.setFornecedor(rs.getString("p.fornecedor"));

            obj.setModelo(rs.getString("p.modelo"));
            obj.setTipo(rs.getString("p.tipo"));
            obj.setTamanho(rs.getString("p.tamanho"));
            obj.setCor (rs.getString("p.cor"));  
            
            
        }
        return obj;
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "consultaProdutosPorReferencia: "+erro);
            return null;
        }
    }
    
    public Produtos consultaProdutosPorId(int id){
        try {
            
            String sql = "select p.id,p.codigo,p.referencia,p.custo,p.quantidade,p.fornecedor,p.modelo,p.tipo,p.tamanho,p.cor from tb_produtos as p "
                   + "where p.id=?";
        PreparedStatement stmt = (PreparedStatement) con.prepareStatement(sql);
        stmt.setInt(1, id);
        
        ResultSet rs = stmt.executeQuery();
        Produtos obj = new Produtos();
        
        
        if (rs.next()){
            
            
            obj.setCodigo(rs.getString("p.codigo"));
            obj.setReferencia(rs.getString("p.referencia"));
            obj.setCusto(rs.getDouble("p.custo"));
            obj.setQuantidade(rs.getInt("p.quantidade"));
            obj.setFornecedor(rs.getString("p.fornecedor"));           
            obj.setModelo(rs.getString("p.modelo"));
            obj.setTipo(rs.getString("p.tipo"));
            obj.setTamanho(rs.getString("p.tamanho"));
            obj.setCor (rs.getString("p.cor"));  
           
            
            
        }
        return obj;
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "consultaProdutosPorCodigo: "+erro);
            return null;
        }
    }
    
    public Produtos consultaPorId(int idb){
        try {
            
            String sql = "select p.id,p.codigo,p.referencia,p.custo,p.quantidade,p.fornecedor,p.modelo,p.tipo,p.tamanho,p.cor from tb_produtos as p "
                    + "where p.id=?";
        PreparedStatement stmt = (PreparedStatement) con.prepareStatement(sql);
        stmt.setInt(1, idb);
        
        ResultSet rs = stmt.executeQuery();
        Produtos obj = new Produtos();
        
        
        if (rs.next()){
            
            obj.setId(rs.getInt("p.id"));
            obj.setCodigo(rs.getString("p.codigo"));
            obj.setReferencia(rs.getString("p.referencia"));
            obj.setCusto(rs.getDouble("p.custo"));
            obj.setQuantidade(rs.getInt("p.quantidade"));
            obj.setFornecedor(rs.getString("p.fornecedor"));           
            obj.setModelo(rs.getString("p.modelo"));
            obj.setTipo(rs.getString("p.tipo"));
            obj.setTamanho(rs.getString("p.tamanho"));
            obj.setCor (rs.getString("p.cor"));  
           
            
            
        }
        return obj;
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "consultaProdutosPorCodigo: "+erro);
            return null;
        }
    }
    
    public void baixaEstoque(int id,int qntnova){
    try{
        String sql = "update tb_produtos set quantidade = ? where id = ?";
        PreparedStatement stmt = (PreparedStatement) con.prepareStatement(sql);
        
        stmt.setInt(1,qntnova);
        stmt.setInt(2,id);
        stmt.execute();
        stmt.close();
        
        
        }
    catch(SQLException erro){
        JOptionPane.showMessageDialog(null, "Baixa Estoque erro: " + erro);
        }
    }
    
    public int retornaEstoqueAtual(String referencia){
        try {
            
            int quantidade = 0;
            
            String sql = "SELECT quantidade from tb_produtos where referencia = ?";
            PreparedStatement stmt = (PreparedStatement) con.prepareStatement(sql);
        
        stmt.setString(1,referencia);
        
        ResultSet rs = stmt.executeQuery();
        
        if(rs.next()){

            quantidade = (rs.getInt("quantidade"));
        }
            return quantidade;
        } catch (SQLException e) {
            
            throw new RuntimeException(e);
            
        }
    }
    
}
