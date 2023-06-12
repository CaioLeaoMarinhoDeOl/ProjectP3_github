/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import connections.ConnectionFactory;
import javax.swing.JOptionPane;
import model.Fornecedores;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.WebServiceCep;

/**
 *
 * @author caio
 */
public class FornecedoresDAO {
    
    private Connection con;
    
    
    public FornecedoresDAO(){
    
    this.con = (Connection) new ConnectionFactory().getConnection();
    
}
    
    public void cadastrarFornecedores(Fornecedores obj){
        try {
String sql = "insert into tb_fornecedores(nome, cnpj, email, telefone, celular, cep, endereco, numero, complemento, bairro, cidade, estado) "
           + "values(?,?,?,?,?,?,?,?,?,?,?,?)";

                PreparedStatement stmt = (PreparedStatement) con.prepareStatement(sql);
                stmt.setString(1, obj.getNome());
                stmt.setString(2, obj.getCnpj());
                stmt.setString(3, obj.getEmail());
                stmt.setString(4, obj.getTelefone());
                stmt.setString(5, obj.getCelular());
                stmt.setString(6, obj.getCep());
                stmt.setString(7, obj.getEndereco());
                stmt.setInt    (8, obj.getNumero());
                stmt.setString(9, obj.getComplemento());
                stmt.setString(10, obj.getBairro());
                stmt.setString(11, obj.getCidade());
                stmt.setString(12, obj.getUf());
                
            stmt.execute();
            stmt.close();
                    JOptionPane.showMessageDialog(null,"Cadastro Realizado com SUCESSO!");
        } catch (SQLException erro) {
                    JOptionPane.showMessageDialog(null,"Erro: " + erro);
        }
    }
    
    public void alterarFornecedores(Fornecedores obj){
        
        try {
String sql = "update tb_fornecedores set nome= ?, cnpj= ?, email= ?, telefone= ?, celular= ?, cep= ?, endereco= ?, numero= ?, complemento= ?, bairro= ?, cidade= ?, estado= ? where id = ? ";

                PreparedStatement stmt = (PreparedStatement) con.prepareStatement(sql);
                stmt.setString(1, obj.getNome());
                stmt.setString(2, obj.getCnpj());
                stmt.setString(3, obj.getEmail());
                stmt.setString(4, obj.getTelefone());
                stmt.setString(5, obj.getCelular());
                stmt.setString(6, obj.getCep());
                stmt.setString(7, obj.getEndereco());
                stmt.setInt   (8, obj.getNumero());
                stmt.setString(9, obj.getComplemento());
                stmt.setString(10, obj.getBairro());
                stmt.setString(11, obj.getCidade());
                stmt.setString(12, obj.getUf());
                stmt.setInt    (13, obj.getId());
                
            stmt.execute();
            stmt.close();
                    JOptionPane.showMessageDialog(null,"Cadastro Alterado com SUCESSO!");
        } catch (SQLException erro) {
                    JOptionPane.showMessageDialog(null,"Erro: " + erro);
        }
        
    }
    
    public void excluirFornecedores(Fornecedores obj){
        
        try {
String sql = "delete from tb_fornecedores where id = ?";

                PreparedStatement stmt = (PreparedStatement) con.prepareStatement(sql);
                stmt.setInt(1, obj.getId());
                
            stmt.execute();
            stmt.close();
                    JOptionPane.showMessageDialog(null,"Cadastro Excluído com SUCESSO!");
        } catch (SQLException erro) {
                    JOptionPane.showMessageDialog(null,"Erro: " + erro);
        }
        
    }
    
    public List<Fornecedores> listarFornecedores(){
        
        try {
            
            List<Fornecedores> lista = new ArrayList<>();
        String sql = "select * from tb_fornecedores";
        PreparedStatement stmt = (PreparedStatement) con.prepareStatement(sql);
        
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()){
            Fornecedores obj = new Fornecedores();
            
            obj.setId(rs.getInt("id"));
            obj.setNome(rs.getString("nome"));
            obj.setCnpj(rs.getString("cnpj"));
            obj.setEmail(rs.getString("email"));
            obj.setTelefone(rs.getString("telefone"));
            obj.setCelular(rs.getString("celular"));
            obj.setCep(rs.getString("cep"));
            obj.setEndereco(rs.getString("endereco"));
            obj.setNumero(rs.getInt("numero"));
            obj.setComplemento(rs.getString("complemento"));
            obj.setBairro(rs.getString("bairro"));
            obj.setCidade(rs.getString("cidade"));
            obj.setUf(rs.getString("estado"));
        lista.add(obj);
            
            
        }
        return lista;
        
            
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro: "+erro);
            return null;
        }
        
    }
    
    public Fornecedores buscaCep(String cep) {
       
        WebServiceCep webServiceCep = WebServiceCep.searchCep(cep);
       

        Fornecedores obj = new Fornecedores();

        if (webServiceCep.wasSuccessful()) {
            obj.setEndereco(webServiceCep.getLogradouroFull());
            obj.setCidade(webServiceCep.getCidade());
            obj.setBairro(webServiceCep.getBairro());
            obj.setUf(webServiceCep.getUf());
            return obj;
        } else {
            JOptionPane.showMessageDialog(null, "Erro numero: " + webServiceCep.getResulCode());
            JOptionPane.showMessageDialog(null, "Descrição do erro: " + webServiceCep.getResultText());
            return null;
        }
    }
    public List<Fornecedores> buscaFornecedoresPorNome(String nome){
        
        try {
            
            List<Fornecedores> lista = new ArrayList<>();
        String sql = "select * from tb_fornecedores where nome like ?";
        PreparedStatement stmt = (PreparedStatement) con.prepareStatement(sql);
        stmt.setString(1, nome);
        
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()){
            Fornecedores obj = new Fornecedores();
            
            obj.setId(rs.getInt("id"));
            obj.setNome(rs.getString("nome"));
            obj.setCnpj(rs.getString("cnpj"));
            obj.setEmail(rs.getString("email"));
            obj.setTelefone(rs.getString("telefone"));
            obj.setCelular(rs.getString("celular"));
            obj.setCep(rs.getString("cep"));
            obj.setEndereco(rs.getString("endereco"));
            obj.setNumero(rs.getInt("numero"));
            obj.setComplemento(rs.getString("complemento"));
            obj.setBairro(rs.getString("bairro"));
            obj.setCidade(rs.getString("cidade"));
            obj.setUf(rs.getString("estado"));
        lista.add(obj);
            
            
        }
        return lista;
        
            
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro: "+erro);
            return null;
        }
}
    
    public Fornecedores consultaPorNome(String nome){
        try {
            
            String sql = "select * from tb_fornecedores where nome = ?";
        PreparedStatement stmt = (PreparedStatement) con.prepareStatement(sql);
        stmt.setString(1, nome);
        
        ResultSet rs = stmt.executeQuery();
        Fornecedores obj = new Fornecedores();
        
        if (rs.next()){
            
            
            obj.setId(rs.getInt("id"));
            obj.setNome(rs.getString("nome"));
            obj.setCnpj(rs.getString("cnpj"));
            obj.setEmail(rs.getString("email"));
            obj.setTelefone(rs.getString("telefone"));
            obj.setCelular(rs.getString("celular"));
            obj.setCep(rs.getString("cep"));
            obj.setEndereco(rs.getString("endereco"));
            obj.setNumero(rs.getInt("numero"));
            obj.setComplemento(rs.getString("complemento"));
            obj.setBairro(rs.getString("bairro"));
            obj.setCidade(rs.getString("cidade"));
            obj.setUf(rs.getString("estado"));                                
        }
        return obj;
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro: "+erro);
            return null;
        }
    }
    
}
