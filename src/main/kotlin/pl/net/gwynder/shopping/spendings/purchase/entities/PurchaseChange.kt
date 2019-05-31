package pl.net.gwynder.shopping.spendings.purchase.entities

import pl.net.gwynder.shopping.spendings.products.entities.Product
import java.math.BigDecimal

data class PurchaseChange(
        val owner: String,
        val product: Product,
        val amount: Int,
        val price: BigDecimal
)