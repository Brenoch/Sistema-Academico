import util.AvaliacaoAmbiente;
import util.AvaliacaoAtendimento;
import util.AvaliacaoComida;
import util.Cliente;
import util.AvaliacaoLocalizacao;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner notatst = new Scanner(System.in);
        Scanner login = new Scanner(System.in);

        Cliente jose = new Cliente(19324295, "Armando", "Armando@gmail.com", "12356");


        System.out.println("Login realizado com sucesso!");
            System.out.print("Dê uma nota para o atendimento: ");
            float notaAtendimento = notatst.nextFloat();

            System.out.print("Dê uma nota para a comida: ");
            float notaComida = notatst.nextFloat();

            System.out.print("Dê uma nota para o ambiente: ");
            float notaAmbiente = notatst.nextFloat();

            System.out.println("Dê uma nota para o local: ");
            float notaLocal = notatst.nextFloat();

            AvaliacaoAtendimento atendimento = new AvaliacaoAtendimento(notaAtendimento);
            AvaliacaoComida comida = new AvaliacaoComida(notaComida);
            AvaliacaoAmbiente ambiente = new AvaliacaoAmbiente(notaAmbiente);
            AvaliacaoLocalizacao local = new AvaliacaoLocalizacao(notaLocal);


            Cliente.avaliar(atendimento, comida, ambiente, local);
        }
    }