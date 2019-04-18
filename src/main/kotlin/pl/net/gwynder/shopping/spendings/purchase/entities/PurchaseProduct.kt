package pl.net.gwynder.shopping.spendings.purchase.entities

import pl.net.gwynder.shopping.common.database.BaseEntity
import pl.net.gwynder.shopping.spendings.products.entities.Product
import java.math.BigDecimal
import javax.persistence.*

@Entity
class PurchaseProduct(
        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "purchaseId", nullable = false)
        var purchase: Purchase = Purchase(),
        @ManyToOne(fetch = FetchType.EAGER, optional = false)
        @JoinColumn(name = "productId", nullable = false)
        var product: Product = Product(),
        @Column(nullable = false)
        var amount: Int = 0,
        @Column(nullable = false, scale = VALUE_SCALE, precision = VALUE_PRECISION)
        var price: BigDecimal = BigDecimal.ZERO,
        owner: String = ""
) : BaseEntity(owner)