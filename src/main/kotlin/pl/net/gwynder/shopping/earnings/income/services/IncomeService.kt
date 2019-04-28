package pl.net.gwynder.shopping.earnings.income.services

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import pl.net.gwynder.shopping.common.BaseService
import pl.net.gwynder.shopping.common.DateParser
import pl.net.gwynder.shopping.common.errors.DataNotFound
import pl.net.gwynder.shopping.earnings.income.entities.Income
import pl.net.gwynder.shopping.earnings.income.entities.IncomeData
import pl.net.gwynder.shopping.earnings.income.entities.Income_
import pl.net.gwynder.shopping.earnings.sources.services.IncomeSourceService
import java.time.LocalDate
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

@Service
class IncomeService(
        private val repository: IncomeRepository,
        private val sourceService: IncomeSourceService,
        private val dateParser: DateParser
) : BaseService() {

    fun select(owner: String, dateFrom: LocalDate, dateTo: LocalDate): List<Income> {
        return repository.findAll(IncomeSpecification(owner, dateFrom, dateTo))
    }

    fun selectLatest(owner: String): List<Income> {
        return repository.findByOwner(
                owner,
                PageRequest.of(
                        0,
                        5,
                        Sort.by(Sort.Order.desc("incomeDate"))
                )
        ).content
    }

    fun toData(income: Income): IncomeData {
        return IncomeData(
                income.id,
                sourceService.toData(income.source),
                dateParser.toString(income.incomeDate),
                income.income
        )
    }

    fun create(owner: String, data: IncomeData): Income {
        val income = Income(
                sourceService.fromData(owner, data.source),
                dateParser.toDate(data.date),
                data.income
        )
        return repository.save(income)
    }

    fun get(owner: String, id: Long): Income {
        return repository.findFirstByOwnerAndId(owner, id)
                .orElseThrow { DataNotFound("income", id) }
    }

    fun update(owner: String, id: Long, data: IncomeData): Income {
        val income = get(owner, id)
        income.source = sourceService.fromData(owner, data.source)
        income.incomeDate = dateParser.toDate(data.date)
        income.income = data.income
        return repository.save(income)
    }

    fun delete(owner: String, id: Long) {
        val income = get(owner, id)
        repository.delete(income)
    }
}

private class IncomeSpecification(
        val owner: String,
        val dateFrom: LocalDate,
        val dateTo: LocalDate
) : Specification<Income> {

    override fun toPredicate(root: Root<Income>, query: CriteriaQuery<*>, criteriaBuilder: CriteriaBuilder): Predicate? {
        val conditions = ArrayList<Predicate>()
        conditions.add(
                criteriaBuilder.equal(
                        root.get(Income_.owner),
                        owner
                )
        )
        conditions.add(
                criteriaBuilder.greaterThanOrEqualTo(
                        root.get(Income_.incomeDate),
                        dateFrom
                )
        )
        conditions.add(
                criteriaBuilder.lessThanOrEqualTo(
                        root.get(Income_.incomeDate),
                        dateTo
                )
        )
        return criteriaBuilder.and(*conditions.toTypedArray())
    }

}