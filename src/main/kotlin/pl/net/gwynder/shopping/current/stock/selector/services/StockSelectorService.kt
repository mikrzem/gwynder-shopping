package pl.net.gwynder.shopping.current.stock.selector.services

import org.springframework.stereotype.Service
import pl.net.gwynder.shopping.common.BaseService
import pl.net.gwynder.shopping.common.errors.InvalidStructure
import pl.net.gwynder.shopping.current.stock.available.entities.Stock
import pl.net.gwynder.shopping.current.stock.selector.entities.StockSelector
import pl.net.gwynder.shopping.current.stock.selector.entities.StockSelectorData
import pl.net.gwynder.shopping.spendings.categories.entities.SpendingCategory
import pl.net.gwynder.shopping.spendings.categories.services.SpendingCategoryService
import pl.net.gwynder.shopping.spendings.products.entities.Product
import pl.net.gwynder.shopping.spendings.products.services.ProductService

@Service
class StockSelectorService(
        private val repository: StockSelectorRepository,
        private val categoryService: SpendingCategoryService,
        private val productService: ProductService
) : BaseService() {

    fun fetchFor(category: SpendingCategory): StockSelector {
        return repository.findFirstByCategory(category).orElseGet {
            val selector = StockSelector(
                    category = category,
                    owner = category.owner
            )
            repository.save(selector)
        }
    }

    fun fetchFor(product: Product): StockSelector {
        return repository.findFirstByProduct(product).orElseGet {
            val selector = StockSelector(
                    product = product,
                    owner = product.owner
            )
            repository.save(selector)
        }
    }

    private fun findParents(category: SpendingCategory, result: MutableList<SpendingCategory>) {
        result.add(category)
        val parent = category.parent
        if (parent != null) {
            findParents(parent, result)
        }
    }

    fun findAllMatching(owner: String, category: SpendingCategory?, product: Product?): List<StockSelector> {
        return when {
            product != null -> listOf(fetchFor(product))
            category != null -> repository.findByCategoryIn(findCategories(category))
            else -> repository.findByOwner(owner)
        }
    }

    fun findAllMatching(product: Product): List<StockSelector> {
        val categories = findCategories(product.category)
        return repository.findByProductOrCategoryIn(product, categories)
    }

    private fun findCategories(category: SpendingCategory): ArrayList<SpendingCategory> {
        val categories = ArrayList<SpendingCategory>()
        findParents(category, categories)
        return categories
    }

    fun toData(selector: StockSelector): StockSelectorData {
        return StockSelectorData(
                selector.id,
                selector.category?.let { category -> categoryService.toData(category) },
                selector.product?.let { product -> productService.toData(product) },
                displayText(selector)
        )
    }

    private fun displayText(selector: StockSelector): String {
        val product = selector.product
        val category = selector.category
        return when {
            product != null -> "${displayCategory(product.category)} / ${product.name}"
            category != null -> displayCategory(category)
            else -> "Invalid selector"
        }
    }

    private fun displayCategory(category: SpendingCategory): String {
        val parent = category.parent
        return if (parent == null) {
            category.name
        } else {
            "${displayCategory(parent)} / ${category.name}"
        }
    }

    fun fromData(owner: String, data: StockSelectorData): StockSelector {
        return when {
            data.product != null -> fetchFor(
                    productService.fromData(owner, data.product)
            )
            data.category != null -> fetchFor(
                    categoryService.fromData(owner, data.category)
            )
            else -> throw InvalidStructure()
        }
    }

}