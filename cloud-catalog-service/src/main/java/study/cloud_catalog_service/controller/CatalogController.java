package study.cloud_catalog_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.cloud_catalog_service.response.CatalogInfo;
import study.cloud_catalog_service.response.CatalogResponseType;
import study.cloud_catalog_service.service.CatalogService;

import java.util.List;

@RestController
@RequestMapping("/catalog-service")
@RequiredArgsConstructor
public class CatalogController {

    private final CatalogService catalogServiceImpl;
    private final Environment env;

    @GetMapping("/health_check")
    public String healthCheck() {
        return String.format("This is catalog Service. PORT NUMBER = %s", env.getProperty("local.server.port"));
    }

    @GetMapping("/catalogs")
    public ResponseEntity<CatalogResponseType<List<CatalogInfo>>> getAllCatalogs() {
        List<CatalogInfo> catalogs = catalogServiceImpl.getAllCatalogs();
        return ResponseEntity.ok().body(new CatalogResponseType<>(catalogs));
    }

}
