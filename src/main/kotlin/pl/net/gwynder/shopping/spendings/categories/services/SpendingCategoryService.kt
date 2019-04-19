package pl.net.gwynder.shopping.spendings.categories.services

import org.springframework.stereotype.Service
import pl.net.gwynder.shopping.common.catalogs.BaseCatalogService
import pl.net.gwynder.shopping.spendings.categories.entities.SpendingCategory
import pl.net.gwynder.shopping.spendings.categories.entities.SpendingCategoryData

@Service
class SpendingCategoryService(
        repository: SpendingCategoryRepository
) : BaseCatalogService<SpendingCategory, SpendingCategoryData>(
        repository
) {
    override fun newEntity(owner: String, data: SpendingCategoryData): SpendingCategory {
        return SpendingCategory(
                if (data.parent == null) {
                    null
                } else {
                    fromData(owner, data.parent)
                },
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
                if (entity.parent == null) {
                    null
                } else {
                    toData(entity.parent!!)
                },
                entity.name
        )
    }

}