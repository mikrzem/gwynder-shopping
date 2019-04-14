package pl.net.gwynder.shopping.common

import org.springframework.beans.factory.annotation.Autowired

abstract class BaseController : BaseService() {

    @Autowired
    lateinit var ownerProvider: OwnerProvider

}