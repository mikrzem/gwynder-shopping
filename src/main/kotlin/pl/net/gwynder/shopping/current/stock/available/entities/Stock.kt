package pl.net.gwynder.shopping.current.stock.available.entities

import pl.net.gwynder.shopping.common.database.BaseEntity
import pl.net.gwynder.shopping.current.stock.selector.entities.StockSelector
import javax.persistence.*

@Entity
class Stock(
        @ManyToOne(fetch = FetchType.EAGER, optional = false)
        @JoinColumn(name = "selectorId", nullable = false)
        var selector: StockSelector = StockSelector(),
        @Column(nullable = false)
        var amount: Int = 0,
        owner: String = ""
) : BaseEntity(owner)