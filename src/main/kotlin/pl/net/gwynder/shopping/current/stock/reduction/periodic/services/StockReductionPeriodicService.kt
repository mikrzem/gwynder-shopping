package pl.net.gwynder.shopping.current.stock.reduction.periodic.services

import org.springframework.stereotype.Service
import pl.net.gwynder.shopping.common.BaseService
import pl.net.gwynder.shopping.common.errors.DataNotFound
import pl.net.gwynder.shopping.current.stock.available.entities.Stock
import pl.net.gwynder.shopping.current.stock.reduction.periodic.entities.StockReductionPeriodic
import pl.net.gwynder.shopping.current.stock.reduction.periodic.entities.StockReductionPeriodicData
import pl.net.gwynder.shopping.current.stock.selector.services.StockSelectorService
import java.time.LocalDate

@Service
class StockReductionPeriodicService(
        private val repository: StockReductionPeriodicRepository,
        private val selectorService: StockSelectorService
) : BaseService() {

    fun select(stock: Stock): List<StockReductionPeriodic> {
        return repository.findBySelector(stock.selector)
    }

    fun get(owner: String, id: Long, stock: Stock): StockReductionPeriodic {
        return repository.findFirstByOwnerAndId(owner, id)
                .filter { reduction -> reduction.selector.id == stock.selector.id }
                .orElseThrow { DataNotFound("stock-reduction-periodic", id) }
    }

    fun create(owner: String, stock: Stock, data: StockReductionPeriodicData): StockReductionPeriodic {
        val reduction = StockReductionPeriodic(
                stock.selector,
                data.amount,
                data.period,
                LocalDate.now(),
                owner
        )
        return repository.save(reduction)
    }

    fun update(owner: String, id: Long, stock: Stock, data: StockReductionPeriodicData): StockReductionPeriodic {
        val reduction = get(owner, id, stock)
        reduction.amount = data.amount
        reduction.period = data.period
        reduction.lastReduction = LocalDate.now()
        return repository.save(reduction)
    }

    fun delete(owner: String, id: Long, stock: Stock) {
        val reduction = get(owner, id, stock)
        repository.delete(reduction)
    }

    fun toData(reduction: StockReductionPeriodic): StockReductionPeriodicData {
        return StockReductionPeriodicData(
                reduction.id,
                selectorService.toData(reduction.selector),
                reduction.amount,
                reduction.period
        )
    }

}