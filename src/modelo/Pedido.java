package modelo;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private Integer id;
    private Integer mesa;
    private Integer status;
    private List<ItensPedido> itens = new ArrayList<>();
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMesa() {
        return mesa;
    }

    public void setMesa(Integer mesa) {
        this.mesa = mesa;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<ItensPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItensPedido> itens) {
        this.itens = itens;
    }
    
    public void addItemPedido(ItensPedido ip){
        this.itens.add(ip);
    }

}
