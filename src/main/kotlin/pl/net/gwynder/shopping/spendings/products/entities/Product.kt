package pl.net.gwynder.shopping.spendings.products.entities

import pl.net.gwynder.shopping.common.catalogs.BaseCatalogEntity
import pl.net.gwynder.shopping.spendings.catgories.entities.SpendingCategory
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class Product(
        @ManyToOne(fetch = FetchType.EAGER, optional = false)
        @JoinColumn(name = "categoryId", nullable = false)
        var category: SpendingCategory = SpendingCategory(),
        name: String = "",
        owner: String = ""
) : BaseCatalogEntity(name, owner)