package pl.net.gwynder.shopping.spendings.purchase.services

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import pl.net.gwynder.shopping.common.BaseService
import pl.net.gwynder.shopping.common.DateParser
import pl.net.gwynder.shopping.common.database.BaseEntity_
import pl.net.gwynder.shopping.common.errors.DataNotFound
import pl.net.gwynder.shopping.spendings.categories.services.SpendingCategoryService
import pl.net.gwynder.shopping.spendings.locations.services.SpendingLocationService
import pl.net.gwynder.shopping.spendings.products.services.ProductService
import pl.net.gwynder.shopping.spendings.purchase.entities.*
import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root
import javax.transaction.Transactional

@Service
class PurchaseService(
        private val repository: PurchaseRepository,
        private val productRepository: PurchaseProductRepository,
        private val productService: ProductService,
        private val locationService: SpendingLocationService,
        private val categoryService: SpendingCategoryService,
        private val dateParser: DateParser
) : BaseService() {

    fun selectLatest(owner: String): List<Purchase> {
        return repository.findByOwner(
                owner,
                PageRequest.of(
                        0, 10,
                        Sort.by(Sort.Direction.DESC, "purchaseDate")
                )
        ).content
    }

    fun select(owner: String, dateFrom: LocalDate, dateTo: LocalDate): List<Purchase> {
        return repository.findAll(PurchaseSpecification(owner, dateFrom, dateTo))
    }

    fun toData(entity: Purchase): PurchaseData {
        return PurchaseData(
                entity.id,
                locationService.toData(entity.location),
                categoryService.toData(entity.category),
                dateParser.toString(entity.purchaseDate),
                entity.manualTotal,
                entity.total,
                productRepository.findByPurchase(entity)
                        .map(this::toData)
        )
    }

    private fun toData(entity: PurchaseProduct): PurchaseProductData {
        return PurchaseProductData(
                productService.toData(entity.product),
                entity.amount,
                entity.price
        )
    }

    @Transactional
    fun create(owner: String, data: PurchaseData): Purchase {
        val purchase = Purchase(owner = owner)
        return savePurchaseFromData(purchase, data)
    }

    @Transactional
    fun update(owner: String, id: Long, data: PurchaseData): Purchase {
        val purchase = get(owner, id)
        return savePurchaseFromData(purchase, data)
    }

    private fun savePurchaseFromData(purchase: Purchase, data: PurchaseData): Purchase {
        fillEntity(purchase, data)
        val saved = repository.save(purchase)
        createPurchaseProducts(saved, data)
        return saved
    }

    private fun fillEntity(entity: Purchase, data: PurchaseData) {
        entity.category = categoryService.fromData(entity.owner, data.category)
        entity.location = locationService.fromData(entity.owner, data.location)
        entity.purchaseDate = dateParser.toDate(data.date)
        entity.manualTotal = data.manualTotal
        if (entity.manualTotal) {
            entity.total = data.total
        } else {
            entity.total = data.products.stream().map { p -> p.price }.reduce(BigDecimal.ZERO, BigDecimal::add)
        }
    }

    private fun createPurchaseProducts(purchase: Purchase, data: PurchaseData) {
        productRepository.deleteByPurchase(purchase)
        for (productData in data.products) {
            productRepository.save(
                    PurchaseProduct(
                            purchase,
                            productService.fromData(purchase.owner, productData.product),
                            productData.amount,
                            productData.price
                    )
            )
        }
    }

    fun get(owner: String, id: Long): Purchase {
        return repository.findFirstByOwnerAndId(owner, id)
                .orElseThrow { DataNotFound("purchase", id) }
    }

    fun delete(owner: String, id: Long) {
        val purchase = get(owner, id)
        repository.delete(purchase)
    }

}

private class PurchaseSpecification(
        val owner: String,
        val dateFrom: LocalDate,
        val dateTo: LocalDate
) : Specification<Purchase> {
    override fun toPredicate(root: Root<Purchase>, query: CriteriaQuery<*>, criteriaBuilder: CriteriaBuilder): Predicate? {
        val conditions = ArrayList<Predicate>()
        conditions.add(
                criteriaBuilder.equal(
                        root.get(BaseEntity_.owner),
                        owner
                )
        )
        conditions.add(
                criteriaBuilder.greaterThanOrEqualTo(
                        root.get(Purchase_.purchaseDate),
                        dateFrom
                )
        )
        conditions.add(
                criteriaBuilder.lessThanOrEqualTo(
                        root.get(Purchase_.purchaseDate),
                        dateTo
                )
        )
        return criteriaBuilder.and(*conditions.toTypedArray())
    }

}