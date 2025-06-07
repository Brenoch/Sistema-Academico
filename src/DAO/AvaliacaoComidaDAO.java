package DAO;

import util.AvaliacaoComida;
import java.util.ArrayList;
import java.util.List;

public class AvaliacaoComidaDAO {
    private static List<AvaliacaoComida> avaliacoes = new ArrayList<>();
    private static int idCounter = 1;

    public void save(AvaliacaoComida avaliacao) {
        avaliacao.setId(idCounter++);
        avaliacoes.add(avaliacao);
    }

    public AvaliacaoComida findById(int id) {
        for (AvaliacaoComida a : avaliacoes) {
            if (a.getId() == id) {
                return a;
            }
        }
        return null;
    }

    public void update(AvaliacaoComida avaliacao) {
        for (int i = 0; i < avaliacoes.size(); i++) {
            if (avaliacoes.get(i).getId() == avaliacao.getId()) {
                avaliacoes.set(i, avaliacao);
                return;
            }
        }
    }

    public void delete(int id) {
        avaliacoes.removeIf(a -> a.getId() == id);
    }

    public List<AvaliacaoComida> listAll() {
        return new ArrayList<>(avaliacoes);
    }
}