package pl.net.gwynder.shopping.common.database

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean
import java.util.*

@NoRepositoryBean
interface BaseEntityRepository<Entity : BaseEntity> : JpaRepository<Entity, Long> {

    fun findByOwner(owner: String): List<Entity>

    fun findFirstByOwnerAndId(owner: String, id: Long): Optional<Entity>

}