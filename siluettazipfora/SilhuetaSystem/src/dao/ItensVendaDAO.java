/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import connections.ConnectionFactory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.ItemVenda;
import model.Produtos;

/**
 *
 * @author caio
 */
public class ItensVendaDAO {
    
    private Connection con;
    public ItensVendaDAO(){
    this.con = (Connection) new ConnectionFactory().getConnection();
    }
    
    public void cadastrarItem(ItemVenda obj){
        try {
            String sql = "insert into tb_itensvendas(venda_id,produto_id,qnt,subtotal) "
           + "values(?,?,?,?)";

                PreparedStatement stmt = (PreparedStatement) con.prepareStatement(sql);
                stmt.setInt(1, obj.getVenda().getId());
                stmt.setString(2, obj.getProduto().getCodigo());
                stmt.setInt(3, obj.getQnt());
                stmt.setDouble(4, obj.getSubtotal());
                

                
            stmt.execute();
            stmt.close();
                    
        } catch (SQLException erro) {
                    JOptionPane.showMessageDialog(null,"Erro: " + erro);
        }
    }
    
    public List<ItemVenda> listarItensPorVenda(int venda_id){
        List<ItemVenda> lista = new ArrayList<>();
        
        try {
        String query = "select p.modelo, i.qnt, p.custo, i.subtotal from tb_itensvendas as i "
             + " inner join tb_produtos as p on(i.produto_id = p.codigo) where i.venda_id = ?";
        
        PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
        ps.setInt(1,venda_id);
        
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()){
            ItemVenda item = new ItemVenda();
            Produtos prod = new Produtos();
            
            prod.setModelo(rs.getString("p.modelo"));
            item.setQnt(rs.getInt("i.qnt"));
            prod.setCusto(rs.getDouble("p.custo"));
            item.setSubtotal(rs.getDouble("i.subtotal"));
            
            
            item.setProduto(prod);
            
            
            lista.add(item);                        
            }
        return lista;
        
            
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
    }    
}
    
}
