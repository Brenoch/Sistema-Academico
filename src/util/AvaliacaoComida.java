package util;

    public class AvaliacaoComida extends Avaliacao {
        private int id;
        private float notaComida;

        public AvaliacaoComida(float notaComida) {
            super();
            this.notaComida = notaComida;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public float getNotaComida() {
            return notaComida;
        }

        @Override
        protected void comentar() {
            // Implementação do comentário
        }
    }