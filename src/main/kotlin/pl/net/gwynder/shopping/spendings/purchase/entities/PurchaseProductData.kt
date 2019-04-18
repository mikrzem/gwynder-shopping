package pl.net.gwynder.shopping.spendings.purchase.entities

import pl.net.gwynder.shopping.spendings.products.entities.ProductData
import java.io.Serializable
import java.math.BigDecimal

class PurchaseProductData(
        val product: ProductData,
        val amount: Int,
        val price: BigDecimal
) : Serializable