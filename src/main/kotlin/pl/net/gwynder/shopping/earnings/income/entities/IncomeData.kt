package pl.net.gwynder.shopping.earnings.income.entities

import pl.net.gwynder.shopping.common.BaseData
import pl.net.gwynder.shopping.earnings.sources.entities.IncomeSourceData
import java.math.BigDecimal

class IncomeData(
        id: Long?,
        val source: IncomeSourceData,
        val date: String,
        val income: BigDecimal
) : BaseData(id)