package pl.net.gwynder.shopping.spendings.catgories.controllers

import org.springframework.web.bind.annotation.*
import pl.net.gwynder.shopping.common.BaseController
import pl.net.gwynder.shopping.spendings.catgories.entities.ProductCategoryData
import pl.net.gwynder.shopping.spendings.catgories.services.ProductCategoryService

@RestController
@RequestMapping("/api/shopping/spendings/categories")
class ProductCategoryController(
        private val service: ProductCategoryService
) : BaseController() {

    @GetMapping("/")
    fun select(): List<ProductCategoryData> {
        return service.select(ownerProvider.owner()).map(service::toData)
    }

    @GetMapping("/{id}")
    fun get(@PathVariable("id") id: Long): ProductCategoryData {
        return service.toData(
                service.get(ownerProvider.owner(), id)
        )
    }

    @PostMapping("/")
    fun create(@RequestBody data: ProductCategoryData): ProductCategoryData {
        return service.toData(
                service.create(ownerProvider.owner(), data)
        )
    }

    @PutMapping("/{id}")
    fun update(
            @PathVariable("id") id: Long,
            @RequestBody data: ProductCategoryData
    ): ProductCategoryData {
        return service.toData(
                service.update(ownerProvider.owner(), id, data)
        )
    }

    @DeleteMapping("/{id}")
    fun delete(
            @PathVariable("id") id: Long
    ) {
        service.delete(ownerProvider.owner(), id)
    }

}