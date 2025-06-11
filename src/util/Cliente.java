package util;

import java.util.Scanner; // Mantido caso você use o método autenticacao()

public class Cliente {
    private int idcliente;
    private String cpf;
    private String nome;
    private String email;
    private String senha;

    // Construtor completo para criar um cliente (ID 0 para novos clientes)
    public Cliente(int idcliente, String cpf, String nome, String email, String senha) {
        this.idcliente = idcliente;
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    // Construtor para casos onde o ID é gerado pelo banco de dados (o mais comum para INSERT)
    public Cliente(String cpf, String nome, String email, String senha) {
        this(0, cpf, nome, email, senha); // Chama o construtor completo com ID 0
    }

    // --- Getters ---
    public int getIdcliente() {
        return idcliente;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    // --- Setters (Adicionados) ---
    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setNome(String nome) { // <-- ADICIONADO
        this.nome = nome;
    }

    public void setEmail(String email) { // <-- ADICIONADO
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    // --- Métodos de autenticação (mantidos do seu original) ---
    public boolean login(String email, String senha) {
        if (this.email.equals(email) && this.senha.equals(senha)) {
            System.out.println("Login bem-sucedido!");
            return true;
        } else {
            System.out.println("Email ou senha incorretos.");
            return false;
        }
    }

    public boolean login(String email, boolean isEmailOnly) {
        // Assume que a senha é a senha interna do objeto para esta sobrecarga
        return login(email, this.senha);
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
        } // O scanner é fechado automaticamente pelo try-with-resources
    }

    // Opcional: toString() para facilitar a depuração
    @Override
    public String toString() {
        return "Cliente{" +
                "idcliente=" + idcliente +
                ", cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='[PROTECTED]'" + // Não imprima a senha real em logs
                '}';
    }
}