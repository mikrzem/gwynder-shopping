package pl.net.gwynder.shopping.spendings.products.entities

import com.fasterxml.jackson.annotation.JsonCreator
import pl.net.gwynder.shopping.common.catalogs.BaseCatalogData
import pl.net.gwynder.shopping.spendings.catgories.entities.SpendingCategoryData

class ProductData @JsonCreator constructor(
        id: Long?,
        val category: SpendingCategoryData,
        name: String
) : BaseCatalogData(id, name)