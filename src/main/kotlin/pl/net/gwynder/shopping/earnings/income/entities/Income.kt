package pl.net.gwynder.shopping.earnings.income.entities

import pl.net.gwynder.shopping.common.database.BaseEntity
import pl.net.gwynder.shopping.common.database.VALUE_PRECISION
import pl.net.gwynder.shopping.common.database.VALUE_SCALE
import pl.net.gwynder.shopping.earnings.sources.entities.IncomeSource
import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.*

@Entity
class Income(
        @ManyToOne(fetch = FetchType.EAGER, optional = false)
        @JoinColumn(name = "sourceId", nullable = false)
        var source: IncomeSource = IncomeSource(),
        @Column(nullable = false)
        var incomeDate: LocalDate = LocalDate.now(),
        @Column(nullable = false, scale = VALUE_SCALE, precision = VALUE_PRECISION)
        var income: BigDecimal = BigDecimal.ZERO
) : BaseEntity()