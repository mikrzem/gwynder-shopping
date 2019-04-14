package pl.net.gwynder.shopping.spendings.products.services

import org.springframework.stereotype.Service
import pl.net.gwynder.shopping.common.catalogs.BaseCatalogService
import pl.net.gwynder.shopping.spendings.catgories.services.ProductCategoryService
import pl.net.gwynder.shopping.spendings.products.entities.Product
import pl.net.gwynder.shopping.spendings.products.entities.ProductData

@Service
class ProductService(
        repository: ProductRepository,
        private val categoryService: ProductCategoryService
) : BaseCatalogService<Product, ProductData>(repository) {

    override fun newEntity(owner: String, data: ProductData): Product {
        return Product(
                categoryService.fromData(owner, data.category),
                data.name,
                owner
        )
    }

    override fun fillEntity(entity: Product, data: ProductData) {
        entity.category = categoryService.fromData(entity.owner, data.category)
    }

    override fun toData(entity: Product): ProductData {
        return ProductData(
                entity.id,
                categoryService.toData(entity.category),
                entity.name
        )
    }

    override val name: String = "product"
}