package util;

import java.sql.SQLOutput;
import java.util.Objects;

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

    public static void logar(int cpf, String nome, String email, String senha){
        boolean login;
        while (true) {
            System.out.println("Digite seu email: ");
            String email = login.nextLine();

            if (Objects.equals(email, jose.getEmail())) {
                break;
            } else {
                System.out.println("Email incorreto! Tente novamente.");
            }
        }
        while (true) {
            System.out.println("Digite sua senha: ");
            String senha = login.nextLine();

            if (Objects.equals(senha, jose.getSenha())) {
                break;
            }
            else{
                System.out.println("Senha incorreta! Tente novamente.");
            }
        }

    }

    public String getSenha(){
        return senha;
    }

    public String getEmail(){
        return email;
    }

    public static void avaliar(AvaliacaoAtendimento atendimento, AvaliacaoComida comida, AvaliacaoAmbiente ambiente, AvaliacaoLocalizacao local) {
        float nota = (atendimento.getNota() + comida.getNota() + ambiente.getNota() + local.getNota());

        System.out.println("Sua avaliação:");
        System.out.printf("Atendimento: %-10.2f%n", atendimento.getNotaAtendimento());
        System.out.printf("Comida:      %-10.2f%n", comida.getNotaComida());
        System.out.printf("Ambiente:    %-10.2f%n", ambiente.getNotaAmbiente());
        System.out.printf("Localização: %-10.2f%n", local.getNotaLocalizacao());

    }
}