package util;

import java.sql.SQLOutput;

public class Cliente {
    private int cpf;
    private String nome;
    private String email;
    private String senha;

    public Cliente(int cpf, String nome, String email, String senha) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public static void avaliar(AvaliacaoAtendimento atendimento, AvaliacaoComida comida, AvaliacaoAmbiente ambiente) {
        float nota = (atendimento.getNota() + comida.getNota() + ambiente.getNota());

        if (nota <= 1.9f) {
            System.out.println("Avaliação: Ruim");
        } else if (nota <= 3.9f) {
            System.out.println("Avaliação: Boa");
        } else {
            System.out.println("Avaliação: Excelente");
        }
    }
}
