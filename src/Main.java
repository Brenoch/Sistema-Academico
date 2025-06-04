 import util.AvaliacaoAmbiente;
import util.AvaliacaoAtendimento;
import util.AvaliacaoComida;
import util.Cliente;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner notatst = new Scanner(System.in);
        System.out.print("Digite a nota de atendimento: ");
        float notaAtendimento = notatst.nextFloat();

        System.out.print("Digite a nota de comida: ");
        float notaComida = notatst.nextFloat();

        System.out.print("Digite a nota de ambiente: ");
        float notaAmbiente = notatst.nextFloat();

        AvaliacaoAtendimento atendimento = new AvaliacaoAtendimento(notaAtendimento);
        AvaliacaoComida comida = new AvaliacaoComida(notaComida);
        AvaliacaoAmbiente ambiente = new AvaliacaoAmbiente(notaAmbiente);

        Cliente jose = new Cliente(19324295, "Armando", "Armando@gmail.com", "12356");

        Cliente.avaliar(atendimento, comida, ambiente);
    }
}