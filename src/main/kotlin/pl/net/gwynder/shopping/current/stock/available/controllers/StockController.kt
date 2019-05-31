package pl.net.gwynder.shopping.current.stock.available.controllers

import org.springframework.web.bind.annotation.*
import pl.net.gwynder.shopping.common.BaseController
import pl.net.gwynder.shopping.current.stock.available.entities.StockData
import pl.net.gwynder.shopping.current.stock.available.services.StockService
import pl.net.gwynder.shopping.spendings.categories.services.SpendingCategoryService
import pl.net.gwynder.shopping.spendings.products.services.ProductService

@RestController
@RequestMapping("/api/shopping/stock")
class StockController(
        private val service: StockService,
        private val categoryService: SpendingCategoryService,
        private val productService: ProductService
) : BaseController() {

    @GetMapping
    fun select(
            @RequestParam("category", required = false) categoryId: Long?,
            @RequestParam("product", required = false) productId: Long?
    ): List<StockData> {
        val owner = ownerProvider.owner()
        val category = categoryId?.let { id -> categoryService.get(owner, id) }
        val product = productId?.let { id -> productService.get(owner, id) }
        return service.select(owner, category, product)
                .map { stock -> service.toData(stock) }
    }

    @GetMapping("/{id}")
    fun get(
            @PathVariable("id") id: Long
    ): StockData {
        return service.toData(
                service.get(
                        ownerProvider.owner(),
                        id
                )
        )
    }

    @PostMapping
    fun create(
            @RequestBody data: StockData
    ): StockData {
        return service.toData(
                service.create(
                        ownerProvider.owner(),
                        data
                )
        )
    }

    @PutMapping("/{id}")
    fun update(
            @PathVariable("id") id: Long,
            @RequestBody data: StockData
    ): StockData {
        return service.toData(
                service.update(
                        ownerProvider.owner(),
                        id,
                        data
                )
        )
    }

    @DeleteMapping("/{id}")
    fun delete(
            @PathVariable("id") id: Long
    ) {
        service.delete(
                ownerProvider.owner(),
                id
        )
    }

}