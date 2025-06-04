package util;

public class AvaliacaoAmbiente extends Avaliacao {
    private float notaAmbiente;

    public AvaliacaoAmbiente(float notaAmbiente) {
        super();
        this.notaAmbiente = notaAmbiente;
    }
    public float getNotaAmbiente() {
        return notaAmbiente;
    }

    @Override
    void comentar() {

    }
}