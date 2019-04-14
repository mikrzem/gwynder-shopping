package pl.net.gwynder.shopping.spendings.catgories.entities

import pl.net.gwynder.shopping.common.database.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity

@Entity
class ProductCategory(
        @Column(nullable = false)
        var name: String = "",
        owner: String = ""
) : BaseEntity(owner)