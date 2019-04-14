package pl.net.gwynder.shopping.common.catalogs

import org.springframework.data.repository.NoRepositoryBean
import pl.net.gwynder.shopping.common.database.BaseEntityRepository

@NoRepositoryBean
interface BaseCatalogRepository<Catalog : BaseCatalogEntity> : BaseEntityRepository<Catalog>