import java.util.Scanner;

public class TestaSistemaAmigoGUI {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        SistemaAmigo sistema = new SistemaAmigo();  // a) Inicializa o sistema

        // b) Lê a quantidade de amigos
        System.out.print("Informe a quantidade de amigos participantes: ");
        int quantidade = Integer.parseInt(scanner.nextLine().trim());

        // c) Lê nome e e-mail de cada amigo e cadastra
        for (int i = 1; i <= quantidade; i++) {
            System.out.println("--- Amigo " + i + " ---");
            System.out.print("Nome: ");
            String nome = scanner.nextLine();

            System.out.print("E-mail: ");
            String email = scanner.nextLine();

            sistema.cadastraAmigo(nome, email);
        }

        // d) Cadastra os resultados do sorteio
        System.out.println("\n=== Cadastro do Sorteio ===");
        for (int i = 1; i <= quantidade; i++) {
            System.out.print("E-mail de quem tirou: ");
            String emailPessoa = scanner.nextLine();

            System.out.print("E-mail do amigo secreto sorteado: ");
            String emailSorteado = scanner.nextLine();

            try {
                sistema.configuraAmigoSecretoDe(emailPessoa, emailSorteado);
                System.out.println("Amigo secreto configurado com sucesso!\n");
            } catch (AmigoInexistenteException e) {
                System.out.println("Erro: " + e.getMessage() + "\n");
            }
        }

        // e) Envia uma mensagem para todos
        System.out.println("=== Enviar Mensagem para Todos ===");
        System.out.print("E-mail do remetente: ");
        String emailRemetente = scanner.nextLine();

        System.out.print("Texto da mensagem: ");
        String texto = scanner.nextLine();

        System.out.print("A mensagem é anônima? (s/n): ");
        boolean anonima = scanner.nextLine().trim().equalsIgnoreCase("s");

        sistema.enviarMensagemParaTodos(texto, emailRemetente, anonima);
        System.out.println("Mensagem enviada!");

        // Exibe a mensagem enviada
        System.out.println("\n=== Mensagem Enviada ===");
        for (Mensagem m : sistema.pesquisaTodasAsMensagens()) {
            System.out.println(m.getTextoCompletoAExibir());
        }

        scanner.close();
    }
}
