package pl.net.gwynder.shopping.current.stock.selector.entities

import pl.net.gwynder.shopping.common.database.BaseEntity
import pl.net.gwynder.shopping.spendings.categories.entities.SpendingCategory
import pl.net.gwynder.shopping.spendings.products.entities.Product
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class StockSelector(
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "categoryId")
        var category: SpendingCategory? = null,
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "productId")
        var product: Product? = null,
        owner: String = ""
) : BaseEntity(owner)