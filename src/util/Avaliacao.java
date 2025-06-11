package util;
import java.util.ArrayList;
import java.util.List;
                       public abstract class Avaliacao {
                           private float nota;
                           private static List<Avaliacao> avaliacaos = new ArrayList<>();
                            
                            
                           public Avaliacao() {
                               this.nota = nota;
                           }

                           public static void adicionarAvaliacao(AvaliacaoAtendimento atendimento) {
                           }

                           public static Avaliacao[] getAvaliacao() {
                               return new Avaliacao[0];
                           }

                           public float getNota() {
                               return nota;
                           }

                           protected abstract void comentar();

                           public void setNota(int anInt) {
                           }

                           public static List<Avaliacao> getAvaliacaos() {
                                 return avaliacaos;
                           }
                           
                           public float addAvaliacao() {
                               return this.nota;
                           }

                           public void setNotaComida(float notaComida) {
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