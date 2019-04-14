package pl.net.gwynder.shopping.central.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.net.gwynder.shopping.central.entities.responses.DashboardInfo
import pl.net.gwynder.shopping.common.BaseController

@RestController
@RequestMapping("/api/shopping/central/dashboard")
class DashboardController : BaseController() {

    @GetMapping("/")
    fun readDashboard(): DashboardInfo {
        return DashboardInfo(
                "Shopping and finances",
                ArrayList()
        )
    }

}