import util.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner aval = new Scanner(System.in);

        Cliente armando = new Cliente(19324295, "Armando", "abc", "12345");
        armando.autenticacao();

        AvaliacaoAtendimento atendimento = new AvaliacaoAtendimento(5.0f);
        AvaliacaoComida comida = new AvaliacaoComida(4.7f);
        AvaliacaoAmbiente ambiente = new AvaliacaoAmbiente(5.0f);
        AvaliacaoLocalizacao local = new AvaliacaoLocalizacao(4.2f);

        System.out.println("Notas: ");
        System.out.println("Atendimento: " + atendimento.getNotaAtendimento());
        System.out.println("Comida: " + comida.getNotaComida());
        System.out.println("Ambiente: " + ambiente.getNotaAmbiente());
        System.out.println("Localização: " + local.getNotaLocalizacao());

        Classificacao.calcularClassificacao();

    }
}
