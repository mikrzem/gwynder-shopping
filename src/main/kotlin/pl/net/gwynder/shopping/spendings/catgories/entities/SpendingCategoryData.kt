package pl.net.gwynder.shopping.spendings.catgories.entities

import com.fasterxml.jackson.annotation.JsonCreator
import pl.net.gwynder.shopping.common.catalogs.BaseCatalogData

class SpendingCategoryData @JsonCreator constructor(
        id: Long?,
        name: String
) : BaseCatalogData(id, name)