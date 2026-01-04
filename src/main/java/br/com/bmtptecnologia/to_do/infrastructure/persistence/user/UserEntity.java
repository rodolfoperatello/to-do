package br.com.bmtptecnologia.to_do.infrastructure.persistence.user;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity(name = "USER")
@Table(name = "USER")
public class UserEntity {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @Column(name = "FULL_NAME", nullable = false)
   private String fullName;
   @CreationTimestamp
   @Column(name = "CREATED_AT", nullable = false, updatable = false)
   private LocalDateTime createdAt;
   @UpdateTimestamp
   @Column(name = "UPDATED_AT", nullable = false)
   private LocalDateTime updatedAt;

   protected UserEntity() {
   }

   public UserEntity(String fullName) {
      this.fullName = fullName;
   }

   public Long getId() {
      return id;
   }

   public String getFullName() {
      return fullName;
   }

   public LocalDateTime getCreatedAt() {
      return createdAt;
   }

   public LocalDateTime getUpdatedAt() {
      return updatedAt;
   }

   @Override
   public boolean equals(Object o) {
      if (o == null || getClass() != o.getClass()) return false;
      UserEntity that = (UserEntity) o;
      return Objects.equals(id, that.id) && Objects.equals(fullName,
          that.fullName) && Objects.equals(createdAt,
          that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, fullName, createdAt, updatedAt);
   }
}
