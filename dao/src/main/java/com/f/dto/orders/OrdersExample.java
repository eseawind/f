package com.f.dto.orders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrdersExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrdersExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andOrderNumIsNull() {
            addCriterion("orderNum is null");
            return (Criteria) this;
        }

        public Criteria andOrderNumIsNotNull() {
            addCriterion("orderNum is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNumEqualTo(String value) {
            addCriterion("orderNum =", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumNotEqualTo(String value) {
            addCriterion("orderNum <>", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumGreaterThan(String value) {
            addCriterion("orderNum >", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumGreaterThanOrEqualTo(String value) {
            addCriterion("orderNum >=", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumLessThan(String value) {
            addCriterion("orderNum <", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumLessThanOrEqualTo(String value) {
            addCriterion("orderNum <=", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumLike(String value) {
            addCriterion("orderNum like", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumNotLike(String value) {
            addCriterion("orderNum not like", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumIn(List<String> values) {
            addCriterion("orderNum in", values, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumNotIn(List<String> values) {
            addCriterion("orderNum not in", values, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumBetween(String value1, String value2) {
            addCriterion("orderNum between", value1, value2, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumNotBetween(String value1, String value2) {
            addCriterion("orderNum not between", value1, value2, "orderNum");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Integer value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Integer value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Integer value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Integer value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Integer value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Integer> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Integer> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Integer value1, Integer value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Integer value1, Integer value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andOrderPriceIsNull() {
            addCriterion("orderPrice is null");
            return (Criteria) this;
        }

        public Criteria andOrderPriceIsNotNull() {
            addCriterion("orderPrice is not null");
            return (Criteria) this;
        }

        public Criteria andOrderPriceEqualTo(BigDecimal value) {
            addCriterion("orderPrice =", value, "orderPrice");
            return (Criteria) this;
        }

        public Criteria andOrderPriceNotEqualTo(BigDecimal value) {
            addCriterion("orderPrice <>", value, "orderPrice");
            return (Criteria) this;
        }

        public Criteria andOrderPriceGreaterThan(BigDecimal value) {
            addCriterion("orderPrice >", value, "orderPrice");
            return (Criteria) this;
        }

        public Criteria andOrderPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("orderPrice >=", value, "orderPrice");
            return (Criteria) this;
        }

        public Criteria andOrderPriceLessThan(BigDecimal value) {
            addCriterion("orderPrice <", value, "orderPrice");
            return (Criteria) this;
        }

        public Criteria andOrderPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("orderPrice <=", value, "orderPrice");
            return (Criteria) this;
        }

        public Criteria andOrderPriceIn(List<BigDecimal> values) {
            addCriterion("orderPrice in", values, "orderPrice");
            return (Criteria) this;
        }

        public Criteria andOrderPriceNotIn(List<BigDecimal> values) {
            addCriterion("orderPrice not in", values, "orderPrice");
            return (Criteria) this;
        }

        public Criteria andOrderPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("orderPrice between", value1, value2, "orderPrice");
            return (Criteria) this;
        }

        public Criteria andOrderPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("orderPrice not between", value1, value2, "orderPrice");
            return (Criteria) this;
        }

        public Criteria andPaidPriceIsNull() {
            addCriterion("paidPrice is null");
            return (Criteria) this;
        }

        public Criteria andPaidPriceIsNotNull() {
            addCriterion("paidPrice is not null");
            return (Criteria) this;
        }

        public Criteria andPaidPriceEqualTo(BigDecimal value) {
            addCriterion("paidPrice =", value, "paidPrice");
            return (Criteria) this;
        }

        public Criteria andPaidPriceNotEqualTo(BigDecimal value) {
            addCriterion("paidPrice <>", value, "paidPrice");
            return (Criteria) this;
        }

        public Criteria andPaidPriceGreaterThan(BigDecimal value) {
            addCriterion("paidPrice >", value, "paidPrice");
            return (Criteria) this;
        }

        public Criteria andPaidPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("paidPrice >=", value, "paidPrice");
            return (Criteria) this;
        }

        public Criteria andPaidPriceLessThan(BigDecimal value) {
            addCriterion("paidPrice <", value, "paidPrice");
            return (Criteria) this;
        }

        public Criteria andPaidPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("paidPrice <=", value, "paidPrice");
            return (Criteria) this;
        }

        public Criteria andPaidPriceIn(List<BigDecimal> values) {
            addCriterion("paidPrice in", values, "paidPrice");
            return (Criteria) this;
        }

        public Criteria andPaidPriceNotIn(List<BigDecimal> values) {
            addCriterion("paidPrice not in", values, "paidPrice");
            return (Criteria) this;
        }

        public Criteria andPaidPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("paidPrice between", value1, value2, "paidPrice");
            return (Criteria) this;
        }

        public Criteria andPaidPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("paidPrice not between", value1, value2, "paidPrice");
            return (Criteria) this;
        }

        public Criteria andPayPriceIsNull() {
            addCriterion("payPrice is null");
            return (Criteria) this;
        }

        public Criteria andPayPriceIsNotNull() {
            addCriterion("payPrice is not null");
            return (Criteria) this;
        }

        public Criteria andPayPriceEqualTo(BigDecimal value) {
            addCriterion("payPrice =", value, "payPrice");
            return (Criteria) this;
        }

        public Criteria andPayPriceNotEqualTo(BigDecimal value) {
            addCriterion("payPrice <>", value, "payPrice");
            return (Criteria) this;
        }

        public Criteria andPayPriceGreaterThan(BigDecimal value) {
            addCriterion("payPrice >", value, "payPrice");
            return (Criteria) this;
        }

        public Criteria andPayPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("payPrice >=", value, "payPrice");
            return (Criteria) this;
        }

        public Criteria andPayPriceLessThan(BigDecimal value) {
            addCriterion("payPrice <", value, "payPrice");
            return (Criteria) this;
        }

        public Criteria andPayPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("payPrice <=", value, "payPrice");
            return (Criteria) this;
        }

        public Criteria andPayPriceIn(List<BigDecimal> values) {
            addCriterion("payPrice in", values, "payPrice");
            return (Criteria) this;
        }

        public Criteria andPayPriceNotIn(List<BigDecimal> values) {
            addCriterion("payPrice not in", values, "payPrice");
            return (Criteria) this;
        }

        public Criteria andPayPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("payPrice between", value1, value2, "payPrice");
            return (Criteria) this;
        }

        public Criteria andPayPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("payPrice not between", value1, value2, "payPrice");
            return (Criteria) this;
        }

        public Criteria andPayTimeIsNull() {
            addCriterion("payTime is null");
            return (Criteria) this;
        }

        public Criteria andPayTimeIsNotNull() {
            addCriterion("payTime is not null");
            return (Criteria) this;
        }

        public Criteria andPayTimeEqualTo(Date value) {
            addCriterion("payTime =", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotEqualTo(Date value) {
            addCriterion("payTime <>", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeGreaterThan(Date value) {
            addCriterion("payTime >", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("payTime >=", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeLessThan(Date value) {
            addCriterion("payTime <", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeLessThanOrEqualTo(Date value) {
            addCriterion("payTime <=", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeIn(List<Date> values) {
            addCriterion("payTime in", values, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotIn(List<Date> values) {
            addCriterion("payTime not in", values, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeBetween(Date value1, Date value2) {
            addCriterion("payTime between", value1, value2, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotBetween(Date value1, Date value2) {
            addCriterion("payTime not between", value1, value2, "payTime");
            return (Criteria) this;
        }

        public Criteria andProductPriceIsNull() {
            addCriterion("productPrice is null");
            return (Criteria) this;
        }

        public Criteria andProductPriceIsNotNull() {
            addCriterion("productPrice is not null");
            return (Criteria) this;
        }

        public Criteria andProductPriceEqualTo(BigDecimal value) {
            addCriterion("productPrice =", value, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceNotEqualTo(BigDecimal value) {
            addCriterion("productPrice <>", value, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceGreaterThan(BigDecimal value) {
            addCriterion("productPrice >", value, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("productPrice >=", value, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceLessThan(BigDecimal value) {
            addCriterion("productPrice <", value, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("productPrice <=", value, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceIn(List<BigDecimal> values) {
            addCriterion("productPrice in", values, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceNotIn(List<BigDecimal> values) {
            addCriterion("productPrice not in", values, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("productPrice between", value1, value2, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("productPrice not between", value1, value2, "productPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceIsNull() {
            addCriterion("discountPrice is null");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceIsNotNull() {
            addCriterion("discountPrice is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceEqualTo(BigDecimal value) {
            addCriterion("discountPrice =", value, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceNotEqualTo(BigDecimal value) {
            addCriterion("discountPrice <>", value, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceGreaterThan(BigDecimal value) {
            addCriterion("discountPrice >", value, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("discountPrice >=", value, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceLessThan(BigDecimal value) {
            addCriterion("discountPrice <", value, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("discountPrice <=", value, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceIn(List<BigDecimal> values) {
            addCriterion("discountPrice in", values, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceNotIn(List<BigDecimal> values) {
            addCriterion("discountPrice not in", values, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("discountPrice between", value1, value2, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("discountPrice not between", value1, value2, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andBalancePriceIsNull() {
            addCriterion("balancePrice is null");
            return (Criteria) this;
        }

        public Criteria andBalancePriceIsNotNull() {
            addCriterion("balancePrice is not null");
            return (Criteria) this;
        }

        public Criteria andBalancePriceEqualTo(BigDecimal value) {
            addCriterion("balancePrice =", value, "balancePrice");
            return (Criteria) this;
        }

        public Criteria andBalancePriceNotEqualTo(BigDecimal value) {
            addCriterion("balancePrice <>", value, "balancePrice");
            return (Criteria) this;
        }

        public Criteria andBalancePriceGreaterThan(BigDecimal value) {
            addCriterion("balancePrice >", value, "balancePrice");
            return (Criteria) this;
        }

        public Criteria andBalancePriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("balancePrice >=", value, "balancePrice");
            return (Criteria) this;
        }

        public Criteria andBalancePriceLessThan(BigDecimal value) {
            addCriterion("balancePrice <", value, "balancePrice");
            return (Criteria) this;
        }

        public Criteria andBalancePriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("balancePrice <=", value, "balancePrice");
            return (Criteria) this;
        }

        public Criteria andBalancePriceIn(List<BigDecimal> values) {
            addCriterion("balancePrice in", values, "balancePrice");
            return (Criteria) this;
        }

        public Criteria andBalancePriceNotIn(List<BigDecimal> values) {
            addCriterion("balancePrice not in", values, "balancePrice");
            return (Criteria) this;
        }

        public Criteria andBalancePriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("balancePrice between", value1, value2, "balancePrice");
            return (Criteria) this;
        }

        public Criteria andBalancePriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("balancePrice not between", value1, value2, "balancePrice");
            return (Criteria) this;
        }

        public Criteria andIsPaidIsNull() {
            addCriterion("isPaid is null");
            return (Criteria) this;
        }

        public Criteria andIsPaidIsNotNull() {
            addCriterion("isPaid is not null");
            return (Criteria) this;
        }

        public Criteria andIsPaidEqualTo(Integer value) {
            addCriterion("isPaid =", value, "isPaid");
            return (Criteria) this;
        }

        public Criteria andIsPaidNotEqualTo(Integer value) {
            addCriterion("isPaid <>", value, "isPaid");
            return (Criteria) this;
        }

        public Criteria andIsPaidGreaterThan(Integer value) {
            addCriterion("isPaid >", value, "isPaid");
            return (Criteria) this;
        }

        public Criteria andIsPaidGreaterThanOrEqualTo(Integer value) {
            addCriterion("isPaid >=", value, "isPaid");
            return (Criteria) this;
        }

        public Criteria andIsPaidLessThan(Integer value) {
            addCriterion("isPaid <", value, "isPaid");
            return (Criteria) this;
        }

        public Criteria andIsPaidLessThanOrEqualTo(Integer value) {
            addCriterion("isPaid <=", value, "isPaid");
            return (Criteria) this;
        }

        public Criteria andIsPaidIn(List<Integer> values) {
            addCriterion("isPaid in", values, "isPaid");
            return (Criteria) this;
        }

        public Criteria andIsPaidNotIn(List<Integer> values) {
            addCriterion("isPaid not in", values, "isPaid");
            return (Criteria) this;
        }

        public Criteria andIsPaidBetween(Integer value1, Integer value2) {
            addCriterion("isPaid between", value1, value2, "isPaid");
            return (Criteria) this;
        }

        public Criteria andIsPaidNotBetween(Integer value1, Integer value2) {
            addCriterion("isPaid not between", value1, value2, "isPaid");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("userId is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("userId is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Long value) {
            addCriterion("userId =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Long value) {
            addCriterion("userId <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Long value) {
            addCriterion("userId >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("userId >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Long value) {
            addCriterion("userId <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("userId <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Long> values) {
            addCriterion("userId in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Long> values) {
            addCriterion("userId not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("userId between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("userId not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createtime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createtime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createtime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createtime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createtime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createtime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createtime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createtime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createtime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createtime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createtime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("createtime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andMerchantIdIsNull() {
            addCriterion("merchantId is null");
            return (Criteria) this;
        }

        public Criteria andMerchantIdIsNotNull() {
            addCriterion("merchantId is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantIdEqualTo(Long value) {
            addCriterion("merchantId =", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdNotEqualTo(Long value) {
            addCriterion("merchantId <>", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdGreaterThan(Long value) {
            addCriterion("merchantId >", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdGreaterThanOrEqualTo(Long value) {
            addCriterion("merchantId >=", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdLessThan(Long value) {
            addCriterion("merchantId <", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdLessThanOrEqualTo(Long value) {
            addCriterion("merchantId <=", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdIn(List<Long> values) {
            addCriterion("merchantId in", values, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdNotIn(List<Long> values) {
            addCriterion("merchantId not in", values, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdBetween(Long value1, Long value2) {
            addCriterion("merchantId between", value1, value2, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdNotBetween(Long value1, Long value2) {
            addCriterion("merchantId not between", value1, value2, "merchantId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdIsNull() {
            addCriterion("provinceId is null");
            return (Criteria) this;
        }

        public Criteria andProvinceIdIsNotNull() {
            addCriterion("provinceId is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceIdEqualTo(Integer value) {
            addCriterion("provinceId =", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdNotEqualTo(Integer value) {
            addCriterion("provinceId <>", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdGreaterThan(Integer value) {
            addCriterion("provinceId >", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("provinceId >=", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdLessThan(Integer value) {
            addCriterion("provinceId <", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdLessThanOrEqualTo(Integer value) {
            addCriterion("provinceId <=", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdIn(List<Integer> values) {
            addCriterion("provinceId in", values, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdNotIn(List<Integer> values) {
            addCriterion("provinceId not in", values, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdBetween(Integer value1, Integer value2) {
            addCriterion("provinceId between", value1, value2, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdNotBetween(Integer value1, Integer value2) {
            addCriterion("provinceId not between", value1, value2, "provinceId");
            return (Criteria) this;
        }

        public Criteria andCityIdIsNull() {
            addCriterion("cityId is null");
            return (Criteria) this;
        }

        public Criteria andCityIdIsNotNull() {
            addCriterion("cityId is not null");
            return (Criteria) this;
        }

        public Criteria andCityIdEqualTo(Integer value) {
            addCriterion("cityId =", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotEqualTo(Integer value) {
            addCriterion("cityId <>", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdGreaterThan(Integer value) {
            addCriterion("cityId >", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("cityId >=", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdLessThan(Integer value) {
            addCriterion("cityId <", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdLessThanOrEqualTo(Integer value) {
            addCriterion("cityId <=", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdIn(List<Integer> values) {
            addCriterion("cityId in", values, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotIn(List<Integer> values) {
            addCriterion("cityId not in", values, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdBetween(Integer value1, Integer value2) {
            addCriterion("cityId between", value1, value2, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotBetween(Integer value1, Integer value2) {
            addCriterion("cityId not between", value1, value2, "cityId");
            return (Criteria) this;
        }

        public Criteria andAreaIdIsNull() {
            addCriterion("areaId is null");
            return (Criteria) this;
        }

        public Criteria andAreaIdIsNotNull() {
            addCriterion("areaId is not null");
            return (Criteria) this;
        }

        public Criteria andAreaIdEqualTo(Integer value) {
            addCriterion("areaId =", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotEqualTo(Integer value) {
            addCriterion("areaId <>", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdGreaterThan(Integer value) {
            addCriterion("areaId >", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("areaId >=", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdLessThan(Integer value) {
            addCriterion("areaId <", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdLessThanOrEqualTo(Integer value) {
            addCriterion("areaId <=", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdIn(List<Integer> values) {
            addCriterion("areaId in", values, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotIn(List<Integer> values) {
            addCriterion("areaId not in", values, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdBetween(Integer value1, Integer value2) {
            addCriterion("areaId between", value1, value2, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotBetween(Integer value1, Integer value2) {
            addCriterion("areaId not between", value1, value2, "areaId");
            return (Criteria) this;
        }

        public Criteria andProvinceNameIsNull() {
            addCriterion("provinceName is null");
            return (Criteria) this;
        }

        public Criteria andProvinceNameIsNotNull() {
            addCriterion("provinceName is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceNameEqualTo(String value) {
            addCriterion("provinceName =", value, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameNotEqualTo(String value) {
            addCriterion("provinceName <>", value, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameGreaterThan(String value) {
            addCriterion("provinceName >", value, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameGreaterThanOrEqualTo(String value) {
            addCriterion("provinceName >=", value, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameLessThan(String value) {
            addCriterion("provinceName <", value, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameLessThanOrEqualTo(String value) {
            addCriterion("provinceName <=", value, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameLike(String value) {
            addCriterion("provinceName like", value, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameNotLike(String value) {
            addCriterion("provinceName not like", value, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameIn(List<String> values) {
            addCriterion("provinceName in", values, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameNotIn(List<String> values) {
            addCriterion("provinceName not in", values, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameBetween(String value1, String value2) {
            addCriterion("provinceName between", value1, value2, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameNotBetween(String value1, String value2) {
            addCriterion("provinceName not between", value1, value2, "provinceName");
            return (Criteria) this;
        }

        public Criteria andCityNameIsNull() {
            addCriterion("cityName is null");
            return (Criteria) this;
        }

        public Criteria andCityNameIsNotNull() {
            addCriterion("cityName is not null");
            return (Criteria) this;
        }

        public Criteria andCityNameEqualTo(String value) {
            addCriterion("cityName =", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotEqualTo(String value) {
            addCriterion("cityName <>", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameGreaterThan(String value) {
            addCriterion("cityName >", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameGreaterThanOrEqualTo(String value) {
            addCriterion("cityName >=", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameLessThan(String value) {
            addCriterion("cityName <", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameLessThanOrEqualTo(String value) {
            addCriterion("cityName <=", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameLike(String value) {
            addCriterion("cityName like", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotLike(String value) {
            addCriterion("cityName not like", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameIn(List<String> values) {
            addCriterion("cityName in", values, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotIn(List<String> values) {
            addCriterion("cityName not in", values, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameBetween(String value1, String value2) {
            addCriterion("cityName between", value1, value2, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotBetween(String value1, String value2) {
            addCriterion("cityName not between", value1, value2, "cityName");
            return (Criteria) this;
        }

        public Criteria andAreaNameIsNull() {
            addCriterion("areaName is null");
            return (Criteria) this;
        }

        public Criteria andAreaNameIsNotNull() {
            addCriterion("areaName is not null");
            return (Criteria) this;
        }

        public Criteria andAreaNameEqualTo(String value) {
            addCriterion("areaName =", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameNotEqualTo(String value) {
            addCriterion("areaName <>", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameGreaterThan(String value) {
            addCriterion("areaName >", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameGreaterThanOrEqualTo(String value) {
            addCriterion("areaName >=", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameLessThan(String value) {
            addCriterion("areaName <", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameLessThanOrEqualTo(String value) {
            addCriterion("areaName <=", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameLike(String value) {
            addCriterion("areaName like", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameNotLike(String value) {
            addCriterion("areaName not like", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameIn(List<String> values) {
            addCriterion("areaName in", values, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameNotIn(List<String> values) {
            addCriterion("areaName not in", values, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameBetween(String value1, String value2) {
            addCriterion("areaName between", value1, value2, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameNotBetween(String value1, String value2) {
            addCriterion("areaName not between", value1, value2, "areaName");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andConsigneeIsNull() {
            addCriterion("consignee is null");
            return (Criteria) this;
        }

        public Criteria andConsigneeIsNotNull() {
            addCriterion("consignee is not null");
            return (Criteria) this;
        }

        public Criteria andConsigneeEqualTo(String value) {
            addCriterion("consignee =", value, "consignee");
            return (Criteria) this;
        }

        public Criteria andConsigneeNotEqualTo(String value) {
            addCriterion("consignee <>", value, "consignee");
            return (Criteria) this;
        }

        public Criteria andConsigneeGreaterThan(String value) {
            addCriterion("consignee >", value, "consignee");
            return (Criteria) this;
        }

        public Criteria andConsigneeGreaterThanOrEqualTo(String value) {
            addCriterion("consignee >=", value, "consignee");
            return (Criteria) this;
        }

        public Criteria andConsigneeLessThan(String value) {
            addCriterion("consignee <", value, "consignee");
            return (Criteria) this;
        }

        public Criteria andConsigneeLessThanOrEqualTo(String value) {
            addCriterion("consignee <=", value, "consignee");
            return (Criteria) this;
        }

        public Criteria andConsigneeLike(String value) {
            addCriterion("consignee like", value, "consignee");
            return (Criteria) this;
        }

        public Criteria andConsigneeNotLike(String value) {
            addCriterion("consignee not like", value, "consignee");
            return (Criteria) this;
        }

        public Criteria andConsigneeIn(List<String> values) {
            addCriterion("consignee in", values, "consignee");
            return (Criteria) this;
        }

        public Criteria andConsigneeNotIn(List<String> values) {
            addCriterion("consignee not in", values, "consignee");
            return (Criteria) this;
        }

        public Criteria andConsigneeBetween(String value1, String value2) {
            addCriterion("consignee between", value1, value2, "consignee");
            return (Criteria) this;
        }

        public Criteria andConsigneeNotBetween(String value1, String value2) {
            addCriterion("consignee not between", value1, value2, "consignee");
            return (Criteria) this;
        }

        public Criteria andMobileIsNull() {
            addCriterion("mobile is null");
            return (Criteria) this;
        }

        public Criteria andMobileIsNotNull() {
            addCriterion("mobile is not null");
            return (Criteria) this;
        }

        public Criteria andMobileEqualTo(String value) {
            addCriterion("mobile =", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotEqualTo(String value) {
            addCriterion("mobile <>", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThan(String value) {
            addCriterion("mobile >", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThanOrEqualTo(String value) {
            addCriterion("mobile >=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThan(String value) {
            addCriterion("mobile <", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThanOrEqualTo(String value) {
            addCriterion("mobile <=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLike(String value) {
            addCriterion("mobile like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotLike(String value) {
            addCriterion("mobile not like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileIn(List<String> values) {
            addCriterion("mobile in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotIn(List<String> values) {
            addCriterion("mobile not in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileBetween(String value1, String value2) {
            addCriterion("mobile between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotBetween(String value1, String value2) {
            addCriterion("mobile not between", value1, value2, "mobile");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}