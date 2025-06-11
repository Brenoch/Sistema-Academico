package util;

public abstract class Avaliacao {
    float nota;
    float Avaliacao;

    public Avaliacao(){
        this.nota = nota;
    }

    public float getNota() {
        return nota;
    }

    protected abstract void comentar();

    public void setNota(int anInt) {
    }

    public float addAvaliacao(){
        return Avaliacao;
    }

    public void setNotaComida(float notaComida) {

    }

    public float getAvaliacao(){
        return Avaliacao;
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


