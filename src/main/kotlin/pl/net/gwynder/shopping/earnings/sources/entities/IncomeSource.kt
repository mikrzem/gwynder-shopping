package pl.net.gwynder.shopping.earnings.sources.entities

import pl.net.gwynder.shopping.common.catalogs.BaseCatalogEntity
import javax.persistence.Entity

@Entity
class IncomeSource(
        name: String = "",
        owner: String = ""
) : BaseCatalogEntity(name, owner)