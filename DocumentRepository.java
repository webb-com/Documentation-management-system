package com.example.docsystem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// JpaRepository<EntityType, PrimaryKeyType>
@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
}
