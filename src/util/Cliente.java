package util;

import java.util.Scanner;

public class Cliente {
    private int idcliente;
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

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public boolean login(String email, String senha) {
        if (this.email.equals(email) && this.senha.equals(senha)) {
            System.out.println("Login bem-sucedido!");
            return true;
        } else {
            System.out.println("Email ou senha incorretos.");
            return false;
        }
    }

    public void autenticacao() {
        try (Scanner scanner = new Scanner(System.in)) {
            String email;
            String senha;

            do {
                System.out.println("Digite seu email: ");
                email = scanner.nextLine();

                System.out.println("Digite sua senha: ");
                senha = scanner.nextLine();

            } while (!login(email, senha));
        }
    }

    public void avaliar(AvaliacaoAtendimento atendimento, AvaliacaoComida comida,
                        AvaliacaoAmbiente ambiente, AvaliacaoLocalizacao local) {


        System.out.println("\n--- Sua avaliação ---");
        System.out.printf("Atendimento: %.1f%n", atendimento.getNota());
        System.out.printf("Comida:      %.1f%n", comida.getNota());
        System.out.printf("Ambiente:    %.1f%n", ambiente.getNota());
        System.out.printf("Localização: %.1f%n", local.getNota());
    }
}
