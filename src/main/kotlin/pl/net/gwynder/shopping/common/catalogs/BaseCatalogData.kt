package pl.net.gwynder.shopping.common.catalogs

import pl.net.gwynder.shopping.common.BaseData

abstract class BaseCatalogData(
        id: Long?,
        val name: String
) : BaseData(id)