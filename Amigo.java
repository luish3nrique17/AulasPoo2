public class Amigo {

    private String nome;
    private String email;
    private String emailAmigoSorteado; // null enquanto não sorteado

    public Amigo(String nome, String email) {
        this.nome = nome;
        this.email = email;
        this.emailAmigoSorteado = null;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getEmailAmigoSorteado() { return emailAmigoSorteado; }
    public void setAmigSorteado(String emailAmigoSorteado) {
        this.emailAmigoSorteado = emailAmigoSorteado;
    }
}
