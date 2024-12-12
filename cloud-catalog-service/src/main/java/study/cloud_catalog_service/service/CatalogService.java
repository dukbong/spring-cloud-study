package study.cloud_catalog_service.service;

import study.cloud_catalog_service.entity.Catalog;
import study.cloud_catalog_service.response.CatalogInfo;

import java.util.List;

public interface CatalogService {
    List<CatalogInfo> getAllCatalogs();
}
