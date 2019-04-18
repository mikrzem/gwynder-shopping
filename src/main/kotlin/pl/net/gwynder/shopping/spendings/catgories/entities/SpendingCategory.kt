package pl.net.gwynder.shopping.spendings.catgories.entities

import pl.net.gwynder.shopping.common.catalogs.BaseCatalogEntity
import javax.persistence.Entity

@Entity
class SpendingCategory(
        name: String = "",
        owner: String = ""
) : BaseCatalogEntity(name, owner)