package study.cloud_catalog_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.cloud_catalog_service.entity.Catalog;
import study.cloud_catalog_service.repository.CatalogRepository;
import study.cloud_catalog_service.response.CatalogInfo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService{

    private final CatalogRepository catalogRepository;

    @Override
    public List<CatalogInfo> getAllCatalogs() {
        List<Catalog> findCatalogs = catalogRepository.findAll();
        return findCatalogs.stream().map(c -> new CatalogInfo(c.getProductId(), c.getProductName(), c.getUnitPrice(), c.getStock(), c.getCreatedDate())).toList();
    }
}
