package pl.net.gwynder.shopping.spendings.catgories.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.net.gwynder.shopping.common.catalogs.BaseCatalogController
import pl.net.gwynder.shopping.spendings.catgories.entities.SpendingCategory
import pl.net.gwynder.shopping.spendings.catgories.entities.SpendingCategoryData
import pl.net.gwynder.shopping.spendings.catgories.services.SpendingCategoryService

@RestController
@RequestMapping("/api/shopping/spendings/categories")
class SpendingCategoryController(
        service: SpendingCategoryService
) : BaseCatalogController<SpendingCategory, SpendingCategoryData>(service) {

    @GetMapping("/")
    fun select(): List<SpendingCategoryData> {
        return service.select(ownerProvider.owner()).map(service::toData)
    }

}