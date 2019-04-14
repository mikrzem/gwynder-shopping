package pl.net.gwynder.shopping.spendings.catgories.entities

import com.fasterxml.jackson.annotation.JsonCreator
import pl.net.gwynder.shopping.common.BaseData

class ProductCategoryData @JsonCreator constructor(
        id: Long?,
        val name: String
) : BaseData(id)