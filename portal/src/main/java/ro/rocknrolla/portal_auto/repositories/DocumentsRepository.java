package ro.rocknrolla.portal_auto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.rocknrolla.portal_auto.entities.Document;

import java.util.List;

public interface DocumentsRepository extends JpaRepository<Document, Long> {

    List<Document> findByCarId(Long carId);
}
