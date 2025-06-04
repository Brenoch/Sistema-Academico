package util;

public class Cliente {
    private char cpf;
    private String nome;
    private String email;
    private String senha;

    public Cliente(char cpf, String nome, String email, String senha) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public void avaliar(AvaliacaoAtendimento atendimento, AvaliacaoComida comida, AvaliacaoAmbiente ambiente) {

    }

}
