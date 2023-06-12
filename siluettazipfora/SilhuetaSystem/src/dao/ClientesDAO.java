/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import connections.ConnectionFactory;
import javax.swing.JOptionPane;
import model.Clientes;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.WebServiceCep;

/**
 *
 * @author caio
 */
public class ClientesDAO {
    
    private Connection con;
    
    
    public ClientesDAO(){
    
    this.con = (Connection) new ConnectionFactory().getConnection();
    
}
    
    public void cadastrarCliente(Clientes obj){
        try {
String sql = "insert into tb_clientes(nome, rg, cpf, email, telefone, celular, cep, endereco, numero, complemento, bairro, cidade, estado) "
           + "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";

                PreparedStatement stmt = (PreparedStatement) con.prepareStatement(sql);
                stmt.setString(1, obj.getNome());
                stmt.setString(2, obj.getRg());
                stmt.setString(3, obj.getCpf());
                stmt.setString(4, obj.getEmail());
                stmt.setString(5, obj.getTelefone());
                stmt.setString(6, obj.getCelular());
                stmt.setString(7, obj.getCep());
                stmt.setString(8, obj.getEndereco());
                stmt.setInt   (9, obj.getNumero());
                stmt.setString(10, obj.getComplemento());
                stmt.setString(11, obj.getBairro());
                stmt.setString(12, obj.getCidade());
                stmt.setString(13, obj.getUf());
                
            stmt.execute();
            stmt.close();
                    JOptionPane.showMessageDialog(null,"Cadastro Realizado com SUCESSO!");
        } catch (SQLException erro) {
                    JOptionPane.showMessageDialog(null,"Erro: " + erro);
        }
    }
    
    public void alterarCliente(Clientes obj){
        
        try {
String sql = "update tb_clientes set nome= ?, rg= ?, cpf= ?, email= ?, telefone= ?, celular= ?, cep= ?, endereco= ?, numero= ?, complemento= ?, bairro= ?, cidade= ?, estado= ? where id = ? ";

                PreparedStatement stmt = (PreparedStatement) con.prepareStatement(sql);
                stmt.setString(1, obj.getNome());
                stmt.setString(2, obj.getRg());
                stmt.setString(3, obj.getCpf());
                stmt.setString(4, obj.getEmail());
                stmt.setString(5, obj.getTelefone());
                stmt.setString(6, obj.getCelular());
                stmt.setString(7, obj.getCep());
                stmt.setString(8, obj.getEndereco());
                stmt.setInt   (9, obj.getNumero());
                stmt.setString(10, obj.getComplemento());
                stmt.setString(11, obj.getBairro());
                stmt.setString(12, obj.getCidade());
                stmt.setString(13, obj.getUf());
                stmt.setInt    (14, obj.getId());
                
            stmt.execute();
            stmt.close();
                    JOptionPane.showMessageDialog(null,"Cadastro Alterado com SUCESSO!");
        } catch (SQLException erro) {
                    JOptionPane.showMessageDialog(null,"Erro: " + erro);
        }
        
    }
    
    public void excluirCliente(Clientes obj){
        
        try {
String sql = "delete from tb_clientes where id = ?";

                PreparedStatement stmt = (PreparedStatement) con.prepareStatement(sql);
                stmt.setInt(1, obj.getId());
                
            stmt.execute();
            stmt.close();
                    JOptionPane.showMessageDialog(null,"Cadastro Excluído com SUCESSO!");
        } catch (SQLException erro) {
                    JOptionPane.showMessageDialog(null,"Erro: " + erro);
        }
        
    }
    
    public List<Clientes> listarClientes(){
        
        try {
            
            List<Clientes> lista = new ArrayList<>();
        String sql = "select * from tb_clientes";
        PreparedStatement stmt = (PreparedStatement) con.prepareStatement(sql);
        
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()){
            Clientes obj = new Clientes();
            
            obj.setId(rs.getInt("id"));
            obj.setNome(rs.getString("nome"));
            obj.setRg(rs.getString("rg"));
            obj.setCpf(rs.getString("cpf"));
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
    
    public Clientes buscaCep(String cep) {
       
        WebServiceCep webServiceCep = WebServiceCep.searchCep(cep);
       

        Clientes obj = new Clientes();

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
    public List<Clientes> buscaClientesPorNome(String nome){
        
        try {
            
            List<Clientes> lista = new ArrayList<>();
        String sql = "select * from tb_clientes where nome like ?";
        PreparedStatement stmt = (PreparedStatement) con.prepareStatement(sql);
        stmt.setString(1, nome);
        
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()){
            Clientes obj = new Clientes();
            
            obj.setId(rs.getInt("id"));
            obj.setNome(rs.getString("nome"));
            obj.setRg(rs.getString("rg"));
            obj.setCpf(rs.getString("cpf"));
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
    
    public Clientes consultaPorNome(String nome){
        try {
            
            String sql = "select * from tb_clientes where nome = ?";
        PreparedStatement stmt = (PreparedStatement) con.prepareStatement(sql);
        stmt.setString(1, nome);
        
        ResultSet rs = stmt.executeQuery();
        Clientes obj = new Clientes();
        
        if (rs.next()){
            
            
            obj.setId(rs.getInt("id"));
            obj.setNome(rs.getString("nome"));
            obj.setRg(rs.getString("rg"));
            obj.setCpf(rs.getString("cpf"));
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
    
    public Clientes consultaPorCelular(String celular){
        try {
            
            String sql = "select * from tb_clientes where celular = ?";
        PreparedStatement stmt = (PreparedStatement) con.prepareStatement(sql);
        stmt.setString(1, celular);
        
        ResultSet rs = stmt.executeQuery();
        Clientes obj = new Clientes();
        
        if (rs.next()){
            
            
            obj.setId(rs.getInt("id"));
            obj.setNome(rs.getString("nome"));
            obj.setRg(rs.getString("rg"));
            obj.setCpf(rs.getString("cpf"));
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
