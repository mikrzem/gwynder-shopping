package pl.net.gwynder.shopping.spendings.locations.services

import org.springframework.stereotype.Service
import pl.net.gwynder.shopping.common.catalogs.BaseCatalogService
import pl.net.gwynder.shopping.spendings.locations.entities.SpendingLocation
import pl.net.gwynder.shopping.spendings.locations.entities.SpendingLocationData

@Service
class SpendingLocationService(
        repository: SpendingLocationRepository
) : BaseCatalogService<SpendingLocation, SpendingLocationData>(repository) {

    override fun newEntity(owner: String, data: SpendingLocationData): SpendingLocation {
        return SpendingLocation(
                data.name,
                owner
        )
    }

    override fun fillEntity(entity: SpendingLocation, data: SpendingLocationData) {
    }

    override fun toData(entity: SpendingLocation): SpendingLocationData {
        return SpendingLocationData(
                entity.id,
                entity.name
        )
    }

    override val name: String = "spending-location"

}