package pl.net.gwynder.shopping.current.stock.available.services

import org.springframework.stereotype.Service
import pl.net.gwynder.shopping.common.BaseService
import pl.net.gwynder.shopping.common.errors.DataNotFound
import pl.net.gwynder.shopping.current.stock.available.entities.Stock
import pl.net.gwynder.shopping.current.stock.available.entities.StockData
import pl.net.gwynder.shopping.current.stock.selector.entities.StockSelector
import pl.net.gwynder.shopping.current.stock.selector.services.StockSelectorService
import pl.net.gwynder.shopping.spendings.categories.entities.SpendingCategory
import pl.net.gwynder.shopping.spendings.products.entities.Product
import pl.net.gwynder.shopping.spendings.purchase.entities.PurchaseChange
import pl.net.gwynder.shopping.spendings.purchase.services.PurchaseListener

@Service
class StockManager(
        private val repository: StockRepository,
        private val selectorService: StockSelectorService
) : BaseService(), StockService, PurchaseListener {

    override fun select(owner: String, category: SpendingCategory?, product: Product?): List<Stock> {
        val selectors = selectorService.findAllMatching(owner, category, product)
        return repository.findBySelectorIn(selectors)
    }

    override fun get(owner: String, id: Long): Stock {
        return repository.findFirstByOwnerAndId(owner, id)
                .orElseThrow { DataNotFound("stock", id) }
    }

    override fun create(owner: String, data: StockData): Stock {
        val selector = selectorService.fromData(owner, data.selector)
        val stock = Stock(selector, data.amount, owner)
        return repository.save(stock)
    }

    override fun update(owner: String, id: Long, data: StockData): Stock {
        val selector = selectorService.fromData(owner, data.selector)
        val stock = get(owner, id)
        stock.selector = selector
        stock.amount = data.amount
        return repository.save(stock)
    }

    override fun delete(owner: String, id: Long) {
        val stock = get(owner, id)
        repository.delete(stock)
    }

    override fun onPurchaseChange(change: PurchaseChange) {
        val selectors = selectorService.findAllMatching(change.product)
        if (selectors.isNotEmpty()) {
            repository.findBySelectorIn(selectors).forEach { stock ->
                applyChange(stock, change)
            }
        }
    }

    private fun applyChange(stock: Stock, change: PurchaseChange) {
        stock.amount += change.amount
        repository.save(stock)
    }

    override fun toData(stock: Stock): StockData {
        return StockData(
                stock.id,
                selectorService.toData(stock.selector),
                stock.amount
        )
    }

    override fun changeStock(selector: StockSelector, amountChange: Int) {
        repository.findFirstBySelector(selector).ifPresent { stock ->
            stock.amount += amountChange
            repository.save(stock)
        }
    }

}