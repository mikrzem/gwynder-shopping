package pl.net.gwynder.shopping.spendings.purchase.controllers

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.net.gwynder.shopping.common.BaseController
import pl.net.gwynder.shopping.spendings.purchase.services.PurchaseService

@RestController
@RequestMapping("/api/shopping/spendings/purchases")
class PurchaseController(
        private val service: PurchaseService
) : BaseController() {
}