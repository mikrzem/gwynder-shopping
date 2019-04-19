package pl.net.gwynder.shopping.spendings.purchase.entities

import pl.net.gwynder.shopping.common.BaseData
import pl.net.gwynder.shopping.spendings.categories.entities.SpendingCategoryData
import pl.net.gwynder.shopping.spendings.locations.entities.SpendingLocationData
import java.math.BigDecimal

class PurchaseData(
        id: Long?,
        val location: SpendingLocationData,
        val category: SpendingCategoryData,
        val date: String,
        val manualTotal: Boolean,
        val total: BigDecimal,
        val products: List<PurchaseProductData>
) : BaseData(id)