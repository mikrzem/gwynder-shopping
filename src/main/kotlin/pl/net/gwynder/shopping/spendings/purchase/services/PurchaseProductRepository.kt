package pl.net.gwynder.shopping.spendings.purchase.services

import pl.net.gwynder.shopping.common.database.BaseEntityRepository
import pl.net.gwynder.shopping.spendings.purchase.entities.Purchase
import pl.net.gwynder.shopping.spendings.purchase.entities.PurchaseProduct

interface PurchaseProductRepository : BaseEntityRepository<PurchaseProduct> {

    fun findByPurchase(purchase: Purchase): List<PurchaseProduct>

}