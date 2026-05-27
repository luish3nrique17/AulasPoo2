import java.io.IOException;
import java.util.*;

/**
 * Implementação da interface Estoque.
 * Usa um HashMap com o código do produto como chave para buscas rápidas O(1).
 */
public class SistemaDeEstoque implements Estoque {

    private Map<String, Produto> produtos;
    private GravadorDeDados gravador;

    public SistemaDeEstoque() {
        this.produtos = new HashMap<>();
        this.gravador = new GravadorDeDados("estoque.dat");
    }

    // -------------------------------------------------------
    // Cadastra produto — lança exceção se código já existir
    // -------------------------------------------------------
    @Override
    public boolean cadastrarProduto(String codigo, String nome,
                                    double preco, int quantidade)
            throws ProdutoJaExisteException {

        if (this.produtos.containsKey(codigo)) {
            throw new ProdutoJaExisteException(
                    "Já existe produto com o código: " + codigo);
        }
        this.produtos.put(codigo, new Produto(codigo, nome, preco, quantidade));
        return true;
    }

    // -------------------------------------------------------
    // Pesquisa produto pelo código — busca direta no Map O(1)
    // -------------------------------------------------------
    @Override
    public Produto pesquisarProduto(String codigo)
            throws ProdutoInexistenteException {

        Produto p = this.produtos.get(codigo);
        if (p == null) {
            throw new ProdutoInexistenteException(
                    "Produto com código '" + codigo + "' não encontrado.");
        }
        return p;
    }

    // -------------------------------------------------------
    // Pesquisa produtos com estoque abaixo do limite
    // -------------------------------------------------------
    @Override
    public List<Produto> pesquisarProdutosComEstoqueBaixo(int limiteMinimo) {
        List<Produto> resultado = new ArrayList<>();
        for (Produto p : this.produtos.values()) {
            if (p.getQuantidade() < limiteMinimo) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    // -------------------------------------------------------
    // Remove produto pelo código — lança exceção se não existir
    // -------------------------------------------------------
    @Override
    public boolean removerProduto(String codigo)
            throws ProdutoInexistenteException {

        if (!this.produtos.containsKey(codigo)) {
            throw new ProdutoInexistenteException(
                    "Produto com código '" + codigo + "' não encontrado.");
        }
        this.produtos.remove(codigo);
        return true;
    }

    // -------------------------------------------------------
    // Retorna todos os produtos
    // -------------------------------------------------------
    @Override
    public Collection<Produto> listarTodosProdutos() {
        return new ArrayList<>(this.produtos.values());
    }

    // -------------------------------------------------------
    // Persistência — salva dados em arquivo
    // -------------------------------------------------------
    @Override
    public void salvarDados() throws IOException {
        gravador.gravarDados(this.produtos);
    }

    // -------------------------------------------------------
    // Persistência — recupera dados do arquivo
    // -------------------------------------------------------
    @Override
    public void recuperarDados() throws IOException {
        this.produtos = gravador.recuperarDados();
    }
}
