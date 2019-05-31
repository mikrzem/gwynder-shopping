package pl.net.gwynder.shopping.current.stock.available.services

import pl.net.gwynder.shopping.current.stock.available.entities.Stock
import pl.net.gwynder.shopping.current.stock.available.entities.StockData
import pl.net.gwynder.shopping.current.stock.selector.entities.StockSelector
import pl.net.gwynder.shopping.spendings.categories.entities.SpendingCategory
import pl.net.gwynder.shopping.spendings.products.entities.Product

interface StockService {

    fun toData(stock: Stock): StockData

    fun select(owner: String, category: SpendingCategory?, product: Product?): List<Stock>

    fun get(owner: String, id: Long): Stock

    fun create(owner: String, data: StockData): Stock

    fun update(owner: String, id: Long, data: StockData): Stock

    fun delete(owner: String, id: Long)

    fun changeStock(selector: StockSelector, amountChange: Int)

}