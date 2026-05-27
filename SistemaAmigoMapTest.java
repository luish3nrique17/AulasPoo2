import java.util.List;

public class SistemaAmigoMapTest {

    public static void main(String[] args) {

        SistemaAmigoMap sistema = new SistemaAmigoMap();

        // --- Cadastro de amigos ---
        sistema.cadastraAmigo("José",   "jose@email.com");
        sistema.cadastraAmigo("Maria",  "maria@email.com");
        sistema.cadastraAmigo("Carlos", "carlos@email.com");

        // --- Configura amigos secretos ---
        try {
            sistema.configuraAmigoSecretoDe("jose@email.com",   "maria@email.com");
            sistema.configuraAmigoSecretoDe("maria@email.com",  "carlos@email.com");
            sistema.configuraAmigoSecretoDe("carlos@email.com", "jose@email.com");
        } catch (AmigoInexistenteException e) {
            System.out.println("Erro ao configurar amigo secreto: " + e.getMessage());
        }

        // --- Envia mensagens ---
        // Anônima de Maria para José
        sistema.enviarMensagemParaAlguem(
                "Oi, sou seu amigo secreto!",
                "maria@email.com",
                "jose@email.com",
                true);

        // Não anônima de Carlos para todos
        sistema.enviarMensagemParaTodos(
                "Boas festas a todos!",
                "carlos@email.com",
                false);

        // Anônima para todos
        sistema.enviarMensagemParaTodos(
                "Alguém adivinhe quem sou!",
                "jose@email.com",
                true);

        // --- Todas as mensagens ---
        System.out.println("=== Todas as Mensagens ===");
        for (Mensagem m : sistema.pesquisaTodasAsMensagens()) {
            System.out.println(m.getTextoCompletoAExibir());
        }

        // --- Apenas mensagens anônimas ---
        System.out.println("\n=== Mensagens Anônimas ===");
        List<Mensagem> anonimas = sistema.pesquisaMensagensAnonimas();
        for (Mensagem m : anonimas) {
            System.out.println(m.getTextoCompletoAExibir());
        }

        // --- Testa pesquisaAmigoSecretoDe ---
        System.out.println("\n=== Amigos Secretos ===");
        String[] emails = {"jose@email.com", "maria@email.com", "carlos@email.com"};
        for (String email : emails) {
            try {
                String secreto = sistema.pesquisaAmigoSecretoDe(email);
                System.out.println("Amigo secreto de " + email + " é: " + secreto);
            } catch (AmigoInexistenteException | AmigoNaoSorteadoException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }

        // --- Testa exceção AmigoInexistenteException ---
        System.out.println("\n=== Teste de Exceção (amigo inexistente) ===");
        try {
            sistema.pesquisaAmigoSecretoDe("inexistente@email.com");
        } catch (AmigoInexistenteException e) {
            System.out.println("AmigoInexistenteException capturada: " + e.getMessage());
        } catch (AmigoNaoSorteadoException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        // --- Testa exceção AmigoNaoSorteadoException ---
        System.out.println("\n=== Teste de Exceção (amigo não sorteado) ===");
        sistema.cadastraAmigo("Ana", "ana@email.com"); // sem amigo secreto configurado
        try {
            sistema.pesquisaAmigoSecretoDe("ana@email.com");
        } catch (AmigoInexistenteException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (AmigoNaoSorteadoException e) {
            System.out.println("AmigoNaoSorteadoException capturada: " + e.getMessage());
        }
    }
}
