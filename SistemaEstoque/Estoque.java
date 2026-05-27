import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * Interface que define as funcionalidades do Sistema de Controle de Estoque.
 * <p>
 * Permite cadastrar, pesquisar e remover produtos, além de
 * realizar persistência de dados em arquivo.
 * </p>
 *
 * @author L. Henrique
 * @version 1.0
 */
public interface Estoque {

    /**
     * Cadastra um novo produto no estoque.
     *
     * @param codigo     código único que identifica o produto
     * @param nome       nome do produto
     * @param preco      preço unitário do produto
     * @param quantidade quantidade inicial em estoque
     * @return {@code true} se o cadastro foi realizado com sucesso
     * @throws ProdutoJaExisteException se já existir um produto com o mesmo código
     */
    public boolean cadastrarProduto(String codigo, String nome,
                                    double preco, int quantidade)
            throws ProdutoJaExisteException;

    /**
     * Pesquisa um produto pelo seu código.
     *
     * @param codigo código do produto a ser pesquisado
     * @return o objeto {@link Produto} encontrado
     * @throws ProdutoInexistenteException se não existir produto com o código informado
     */
    public Produto pesquisarProduto(String codigo)
            throws ProdutoInexistenteException;

    /**
     * Pesquisa todos os produtos com quantidade abaixo de um limite.
     * Útil para identificar produtos com estoque baixo.
     *
     * @param limiteMinimo quantidade mínima de referência
     * @return lista de produtos com quantidade menor que o limite
     */
    public List<Produto> pesquisarProdutosComEstoqueBaixo(int limiteMinimo);

    /**
     * Remove um produto do estoque pelo seu código.
     *
     * @param codigo código do produto a ser removido
     * @return {@code true} se a remoção foi realizada com sucesso
     * @throws ProdutoInexistenteException se não existir produto com o código informado
     */
    public boolean removerProduto(String codigo)
            throws ProdutoInexistenteException;

    /**
     * Retorna todos os produtos cadastrados no estoque.
     *
     * @return coleção com todos os produtos
     */
    public Collection<Produto> listarTodosProdutos();

    /**
     * Salva os dados do estoque em arquivo para persistência.
     *
     * @throws IOException se ocorrer erro ao gravar o arquivo
     */
    public void salvarDados() throws IOException;

    /**
     * Recupera os dados do estoque previamente salvos em arquivo.
     *
     * @throws IOException se ocorrer erro ao ler o arquivo
     */
    public void recuperarDados() throws IOException;
}
