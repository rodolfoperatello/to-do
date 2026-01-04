package br.com.bmtptecnologia.to_do.domain.user;

import java.util.Objects;

public class UserId {
   private final Long id;

   private UserId(Long id) {
      this.id = id;
   }

   public static UserId create(Long id) {
      if (Objects.isNull(id)) {
         throw new IllegalArgumentException("id cannot be null");
      }

      if (id < 0) {
         throw new IllegalArgumentException("id cannot be negative");
      }

      return new UserId(id);
   }

   public Long getId() {
      return id;
   }

   @Override
   public boolean equals(Object o) {
      if (o == null || getClass() != o.getClass()) return false;
      UserId userId = (UserId) o;
      return Objects.equals(id, userId.id);
   }

   @Override
   public int hashCode() {
      return Objects.hashCode(id);
   }
}
