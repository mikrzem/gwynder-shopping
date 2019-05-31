package pl.net.gwynder.shopping.spendings.purchase.services

import pl.net.gwynder.shopping.spendings.purchase.entities.PurchaseChange

interface PurchaseListener {

    fun onPurchaseChange(change: PurchaseChange)

}