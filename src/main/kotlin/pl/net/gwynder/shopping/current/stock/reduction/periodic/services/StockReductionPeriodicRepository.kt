package pl.net.gwynder.shopping.current.stock.reduction.periodic.services

import pl.net.gwynder.shopping.common.database.BaseEntityRepository
import pl.net.gwynder.shopping.current.stock.reduction.periodic.entities.StockReductionPeriodic
import pl.net.gwynder.shopping.current.stock.selector.entities.StockSelector

interface StockReductionPeriodicRepository : BaseEntityRepository<StockReductionPeriodic> {

    fun findBySelector(selector: StockSelector): List<StockReductionPeriodic>

}