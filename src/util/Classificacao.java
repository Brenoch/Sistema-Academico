package util;

public class Classificacao {
    protected AvaliacaoComida avaliacaoComida;
    protected AvaliacaoAmbiente avaliacaoAmbiente;
    protected AvaliacaoAtendimento avaliacaoAtendimento;
    protected AvaliacaoLocalizacao avaliacaoLocalizacao;

    public Classificacao(float avaliacaoLocalizacao, float avaliacaoAmbiente, float avaliacaoComida, float avaliacaoAtendimento) {
        this.avaliacaoLocalizacao = new AvaliacaoLocalizacao(avaliacaoLocalizacao);
        this.avaliacaoAmbiente = new AvaliacaoAmbiente(avaliacaoAmbiente);
        this.avaliacaoComida = new AvaliacaoComida(avaliacaoComida);
        this.avaliacaoAtendimento = new AvaliacaoAtendimento(avaliacaoAtendimento);
    }

    public float calcularClassificacao() {
        return (avaliacaoLocalizacao.getNota() + avaliacaoAmbiente.getNota() +
                avaliacaoComida.getNota() + avaliacaoAtendimento.getNota()) / 4;
    }
}
