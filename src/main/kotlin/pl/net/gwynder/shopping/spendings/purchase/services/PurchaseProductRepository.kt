package pl.net.gwynder.shopping.spendings.purchase.services

import org.springframework.data.jpa.repository.Modifying
import pl.net.gwynder.shopping.common.database.BaseEntityRepository
import pl.net.gwynder.shopping.spendings.purchase.entities.Purchase
import pl.net.gwynder.shopping.spendings.purchase.entities.PurchaseProduct
import javax.transaction.Transactional

interface PurchaseProductRepository : BaseEntityRepository<PurchaseProduct> {

    fun findByPurchase(purchase: Purchase): List<PurchaseProduct>

    @Modifying
    @Transactional
    fun deleteByPurchase(purchase: Purchase)

}