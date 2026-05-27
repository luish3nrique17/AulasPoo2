public class MensagemParaAlguem extends Mensagem {

    private String emailDestinatario;

    public MensagemParaAlguem(String texto, String emailRemetente,
                               String emailDestinatario, boolean anonima) {
        super(texto, emailRemetente, anonima);
        this.emailDestinatario = emailDestinatario;
    }

    public String getEmailDestinatario() {
        return emailDestinatario;
    }

    public void setEmailDestinatario(String emailDestinatario) {
        this.emailDestinatario = emailDestinatario;
    }

    @Override
    public String getTextoCompletoAExibir() {
        if (ehAnonima()) {
            // Mensagem anônima: não exibe o remetente
            return "Mensagem para " + emailDestinatario + ". Texto: " + getTexto();
        } else {
            // Mensagem não anônima: exibe remetente e destinatário
            return "Mensagem de: " + getEmailRemetente()
                    + " para " + emailDestinatario
                    + ". Texto: " + getTexto();
        }
    }
}
