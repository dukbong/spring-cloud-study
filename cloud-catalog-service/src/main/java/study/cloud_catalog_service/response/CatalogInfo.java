package study.cloud_catalog_service.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CatalogInfo {
    private String productId;
    private String productName;
    private Integer unitPrice;
    private Integer stock;
    private LocalDateTime createTime;
}