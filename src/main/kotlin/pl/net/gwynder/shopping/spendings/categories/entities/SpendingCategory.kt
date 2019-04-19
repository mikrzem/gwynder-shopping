package pl.net.gwynder.shopping.spendings.categories.entities

import pl.net.gwynder.shopping.common.catalogs.BaseCatalogEntity
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class SpendingCategory(
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "parentId")
        var parent: SpendingCategory? = null,
        name: String = "",
        owner: String = ""
) : BaseCatalogEntity(name, owner)