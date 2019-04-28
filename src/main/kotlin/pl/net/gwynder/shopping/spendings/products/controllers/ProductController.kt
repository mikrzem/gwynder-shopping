package pl.net.gwynder.shopping.spendings.products.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import pl.net.gwynder.shopping.common.catalogs.BaseCatalogController
import pl.net.gwynder.shopping.spendings.products.entities.Product
import pl.net.gwynder.shopping.spendings.products.entities.ProductData
import pl.net.gwynder.shopping.spendings.products.services.ProductService
import java.util.*

@RestController
@RequestMapping("/api/shopping/spendings/products")
class ProductController(
        service: ProductService
) : BaseCatalogController<Product, ProductData>(service) {

    @GetMapping
    fun select(
            @RequestParam("category", required = false) category: Optional<Long>
    ): List<ProductData> {
        var results = service.select(ownerProvider.owner())
        if (category.isPresent) {
            results = results.filter { product -> product.category.id == category.get() }
        }
        return results.map(service::toData)
    }

}