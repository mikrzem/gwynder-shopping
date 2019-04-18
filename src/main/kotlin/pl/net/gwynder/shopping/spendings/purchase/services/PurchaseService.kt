package pl.net.gwynder.shopping.spendings.purchase.services

import org.springframework.stereotype.Service
import pl.net.gwynder.shopping.common.BaseService
import pl.net.gwynder.shopping.common.errors.DataNotFound
import pl.net.gwynder.shopping.spendings.catgories.services.SpendingCategoryService
import pl.net.gwynder.shopping.spendings.locations.services.SpendingLocationService
import pl.net.gwynder.shopping.spendings.products.services.ProductService
import pl.net.gwynder.shopping.spendings.purchase.entities.Purchase
import pl.net.gwynder.shopping.spendings.purchase.entities.PurchaseData
import pl.net.gwynder.shopping.spendings.purchase.entities.PurchaseProduct
import pl.net.gwynder.shopping.spendings.purchase.entities.PurchaseProductData
import java.time.format.DateTimeFormatter
import javax.transaction.Transactional

@Service
class PurchaseService(
        private val repository: PurchaseRepository,
        private val productRepository: PurchaseProductRepository,
        private val productService: ProductService,
        private val locationService: SpendingLocationService,
        private val categoryService: SpendingCategoryService
) : BaseService() {

    fun toData(entity: Purchase): PurchaseData {
        return PurchaseData(
                entity.id,
                locationService.toData(entity.location),
                categoryService.toData(entity.category),
                entity.purchaseDate.format(DateTimeFormatter.ISO_LOCAL_DATE),
                entity.manualTotal,
                entity.total,
                productRepository.findByPurchase(entity)
                        .map(this::toData)
        )
    }

    private fun toData(entity: PurchaseProduct): PurchaseProductData {
        return PurchaseProductData(
                productService.toData(entity.product),
                entity.amount,
                entity.price
        )
    }

    @Transactional
    fun create(owner: String, data: PurchaseData): Purchase {
        val purchase = Purchase(owner = owner)
        return savePurchaseFromData(purchase, data)
    }

    @Transactional
    fun update(owner: String, id: Long, data: PurchaseData): Purchase {
        val purchase = get(owner, id)
        return savePurchaseFromData(purchase, data)
    }

    private fun savePurchaseFromData(purchase: Purchase, data: PurchaseData): Purchase {
        fillEntity(purchase, data)
        val saved = repository.save(purchase)
        createPurchaseProducts(saved, data)
        return saved
    }

    private fun fillEntity(entity: Purchase, data: PurchaseData) {

    }

    private fun createPurchaseProducts(purchase: Purchase, data: PurchaseData) {

    }

    fun get(owner: String, id: Long): Purchase {
        return repository.findFirstByOwnerAndId(owner, id)
                .orElseThrow { DataNotFound("purchase", id) }
    }

    fun delete(owner: String, id: Long) {
        val purchase = get(owner, id)
        repository.delete(purchase)
    }

}