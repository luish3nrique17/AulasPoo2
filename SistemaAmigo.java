import java.util.ArrayList;
import java.util.List;

public class SistemaAmigo {

    private List<Mensagem> mensagens;
    private List<Amigo> amigos;

    public SistemaAmigo() {
        this.mensagens = new ArrayList<>();
        this.amigos = new ArrayList<>();
    }

    // -------------------------------------------------------
    // Cadastra um novo amigo na brincadeira
    // -------------------------------------------------------
    public void cadastraAmigo(String nomeAmigo, String emailAmigo) {
        amigos.add(new Amigo(nomeAmigo, emailAmigo));
    }

    // -------------------------------------------------------
    // Pesquisa e retorna o objeto Amigo pelo e-mail
    // -------------------------------------------------------
    public Amigo pesquisaAmigo(String emailAmigo) {
        for (Amigo a : amigos) {
            if (a.getEmail().equals(emailAmigo)) {
                return a;
            }
        }
        return null;
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
    // Questão 2a – Retorna apenas as mensagens anônimas
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
    // Questão 2b – Configura o amigo secreto sorteado
    // -------------------------------------------------------
    public void configuraAmigoSecretoDe(String emailDaPessoa,
                                         String emailAmigoSorteado)
            throws AmigoInexistenteException {

        Amigo pessoa = pesquisaAmigo(emailDaPessoa);
        if (pessoa == null) {
            throw new AmigoInexistenteException(
                    "Amigo com e-mail '" + emailDaPessoa + "' não encontrado.");
        }
        pessoa.setAmigSorteado(emailAmigoSorteado);
    }

    // -------------------------------------------------------
    // Questão 2c – Retorna todas as mensagens
    // -------------------------------------------------------
    public List<Mensagem> pesquisaTodasAsMensagens() {
        return new ArrayList<>(mensagens);
    }

    // -------------------------------------------------------
    // Questão 2d – Retorna o e-mail do amigo secreto
    // -------------------------------------------------------
    public String pesquisaAmigoSecretoDe(String emailDaPessoa)
            throws AmigoInexistenteException, AmigoNaoSorteadoException {

        Amigo pessoa = pesquisaAmigo(emailDaPessoa);

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
    // Questão 5 (OPCIONAL) – Realiza o sorteio automático
    // -------------------------------------------------------
    public void sortear() throws AmigoInexistenteException {
        // Lista auxiliar com os amigos ainda disponíveis para serem sorteados
        List<Amigo> disponiveis = new ArrayList<>(amigos);

        for (Amigo pessoa : amigos) {
            // Remove a própria pessoa para não tirar a si mesma
            disponiveis.remove(pessoa);

            // Sorteia aleatoriamente entre os disponíveis
            int pos = (int) (Math.random() * disponiveis.size());
            Amigo sorteado = disponiveis.get(pos);

            pessoa.setAmigSorteado(sorteado.getEmail());

            // Remove o sorteado da lista para não ser sorteado novamente
            disponiveis.remove(sorteado);

            // Reinsere a própria pessoa para que ela possa ser sorteada por outros
            disponiveis.add(pessoa);
        }
    }
}
