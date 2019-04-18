package pl.net.gwynder.shopping.spendings.locations.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.net.gwynder.shopping.common.catalogs.BaseCatalogController
import pl.net.gwynder.shopping.spendings.locations.entities.SpendingLocation
import pl.net.gwynder.shopping.spendings.locations.entities.SpendingLocationData
import pl.net.gwynder.shopping.spendings.locations.services.SpendingLocationService

@RestController
@RequestMapping("/api/shopping/spendings/locations")
class SpendingLocationController(
        service: SpendingLocationService
) : BaseCatalogController<SpendingLocation, SpendingLocationData>(service) {

    @GetMapping("/")
    fun select(): List<SpendingLocationData> {
        return service.select(ownerProvider.owner()).map(service::toData)
    }

}