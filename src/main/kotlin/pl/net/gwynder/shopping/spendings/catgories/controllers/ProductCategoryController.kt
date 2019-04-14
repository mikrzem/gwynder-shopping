package pl.net.gwynder.shopping.spendings.catgories.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.net.gwynder.shopping.common.catalogs.BaseCatalogController
import pl.net.gwynder.shopping.spendings.catgories.entities.ProductCategory
import pl.net.gwynder.shopping.spendings.catgories.entities.ProductCategoryData
import pl.net.gwynder.shopping.spendings.catgories.services.ProductCategoryService

@RestController
@RequestMapping("/api/shopping/spendings/categories")
class ProductCategoryController(
        service: ProductCategoryService
) : BaseCatalogController<ProductCategory, ProductCategoryData>(service) {

    @GetMapping("/")
    fun select(): List<ProductCategoryData> {
        return service.select(ownerProvider.owner()).map(service::toData)
    }

}