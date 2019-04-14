package pl.net.gwynder.shopping.common.catalogs

import pl.net.gwynder.shopping.common.database.BaseEntity
import javax.persistence.Column
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class BaseCatalogEntity(
        @Column(nullable = false)
        var name: String = "",
        owner: String = ""
) : BaseEntity(owner)