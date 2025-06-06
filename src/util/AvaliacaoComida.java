package util;

public class AvaliacaoComida extends Avaliacao {
    private float notaComida;

    public AvaliacaoComida(float notaComida) {
        super();
        this.notaComida = notaComida;
    }
    public float getNotaComida() {
        return notaComida;
    }

    @Override
    void comentar() {

    }
}

