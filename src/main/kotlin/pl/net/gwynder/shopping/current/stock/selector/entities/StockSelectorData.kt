package pl.net.gwynder.shopping.current.stock.selector.entities

import pl.net.gwynder.shopping.common.BaseData
import pl.net.gwynder.shopping.spendings.categories.entities.SpendingCategoryData
import pl.net.gwynder.shopping.spendings.products.entities.ProductData

class StockSelectorData(
        id: Long?,
        val category: SpendingCategoryData?,
        val product: ProductData?,
        val display: String
) : BaseData(id)