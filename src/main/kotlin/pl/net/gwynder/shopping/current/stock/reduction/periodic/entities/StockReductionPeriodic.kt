package pl.net.gwynder.shopping.current.stock.reduction.periodic.entities

import pl.net.gwynder.shopping.common.database.BaseEntity
import pl.net.gwynder.shopping.current.stock.selector.entities.StockSelector
import java.time.LocalDate
import javax.persistence.*

@Entity
class StockReductionPeriodic(
        @ManyToOne(fetch = FetchType.EAGER, optional = false)
        @JoinColumn(name = "selectorId", nullable = false)
        var selector: StockSelector = StockSelector(),
        @Column(nullable = false)
        var amount: Int = 0,
        @Column(nullable = false)
        var period: Int = 0,
        @Column(nullable = false)
        var lastReduction: LocalDate = LocalDate.now(),
        owner: String = ""
) : BaseEntity(owner)