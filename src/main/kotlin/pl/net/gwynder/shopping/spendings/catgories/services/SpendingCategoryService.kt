package pl.net.gwynder.shopping.spendings.catgories.services

import org.springframework.stereotype.Service
import pl.net.gwynder.shopping.common.catalogs.BaseCatalogService
import pl.net.gwynder.shopping.spendings.catgories.entities.SpendingCategory
import pl.net.gwynder.shopping.spendings.catgories.entities.SpendingCategoryData

@Service
class SpendingCategoryService(
        repository: SpendingCategoryRepository
) : BaseCatalogService<SpendingCategory, SpendingCategoryData>(
        repository
) {
    override fun newEntity(owner: String, data: SpendingCategoryData): SpendingCategory {
        return SpendingCategory(
                data.name,
                owner
        )
    }

    override fun fillEntity(entity: SpendingCategory, data: SpendingCategoryData) {
    }

    override val name: String = "product-category"

    override fun toData(entity: SpendingCategory): SpendingCategoryData {
        return SpendingCategoryData(
                entity.id,
                entity.name
        )
    }

}