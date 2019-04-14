package pl.net.gwynder.shopping.spendings.catgories.services

import org.springframework.stereotype.Service
import pl.net.gwynder.shopping.common.catalogs.BaseCatalogService
import pl.net.gwynder.shopping.spendings.catgories.entities.ProductCategory
import pl.net.gwynder.shopping.spendings.catgories.entities.ProductCategoryData

@Service
class ProductCategoryService(
        repository: ProductCategoryRepository
) : BaseCatalogService<ProductCategory, ProductCategoryData>(
        repository
) {
    override fun newEntity(owner: String, data: ProductCategoryData): ProductCategory {
        return ProductCategory(
                data.name,
                owner
        )
    }

    override fun fillEntity(entity: ProductCategory, data: ProductCategoryData) {
    }

    override val name: String = "product-category"

    override fun toData(entity: ProductCategory): ProductCategoryData {
        return ProductCategoryData(
                entity.id,
                entity.name
        )
    }

}