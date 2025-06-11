package util;
import java.sql.Date;

public class Restaurante {
   private int idrestaurante;
   private String nome;
   private String local;
   private Date datasql = Date.valueOf("2010-07-03");

   public String getNome() {
      return nome;
   }

   public int getIdrestaurante() {
      return idrestaurante;
   }

   public String getLocal() {
      return local;
   }

   public Date getDatasql() {
      return datasql;
   }

   public void setIdrestaurante(int idrestaurante) {
      this.idrestaurante = idrestaurante;
   }

   public void setNome(String nome) {
      this.nome = nome;
   }

   public void setLocal(String local) {
      this.local = local;
   }

   public void setDatasql(Date datasql) {
      this.datasql = datasql;
   }
}
