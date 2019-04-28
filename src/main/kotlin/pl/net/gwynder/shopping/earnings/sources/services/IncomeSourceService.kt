package pl.net.gwynder.shopping.earnings.sources.services

import org.springframework.stereotype.Service
import pl.net.gwynder.shopping.common.catalogs.BaseCatalogService
import pl.net.gwynder.shopping.earnings.sources.entities.IncomeSource
import pl.net.gwynder.shopping.earnings.sources.entities.IncomeSourceData

@Service
class IncomeSourceService(
        repository: IncomeSourceRepository
) : BaseCatalogService<IncomeSource, IncomeSourceData>(repository) {
    override fun newEntity(owner: String, data: IncomeSourceData): IncomeSource {
        return IncomeSource(
                data.name,
                owner
        )
    }

    override fun fillEntity(entity: IncomeSource, data: IncomeSourceData) {
    }

    override fun toData(entity: IncomeSource): IncomeSourceData {
        return IncomeSourceData(
                entity.id,
                entity.name
        )
    }

    override val name: String = "income-source"
}