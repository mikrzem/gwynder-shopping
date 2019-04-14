package pl.net.gwynder.shopping.spendings.products.entities

import com.fasterxml.jackson.annotation.JsonCreator
import pl.net.gwynder.shopping.common.catalogs.BaseCatalogData
import pl.net.gwynder.shopping.spendings.catgories.entities.ProductCategoryData

class ProductData @JsonCreator constructor(
        id: Long?,
        val category: ProductCategoryData,
        name: String
) : BaseCatalogData(id, name)