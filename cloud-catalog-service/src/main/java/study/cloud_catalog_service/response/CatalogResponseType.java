package study.cloud_catalog_service.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CatalogResponseType<T> {
    private T data;
}
