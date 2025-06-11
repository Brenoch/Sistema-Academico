package util;

public class AvaliacaoLocalizacao extends Avaliacao {
        private float notaLocalizacao;

        public AvaliacaoLocalizacao(float notaLocalizacao) {
            super();
            this.notaLocalizacao = notaLocalizacao;
        }
        public float getNotaLocalizacao() {
            return notaLocalizacao;
        }

        @Override
        protected void comentar() {

        }
    }

