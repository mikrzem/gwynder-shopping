package pl.net.gwynder.shopping.spendings.catgories.services

import org.springframework.stereotype.Service
import pl.net.gwynder.shopping.common.BaseService
import pl.net.gwynder.shopping.common.errors.DataNotFound
import pl.net.gwynder.shopping.spendings.catgories.entities.ProductCategory
import pl.net.gwynder.shopping.spendings.catgories.entities.ProductCategoryData

@Service
class ProductCategoryService(
        private val repository: ProductCategoryRepository
) : BaseService() {

    fun select(owner: String): List<ProductCategory> {
        return repository.findByOwner(owner)
    }

    fun create(owner: String, data: ProductCategoryData): ProductCategory {
        return repository.save(
                ProductCategory(
                        data.name,
                        owner
                )
        )
    }

    fun get(owner: String, id: Long): ProductCategory {
        return repository.findFirstByOwnerAndId(owner, id)
                .orElseThrow { DataNotFound("category", id) }
    }

    fun update(owner: String, id: Long, data: ProductCategoryData): ProductCategory {
        val current = get(owner, id)
        current.name = data.name
        return repository.save(current)
    }

    fun delete(owner: String, id: Long) {
        val current = get(owner, id)
        repository.delete(current)
    }

    fun toData(entity: ProductCategory): ProductCategoryData {
        return ProductCategoryData(
                entity.id,
                entity.name
        )
    }

}