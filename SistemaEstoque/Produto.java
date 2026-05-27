import java.io.Serializable;

/**
 * Representa um produto do estoque.
 * Implementa Serializable para permitir persistência em arquivo.
 */
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String codigo;
    private String nome;
    private double preco;
    private int quantidade;

    public Produto(String codigo, String nome, double preco, int quantidade) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public String getCodigo()         { return codigo; }
    public String getNome()           { return nome; }
    public double getPreco()          { return preco; }
    public int getQuantidade()        { return quantidade; }

    public void setNome(String nome)           { this.nome = nome; }
    public void setPreco(double preco)         { this.preco = preco; }
    public void setQuantidade(int quantidade)  { this.quantidade = quantidade; }

    @Override
    public String toString() {
        return "Produto{codigo='" + codigo + "', nome='" + nome +
               "', preco=R$" + String.format("%.2f", preco) +
               ", quantidade=" + quantidade + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto)) return false;
        Produto p = (Produto) o;
        return this.codigo.equals(p.codigo);
    }

    @Override
    public int hashCode() {
        return codigo.hashCode();
    }
}
