package pl.net.gwynder.shopping.earnings.income.controllers

import org.springframework.web.bind.annotation.*
import pl.net.gwynder.shopping.common.BaseController
import pl.net.gwynder.shopping.common.DateParser
import pl.net.gwynder.shopping.earnings.income.entities.IncomeData
import pl.net.gwynder.shopping.earnings.income.services.IncomeService

@RestController
@RequestMapping("/api/shopping/earnings/incomes")
class IncomeController(
        private val service: IncomeService,
        private val dateParser: DateParser
) : BaseController() {

    @GetMapping
    fun select(
            @RequestParam("dateFrom") dateFrom: String,
            @RequestParam("dateTo") dateTo: String
    ): List<IncomeData> {
        return service.select(
                ownerProvider.owner(),
                dateParser.toDate(dateFrom),
                dateParser.toDate(dateTo)
        ).map(service::toData)
    }

    @GetMapping("/latest")
    fun selectLatest(): List<IncomeData> {
        return service.selectLatest(ownerProvider.owner())
                .map(service::toData)
    }

    @GetMapping("/{id}")
    fun get(
            @PathVariable("id") id: Long
    ): IncomeData {
        return service.toData(
                service.get(
                        ownerProvider.owner(),
                        id
                )
        )
    }

    @PostMapping
    fun create(
            @RequestBody data: IncomeData
    ): IncomeData {
        return service.toData(
                service.create(
                        ownerProvider.owner(),
                        data
                )
        )
    }

    @PutMapping("/{id}")
    fun update(
            @PathVariable("id") id: Long,
            @RequestBody data: IncomeData
    ): IncomeData {
        return service.toData(
                service.update(
                        ownerProvider.owner(),
                        id,
                        data
                )
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