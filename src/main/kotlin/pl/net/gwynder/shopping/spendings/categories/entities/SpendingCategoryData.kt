package pl.net.gwynder.shopping.spendings.categories.entities

import com.fasterxml.jackson.annotation.JsonCreator
import pl.net.gwynder.shopping.common.catalogs.BaseCatalogData

class SpendingCategoryData @JsonCreator constructor(
        id: Long?,
        val parent: SpendingCategoryData?,
        name: String
) : BaseCatalogData(id, name)