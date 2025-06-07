package util;

public class AvaliacaoAtendimento extends Avaliacao {
    private float notaAtendimento;

    public AvaliacaoAtendimento(float notaAtendimento) {
        super();
        this.notaAtendimento = notaAtendimento;
    }
    public float getNotaAtendimento() {
        return notaAtendimento;
    }

    @Override
    protected void comentar() {

    }
}