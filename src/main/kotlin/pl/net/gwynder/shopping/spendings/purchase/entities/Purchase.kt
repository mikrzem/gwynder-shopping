package pl.net.gwynder.shopping.spendings.purchase.entities

import pl.net.gwynder.shopping.common.database.BaseEntity
import pl.net.gwynder.shopping.spendings.catgories.entities.SpendingCategory
import pl.net.gwynder.shopping.spendings.locations.entities.SpendingLocation
import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.*

@Entity
class Purchase(
        @ManyToOne(fetch = FetchType.EAGER, optional = false)
        @JoinColumn(name = "locationId", nullable = false)
        var location: SpendingLocation = SpendingLocation(),
        @ManyToOne(fetch = FetchType.EAGER, optional = false)
        @JoinColumn(name = "categoryId", nullable = false)
        var category: SpendingCategory = SpendingCategory(),
        @Column(nullable = false)
        var purchaseDate: LocalDate = LocalDate.now(),
        @Column(nullable = false)
        var manualTotal: Boolean = false,
        @Column(nullable = false, scale = VALUE_SCALE, precision = VALUE_PRECISION)
        var total: BigDecimal = BigDecimal.ZERO,
        owner: String = ""
) : BaseEntity(owner)