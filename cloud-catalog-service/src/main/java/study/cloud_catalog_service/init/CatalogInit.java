package study.cloud_catalog_service.init;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import study.cloud_catalog_service.entity.Catalog;
import study.cloud_catalog_service.repository.CatalogRepository;

@Component
@RequiredArgsConstructor
public class CatalogInit {

    private final CatalogRepository catalogRepository;

    @PostConstruct
    public void init() {
        for(int i = 1; i <= 3; i++) {
            catalogRepository.save(Catalog.builder()
                    .productId("CATALOG" + i)
                    .productName("catalogName" + i)
                    .stock(100 + (10 * i))
                    .unitPrice(1000 * i)
                    .build());
        }
    }
}
