package util;

public interface ClienteInterface {
    void autenticacao();
    void avaliar(AvaliacaoAtendimento atendimento, AvaliacaoComida comida,
                 AvaliacaoAmbiente ambiente, AvaliacaoLocalizacao local);
    String getCpf();
    String getNome();
    String getEmail();
    String getSenha();
    void setId(int idcliente);
}
