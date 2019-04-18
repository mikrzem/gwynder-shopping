package pl.net.gwynder.shopping.spendings.locations.entities

import pl.net.gwynder.shopping.common.catalogs.BaseCatalogEntity
import javax.persistence.Entity

@Entity
class SpendingLocation(
        name: String = "",
        owner: String = ""
) : BaseCatalogEntity(name, owner)