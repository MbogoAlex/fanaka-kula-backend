package com.fanaka.kula.dao;

import com.fanaka.kula.models.Loan;
import com.fanaka.kula.models.PagedResult;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LoanDaoImpl implements LoanDao{

    private final EntityManager entityManager;

    @Autowired
    public LoanDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Loan getLoanByID(Long id) {
        TypedQuery<Loan> query = entityManager.createQuery("from Loan where id = :id", Loan.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public PagedResult<Loan> getLoansByClientID(
            Long clientId,
            String clientType,
            String repaymentFrequencyType,
            String interestRateType,
            String interestMethodology,
            String repaymentSchedule,
            String status,
            String onboardingStage,
            String createdAtSort,
            LocalDateTime startDate,
            LocalDateTime endDate,
            Integer currentPage,
            Integer size
    ) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        // --- Build the main query ---
        CriteriaQuery<Loan> cq = cb.createQuery(Loan.class);
        Root<Loan> root = cq.from(Loan.class);
        List<Predicate> predicates = new ArrayList<>();

        // mandatory filter
        predicates.add(cb.equal(root.get("client").get("id"), clientId));

        // optional filters
        if (clientType != null && !clientType.isEmpty()) {
            predicates.add(cb.equal(
                    root.get("clientType"),
                    Loan.ClientType.valueOf(clientType))
            );
        }
        if (repaymentFrequencyType != null && !repaymentFrequencyType.isEmpty()) {
            predicates.add(cb.equal(
                    root.get("repaymentFrequencyType"),
                    Loan.RepaymentFrequencyType.valueOf(repaymentFrequencyType))
            );
        }
        if (interestRateType != null && !interestRateType.isEmpty()) {
            predicates.add(cb.equal(
                    root.get("interestRateType"),
                    Loan.InterestRateType.valueOf(interestRateType))
            );
        }
        if (interestMethodology != null && !interestMethodology.isEmpty()) {
            predicates.add(cb.equal(
                    root.get("interestMethodology"),
                    Loan.InterestMethodology.valueOf(interestMethodology))
            );
        }
        if (repaymentSchedule != null && !repaymentSchedule.isEmpty()) {
            predicates.add(cb.equal(root.get("repaymentSchedule"), repaymentSchedule));
        }
        if (status != null && !status.isEmpty()) {
            predicates.add(cb.equal(
                    root.get("status"),
                    Loan.LoanStatus.valueOf(status))
            );
        }
        if (onboardingStage != null && !onboardingStage.isEmpty()) {
            predicates.add(cb.equal(root.get("onboardingStage"), onboardingStage));
        }
        if (startDate != null && endDate != null) {
            predicates.add(cb.between(root.get("createdAt"), startDate, endDate));
        }

        cq.where(predicates.toArray(new Predicate[0]));

        // sorting
        if ("asc".equalsIgnoreCase(createdAtSort)) {
            cq.orderBy(cb.asc(root.get("createdAt")));
        } else if ("desc".equalsIgnoreCase(createdAtSort)) {
            cq.orderBy(cb.desc(root.get("createdAt")));
        }

        // execute paged query
        TypedQuery<Loan> listQuery = entityManager.createQuery(cq);
        if (currentPage != null && currentPage > 0 && size != null && size > 0) {
            listQuery.setFirstResult((currentPage - 1) * size);
            listQuery.setMaxResults(size);
        }
        List<Loan> results = listQuery.getResultList();

        // --- Count total matches ---
        CriteriaQuery<Long> countCQ = cb.createQuery(Long.class);
        Root<Loan> countRoot = countCQ.from(Loan.class);

        // rebuild predicates on countRoot
        List<Predicate> countPreds = new ArrayList<>();
        countPreds.add(cb.equal(countRoot.get("client").get("id"), clientId));
        if (clientType != null && !clientType.isEmpty()) {
            countPreds.add(cb.equal(
                    countRoot.get("clientType"),
                    Loan.ClientType.valueOf(clientType))
            );
        }
        if (repaymentFrequencyType != null && !repaymentFrequencyType.isEmpty()) {
            countPreds.add(cb.equal(
                    countRoot.get("repaymentFrequencyType"),
                    Loan.RepaymentFrequencyType.valueOf(repaymentFrequencyType))
            );
        }
        if (interestRateType != null && !interestRateType.isEmpty()) {
            countPreds.add(cb.equal(
                    countRoot.get("interestRateType"),
                    Loan.InterestRateType.valueOf(interestRateType))
            );
        }
        if (interestMethodology != null && !interestMethodology.isEmpty()) {
            countPreds.add(cb.equal(
                    countRoot.get("interestMethodology"),
                    Loan.InterestMethodology.valueOf(interestMethodology))
            );
        }
        if (repaymentSchedule != null && !repaymentSchedule.isEmpty()) {
            countPreds.add(cb.equal(countRoot.get("repaymentSchedule"), repaymentSchedule));
        }
        if (status != null && !status.isEmpty()) {
            countPreds.add(cb.equal(
                    countRoot.get("status"),
                    Loan.LoanStatus.valueOf(status))
            );
        }
        if (onboardingStage != null && !onboardingStage.isEmpty()) {
            countPreds.add(cb.equal(countRoot.get("onboardingStage"), onboardingStage));
        }
        if (startDate != null && endDate != null) {
            countPreds.add(cb.between(countRoot.get("createdAt"), startDate, endDate));
        }

        countCQ.select(cb.count(countRoot))
                .where(countPreds.toArray(new Predicate[0]));
        Long totalCount = entityManager.createQuery(countCQ).getSingleResult();
        int totalItems = totalCount.intValue();

        // compute last page
        int lastPage = 1;
        if (size != null && size > 0) {
            lastPage = (int) Math.ceil((double) totalItems / size);
        }

        // wrap and return
        return PagedResult.<Loan>builder()
                .data(results)
                .total(totalItems)
                .lastPage(lastPage)
                .totalPerPage(size)
                .currentPage(currentPage != null ? currentPage : 1)
                .build();
    }



}
