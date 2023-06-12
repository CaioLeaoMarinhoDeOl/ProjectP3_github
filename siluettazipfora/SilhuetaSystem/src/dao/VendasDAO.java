/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import connections.ConnectionFactory;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Vendas;
import java.sql.ResultSet;
import com.mysql.jdbc.PreparedStatement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Clientes;

/**
 *
 * @author caio
 */
public class VendasDAO {
    private Connection con;
    
    
    public VendasDAO(){
    
    this.con = (Connection) new ConnectionFactory().getConnection();
    }
    public void cadastrarVenda(Vendas obj){
        try {
            String sql = "insert into tb_vendas(cliente_nome,cliente_celular,data_venda,total_venda,observacoes) values(?,?,?,?,?)";

                PreparedStatement stmt = (PreparedStatement) con.prepareStatement(sql);
                stmt.setString(1, obj.getCliente_nome());
                stmt.setString(2, obj.getCliente_celular());
                stmt.setString(3, obj.getData_venda());
                stmt.setDouble(4, obj.getTotal_venda());
                stmt.setString(5, obj.getObs());

                
            stmt.execute();
            stmt.close();
                    
        } catch (Exception erro) {
                    JOptionPane.showMessageDialog(null,"cadastrarVenda erro: " + erro);
        }
    }
    
    public int retornaIdUltimaVenda(){
        try {
            int idvenda = 0;
            
            String sql = "select max(id) id from tb_vendas";
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                Vendas p = new Vendas();
                
                p.setId(rs.getInt("id"));
                idvenda=p.getId();
            }
            return idvenda;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<Vendas> listarVendasPorPeriodo(LocalDate datainicio, LocalDate datafim){
        
        try {
            
            List<Vendas> lista = new ArrayList<>();
        String sql = "select v.id, date_format(v.data_venda,'%d/%m/%Y') as data_formatada, v.cliente_nome,v.cliente_celular, v.total_venda, v.observacoes from tb_vendas as v "
                +" where v.data_venda BETWEEN ? AND ?";
        
        PreparedStatement stmt = (PreparedStatement) con.prepareStatement(sql);
        stmt.setString(1,datainicio.toString());
        stmt.setString(2,datafim.toString());
        
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()){
            Vendas obj = new Vendas();
            
            obj.setId(rs.getInt("v.id"));
            obj.setData_venda(rs.getString("data_formatada"));
            obj.setCliente_nome(rs.getString("cliente_nome"));
            obj.setCliente_celular(rs.getString("cliente_celular"));
            obj.setData_venda(rs.getString("data_formatada"));            
            obj.setTotal_venda(rs.getDouble("v.total_venda"));
            obj.setObs(rs.getString("v.observacoes"));
            
            
            
        lista.add(obj);
            
            
        }
        return lista;
        
            
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "listarVendasPorPeriodos: "+erro);
            return null;
    }    
}
   
    public double retornaTotalVendaPorData(LocalDate data_venda){
        try {
            double totalvenda = 0;
            
        String sql = "select sum(total_venda) as total from tb_vendas where data_venda = ?";
        PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
        ps.setString(1,data_venda.toString());
        
        ResultSet rs = ps.executeQuery();
        
        if(rs.next()){
            totalvenda = rs.getDouble("total");
        }
        return totalvenda;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
