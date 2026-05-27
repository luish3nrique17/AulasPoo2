import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class SistemaDeEstoqueTest {

    private SistemaDeEstoque sistema;

    @Before
    public void setUp() {
        sistema = new SistemaDeEstoque();
    }

    // -------------------------------------------------------
    // Testa cadastro e pesquisa de produto
    // -------------------------------------------------------
    @Test
    public void testaCadastraEPesquisaProduto() {
        try {
            sistema.cadastrarProduto("P001", "Notebook", 3500.00, 10);
            Produto p = sistema.pesquisarProduto("P001");
            assertEquals("P001", p.getCodigo());
            assertEquals("Notebook", p.getNome());
            assertEquals(3500.00, p.getPreco(), 0.01);
            assertEquals(10, p.getQuantidade());
        } catch (ProdutoJaExisteException | ProdutoInexistenteException e) {
            fail("Não deve lançar exceção: " + e.getMessage());
        }
    }

    // -------------------------------------------------------
    // Testa que cadastrar produto duplicado lança exceção
    // -------------------------------------------------------
    @Test(expected = ProdutoJaExisteException.class)
    public void testaCadastraProdutoDuplicado() throws ProdutoJaExisteException {
        try {
            sistema.cadastrarProduto("P001", "Notebook", 3500.00, 10);
            sistema.cadastrarProduto("P001", "Outro", 100.00, 5); // deve lançar exceção
        } catch (ProdutoInexistenteException e) {
            fail("Exceção errada lançada.");
        }
    }

    // -------------------------------------------------------
    // Testa pesquisa de produto inexistente
    // -------------------------------------------------------
    @Test(expected = ProdutoInexistenteException.class)
    public void testaPesquisaProdutoInexistente() throws ProdutoInexistenteException {
        sistema.pesquisarProduto("INEXISTENTE");
    }

    // -------------------------------------------------------
    // Testa remoção de produto
    // -------------------------------------------------------
    @Test
    public void testaRemoverProduto() {
        try {
            sistema.cadastrarProduto("P002", "Mouse", 80.00, 50);
            assertTrue(sistema.removerProduto("P002"));

            // Após remover, pesquisar deve lançar exceção
            sistema.pesquisarProduto("P002");
            fail("Deveria ter lançado ProdutoInexistenteException");

        } catch (ProdutoInexistenteException e) {
            // esperado após remoção
        } catch (ProdutoJaExisteException e) {
            fail("Não deve lançar essa exceção: " + e.getMessage());
        }
    }

    // -------------------------------------------------------
    // Testa remoção de produto inexistente
    // -------------------------------------------------------
    @Test(expected = ProdutoInexistenteException.class)
    public void testaRemoverProdutoInexistente() throws ProdutoInexistenteException {
        sistema.removerProduto("INEXISTENTE");
    }

    // -------------------------------------------------------
    // Testa listagem de todos os produtos
    // -------------------------------------------------------
    @Test
    public void testaListarTodosProdutos() {
        try {
            sistema.cadastrarProduto("P001", "Notebook", 3500.00, 10);
            sistema.cadastrarProduto("P002", "Mouse", 80.00, 50);
            sistema.cadastrarProduto("P003", "Teclado", 150.00, 30);

            Collection<Produto> lista = sistema.listarTodosProdutos();
            assertEquals(3, lista.size());

        } catch (ProdutoJaExisteException e) {
            fail("Não deve lançar exceção: " + e.getMessage());
        }
    }

    // -------------------------------------------------------
    // Testa pesquisa de produtos com estoque baixo
    // -------------------------------------------------------
    @Test
    public void testaEstoqueBaixo() {
        try {
            sistema.cadastrarProduto("P001", "Notebook", 3500.00, 2);  // baixo
            sistema.cadastrarProduto("P002", "Mouse", 80.00, 50);       // ok
            sistema.cadastrarProduto("P003", "Teclado", 150.00, 1);    // baixo

            List<Produto> baixo = sistema.pesquisarProdutosComEstoqueBaixo(5);
            assertEquals(2, baixo.size());

        } catch (ProdutoJaExisteException e) {
            fail("Não deve lançar exceção: " + e.getMessage());
        }
    }

    // -------------------------------------------------------
    // Testa persistência: salvar e recuperar dados
    // -------------------------------------------------------
    @Test
    public void testaSalvarERecuperarDados() {
        try {
            sistema.cadastrarProduto("P001", "Notebook", 3500.00, 10);
            sistema.cadastrarProduto("P002", "Mouse", 80.00, 50);
            sistema.salvarDados();

            // Cria novo sistema e recupera os dados do arquivo
            SistemaDeEstoque sistemaRecuperado = new SistemaDeEstoque();
            sistemaRecuperado.recuperarDados();

            Produto p = sistemaRecuperado.pesquisarProduto("P001");
            assertEquals("Notebook", p.getNome());
            assertEquals(2, sistemaRecuperado.listarTodosProdutos().size());

        } catch (ProdutoJaExisteException | ProdutoInexistenteException | IOException e) {
            fail("Não deve lançar exceção: " + e.getMessage());
        }
    }
}
