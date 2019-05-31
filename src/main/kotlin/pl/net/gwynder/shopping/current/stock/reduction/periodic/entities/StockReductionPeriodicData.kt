package pl.net.gwynder.shopping.current.stock.reduction.periodic.entities

import pl.net.gwynder.shopping.common.BaseData
import pl.net.gwynder.shopping.current.stock.selector.entities.StockSelectorData

class StockReductionPeriodicData(
        id: Long?,
        val selector: StockSelectorData,
        val amount: Int,
        val period: Int
) : BaseData(id)