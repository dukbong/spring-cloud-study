package study.cloud_catalog_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.cloud_catalog_service.entity.Catalog;

import java.util.Optional;

public interface CatalogRepository extends JpaRepository<Catalog, Long> {
    Optional<Catalog> findByProductId(String productId);
}
