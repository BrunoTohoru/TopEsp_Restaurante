package controlador;

import modelo.ItensPedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class ItensPedidoDao {

    public void inserir(ItensPedido ip) throws Exception {
        String sql = "INSERT INTO itens_pedido (quantidade, valor, id_produto, id_pedido) values(?,?,?,?)";
        Connection conexao = Conexao.getConexao();
        try ( PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, ip.getQuantidade());
            ps.setDouble(2, ip.getValor());
            ps.setInt(3, ip.getProduto().getId());
            ps.setInt(4, ip.getPedido().getId());
            ps.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        }

    }

    public List<ItensPedido> buscar() throws Exception {
        List<ItensPedido> lista = new ArrayList<>();

        String sql = "select * from itens_pedido";

        Connection conexao = Conexao.getConexao();
        PreparedStatement ps = conexao.prepareStatement(sql);

        try ( java.sql.ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ProdutoDao dao = new ProdutoDao();
                ItensPedido ip = new ItensPedido();
                ip.setId(rs.getInt("id"));
                ip.setQuantidade(rs.getInt("quantidade"));
                ip.setProduto(dao.getProduto(rs.getInt("produto")));
                lista.add(ip);
            }
        }

        return lista;
    }

    public void excluir(int id) throws Exception {
        String sql = "DELETE FROM itens_pedido WHERE id = ?";
        Connection conexao = Conexao.getConexao();
        try ( PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw e;
        }
    }

    public ItensPedido getItensPedido(int id) throws Exception {
        Connection conexao = Conexao.getConexao();
        String sql = "select * from itens_pedido where id = ?";

        ItensPedido obj = null;

        try ( PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, id);

            try ( java.sql.ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ProdutoDao dao = new ProdutoDao();
                    obj = new ItensPedido();
                    obj.setId(rs.getInt("id"));
                    obj.setProduto(dao.getProduto(rs.getInt("produto")));
                    obj.setQuantidade(rs.getInt("quantidade"));
                    obj.setValor(rs.getDouble("valor"));
                }
            }
        } catch (Exception e) {
            throw e;
        }

        return obj;
    }

    /*public boolean atualizar(ItensPedido p) throws Exception {
        String sql = "update pedido"
                + "      set mesa = ?,"
                + "          status = ?"
                + "    where id = ?";

        Connection conexao = Conexao.getConexao();
        try ( PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, p.getMesa());
            ps.setBoolean(2, p.getStatus());
            ps.setInt(3, p.getId());

            return ps.executeUpdate() == 1;
        }
    }*/
}
