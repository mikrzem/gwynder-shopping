package pl.net.gwynder.shopping.current.stock.available.entities

import pl.net.gwynder.shopping.common.BaseData
import pl.net.gwynder.shopping.current.stock.selector.entities.StockSelectorData

class StockData(
        id: Long?,
        val selector: StockSelectorData,
        val amount: Int
) : BaseData(id)