package pl.net.gwynder.shopping.spendings.categories.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.net.gwynder.shopping.common.catalogs.BaseCatalogController
import pl.net.gwynder.shopping.spendings.categories.entities.SpendingCategory
import pl.net.gwynder.shopping.spendings.categories.entities.SpendingCategoryData
import pl.net.gwynder.shopping.spendings.categories.services.SpendingCategoryService

@RestController
@RequestMapping("/api/shopping/spendings/categories")
class SpendingCategoryController(
        service: SpendingCategoryService
) : BaseCatalogController<SpendingCategory, SpendingCategoryData>(service) {

    @GetMapping
    fun select(): List<SpendingCategoryData> {
        return service.select(ownerProvider.owner()).map(service::toData)
    }

}