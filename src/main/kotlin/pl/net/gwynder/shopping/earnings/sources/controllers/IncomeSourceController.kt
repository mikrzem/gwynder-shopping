package pl.net.gwynder.shopping.earnings.sources.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.net.gwynder.shopping.common.catalogs.BaseCatalogController
import pl.net.gwynder.shopping.earnings.sources.entities.IncomeSource
import pl.net.gwynder.shopping.earnings.sources.entities.IncomeSourceData
import pl.net.gwynder.shopping.earnings.sources.services.IncomeSourceService

@RestController
@RequestMapping("/api/shopping/earnings/sources")
class IncomeSourceController(
        service: IncomeSourceService
) : BaseCatalogController<IncomeSource, IncomeSourceData>(service) {

    @GetMapping
    fun select(): List<IncomeSourceData> {
        return service.select(
                ownerProvider.owner()
        ).map(service::toData)
    }

}