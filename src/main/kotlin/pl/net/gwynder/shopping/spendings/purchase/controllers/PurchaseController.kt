package pl.net.gwynder.shopping.spendings.purchase.controllers

import org.springframework.web.bind.annotation.*
import pl.net.gwynder.shopping.common.BaseController
import pl.net.gwynder.shopping.common.DateParser
import pl.net.gwynder.shopping.spendings.purchase.entities.PurchaseData
import pl.net.gwynder.shopping.spendings.purchase.services.PurchaseService

@RestController
@RequestMapping("/api/shopping/spendings/purchases")
class PurchaseController(
        private val service: PurchaseService,
        private val dateParser: DateParser
) : BaseController() {

    @GetMapping
    fun select(
            @RequestParam("dateFrom") dateFrom: String,
            @RequestParam("dateTo") dateTo: String
    ): List<PurchaseData> {
        return service.select(
                ownerProvider.owner(),
                dateParser.toDate(dateFrom),
                dateParser.toDate(dateTo)
        ).map(service::toData)
    }

    @GetMapping("/latest")
    fun selectLatest(): List<PurchaseData> {
        return service.selectLatest(
                ownerProvider.owner()
        ).map(service::toData)
    }

    @GetMapping("/{id}")
    fun get(
            @PathVariable("id") id: Long
    ): PurchaseData {
        return service.toData(
                service.get(ownerProvider.owner(), id)
        )
    }

    @PostMapping
    fun create(
            @RequestBody data: PurchaseData
    ): PurchaseData {
        return service.toData(
                service.create(ownerProvider.owner(), data)
        )
    }

    @PutMapping("/{id}")
    fun update(
            @PathVariable("id") id: Long,
            @RequestBody data: PurchaseData
    ): PurchaseData {
        return service.toData(
                service.update(ownerProvider.owner(), id, data)
        )
    }

    @DeleteMapping("/{id}")
    fun delete(
            @PathVariable("id") id: Long
    ) {
        service.delete(
                ownerProvider.owner(),
                id
        )
    }
}