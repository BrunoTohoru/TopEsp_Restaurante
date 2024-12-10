/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import modelo.Pedido;

/**
 *
 * @author Aluno
 */
public class PedidoDao {

    public void inserir(Pedido u) throws Exception {
        String sql = "INSERT INTO pedido (mesa, status) values(?,?)";
        Connection conexao = Conexao.getConexao();
        try ( PreparedStatement ps = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, u.getMesa());
            ps.setInt(2, u.getStatus());
            ps.executeUpdate();
            
            
            
            
        } catch (Exception ex) {
            throw ex;
        }

    }

    public List<Pedido> buscar() throws Exception {
        List<Pedido> lista = new ArrayList<>();

        String sql = "select * from pedido";

        Connection conexao = Conexao.getConexao();
        PreparedStatement ps = conexao.prepareStatement(sql);

        try ( java.sql.ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Pedido p = new Pedido();
                p.setId(rs.getInt("id"));
                p.setMesa(rs.getInt("mesa"));
                p.setStatus(rs.getInt("status"));
                lista.add(p);
            }
        }

        return lista;
    }

    public void excluir(int id) throws Exception {
        String sql = "DELETE FROM pedido WHERE id = ?";
        Connection conexao = Conexao.getConexao();
        try ( PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw e;
        }
    }

    public Pedido getPedido(int id) throws Exception {
        Connection conexao = Conexao.getConexao();
        String sql = "select * from pedido where id = ?";

        Pedido obj = null;

        try ( PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, id);

            try ( java.sql.ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    obj = new Pedido();
                    obj.setId(rs.getInt("id"));
                    obj.setMesa(rs.getInt("mesa"));
                    obj.setStatus(rs.getInt("status"));
                }
            }
        } catch (Exception e) {
            throw e;
        }

        return obj;
    }

    public boolean atualizar(Pedido p) throws Exception {
        String sql = "update pedido"
                + "      set mesa = ?,"
                + "          status = ?"
                + "    where id = ?";

        Connection conexao = Conexao.getConexao();
        try ( PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, p.getMesa());
            ps.setInt(2, p.getStatus());
            ps.setInt(3, p.getId());

            return ps.executeUpdate() == 1;
        }
    }
}
