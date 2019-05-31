package pl.net.gwynder.shopping.current.stock.available.services

import pl.net.gwynder.shopping.common.database.BaseEntityRepository
import pl.net.gwynder.shopping.current.stock.available.entities.Stock
import pl.net.gwynder.shopping.current.stock.selector.entities.StockSelector
import java.util.*

interface StockRepository : BaseEntityRepository<Stock> {

    fun findBySelectorIn(selectors: List<StockSelector>): List<Stock>

    fun findFirstBySelector(selector: StockSelector): Optional<Stock>

}