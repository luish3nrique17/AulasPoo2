import java.io.*;
import java.util.Map;

/**
 * Classe responsável pela persistência de dados em arquivo.
 * Usa ObjectOutputStream e ObjectInputStream para gravar e recuperar objetos.
 */
public class GravadorDeDados {

    private String nomeArquivo;

    public GravadorDeDados(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    /**
     * Grava o mapa de produtos em arquivo binário.
     *
     * @param dados mapa a ser gravado
     * @throws IOException se ocorrer erro na gravação
     */
    public void gravarDados(Map<String, Produto> dados) throws IOException {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(dados);
        }
    }

    /**
     * Recupera o mapa de produtos do arquivo binário.
     *
     * @return mapa recuperado do arquivo
     * @throws IOException se ocorrer erro na leitura
     */
    @SuppressWarnings("unchecked")
    public Map<String, Produto> recuperarDados() throws IOException {
        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            return (Map<String, Produto>) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException("Erro ao desserializar os dados: " + e.getMessage());
        }
    }
}
