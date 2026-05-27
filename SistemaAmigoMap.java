import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Versão de SistemaAmigo que utiliza HashMap para armazenar os amigos,
 * usando o e-mail como chave. Isso torna as buscas O(1) em vez de O(n).
 */
public class SistemaAmigoMap {

    // Chave: e-mail do amigo | Valor: objeto Amigo
    private Map<String, Amigo> amigos;
    private List<Mensagem> mensagens;

    public SistemaAmigoMap() {
        this.amigos = new HashMap<>();
        this.mensagens = new ArrayList<>();
    }

    // -------------------------------------------------------
    // Cadastra um novo amigo (chave = e-mail)
    // -------------------------------------------------------
    public void cadastraAmigo(String nomeAmigo, String emailAmigo) {
        amigos.put(emailAmigo, new Amigo(nomeAmigo, emailAmigo));
    }

    // -------------------------------------------------------
    // Pesquisa amigo pelo e-mail — retorna null se não existir
    // -------------------------------------------------------
    public Amigo pesquisaAmigo(String emailAmigo) {
        return amigos.get(emailAmigo);
    }

    // -------------------------------------------------------
    // Envia mensagem para todos os participantes
    // -------------------------------------------------------
    public void enviarMensagemParaTodos(String texto, String emailRemetente,
                                        boolean ehAnonima) {
        mensagens.add(new MensagemParaTodos(texto, emailRemetente, ehAnonima));
    }

    // -------------------------------------------------------
    // Envia mensagem para um participante específico
    // -------------------------------------------------------
    public void enviarMensagemParaAlguem(String texto, String emailRemetente,
                                          String emailDestinatario,
                                          boolean ehAnonima) {
        mensagens.add(new MensagemParaAlguem(
                texto, emailRemetente, emailDestinatario, ehAnonima));
    }

    // -------------------------------------------------------
    // Retorna apenas as mensagens anônimas
    // -------------------------------------------------------
    public List<Mensagem> pesquisaMensagensAnonimas() {
        List<Mensagem> anonimas = new ArrayList<>();
        for (Mensagem m : mensagens) {
            if (m.ehAnonima()) {
                anonimas.add(m);
            }
        }
        return anonimas;
    }

    // -------------------------------------------------------
    // Configura o amigo secreto sorteado — busca direta no Map
    // -------------------------------------------------------
    public void configuraAmigoSecretoDe(String emailDaPessoa,
                                         String emailAmigoSorteado)
            throws AmigoInexistenteException {

        Amigo pessoa = amigos.get(emailDaPessoa); // O(1)
        if (pessoa == null) {
            throw new AmigoInexistenteException(
                    "Amigo com e-mail '" + emailDaPessoa + "' não encontrado.");
        }
        pessoa.setAmigSorteado(emailAmigoSorteado);
    }

    // -------------------------------------------------------
    // Retorna todas as mensagens enviadas
    // -------------------------------------------------------
    public List<Mensagem> pesquisaTodasAsMensagens() {
        return new ArrayList<>(mensagens);
    }

    // -------------------------------------------------------
    // Retorna o e-mail do amigo secreto de uma pessoa
    // -------------------------------------------------------
    public String pesquisaAmigoSecretoDe(String emailDaPessoa)
            throws AmigoInexistenteException, AmigoNaoSorteadoException {

        Amigo pessoa = amigos.get(emailDaPessoa); // O(1)

        if (pessoa == null) {
            throw new AmigoInexistenteException(
                    "Amigo com e-mail '" + emailDaPessoa + "' não encontrado.");
        }

        if (pessoa.getEmailAmigoSorteado() == null) {
            throw new AmigoNaoSorteadoException(
                    "O amigo secreto de '" + emailDaPessoa + "' ainda não foi sorteado.");
        }

        return pessoa.getEmailAmigoSorteado();
    }

    // -------------------------------------------------------
    // Sorteio automático usando o Map
    // -------------------------------------------------------
    public void sortear() {
        List<Amigo> disponiveis = new ArrayList<>(amigos.values());

        for (Amigo pessoa : amigos.values()) {
            disponiveis.remove(pessoa);

            int pos = (int) (Math.random() * disponiveis.size());
            Amigo sorteado = disponiveis.get(pos);

            pessoa.setAmigSorteado(sorteado.getEmail());
            disponiveis.remove(sorteado);
            disponiveis.add(pessoa);
        }
    }
}
