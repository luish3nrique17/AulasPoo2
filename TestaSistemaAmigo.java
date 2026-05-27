import java.util.List;

public class TestaSistemaAmigo {

    public static void main(String[] args) {

        SistemaAmigo sistema = new SistemaAmigo();

        // a) Cadastra dois amigos
        sistema.cadastraAmigo("José", "jose@email.com");
        sistema.cadastraAmigo("Maria", "maria@email.com");

        // b) Configura os amigos secretos
        try {
            sistema.configuraAmigoSecretoDe("jose@email.com", "maria@email.com");
            sistema.configuraAmigoSecretoDe("maria@email.com", "jose@email.com");
        } catch (AmigoInexistenteException e) {
            System.out.println("Erro ao configurar amigo secreto: " + e.getMessage());
        }

        // c) Envia mensagem anônima de Maria para José
        sistema.enviarMensagemParaAlguem(
                "Olá, sou seu amigo secreto!",
                "maria@email.com",
                "jose@email.com",
                true   // anônima
        );

        // d) Envia mensagem anônima de Maria para todos
        sistema.enviarMensagemParaTodos(
                "Feliz Natal a todos!",
                "maria@email.com",
                true   // anônima
        );

        // e) Pesquisa e imprime as mensagens anônimas
        System.out.println("=== Mensagens Anônimas ===");
        List<Mensagem> anonimas = sistema.pesquisaMensagensAnonimas();
        for (Mensagem m : anonimas) {
            System.out.println(m.getTextoCompletoAExibir());
        }

        // f) Pesquisa o amigo secreto de José e verifica se é Maria
        try {
            String emailAmigoDeJose = sistema.pesquisaAmigoSecretoDe("jose@email.com");
            if (emailAmigoDeJose.equals("maria@email.com")) {
                System.out.println("Ok");
            }
        } catch (AmigoInexistenteException | AmigoNaoSorteadoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
