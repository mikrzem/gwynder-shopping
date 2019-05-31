package pl.net.gwynder.shopping.current.stock.selector.services

import pl.net.gwynder.shopping.common.database.BaseEntityRepository
import pl.net.gwynder.shopping.current.stock.selector.entities.StockSelector
import pl.net.gwynder.shopping.spendings.categories.entities.SpendingCategory
import pl.net.gwynder.shopping.spendings.products.entities.Product
import java.util.*

interface StockSelectorRepository : BaseEntityRepository<StockSelector> {

    fun findFirstByCategory(category: SpendingCategory): Optional<StockSelector>

    fun findFirstByProduct(product: Product): Optional<StockSelector>

    fun findByProductOrCategoryIn(product: Product, categories: List<SpendingCategory>): List<StockSelector>

    fun findByCategoryIn(categories: List<SpendingCategory>): List<StockSelector>

}