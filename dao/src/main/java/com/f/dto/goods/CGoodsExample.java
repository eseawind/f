package com.f.dto.goods;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CGoodsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CGoodsExample() {
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

        public Criteria andGidIsNull() {
            addCriterion("gid is null");
            return (Criteria) this;
        }

        public Criteria andGidIsNotNull() {
            addCriterion("gid is not null");
            return (Criteria) this;
        }

        public Criteria andGidEqualTo(Long value) {
            addCriterion("gid =", value, "gid");
            return (Criteria) this;
        }

        public Criteria andGidNotEqualTo(Long value) {
            addCriterion("gid <>", value, "gid");
            return (Criteria) this;
        }

        public Criteria andGidGreaterThan(Long value) {
            addCriterion("gid >", value, "gid");
            return (Criteria) this;
        }

        public Criteria andGidGreaterThanOrEqualTo(Long value) {
            addCriterion("gid >=", value, "gid");
            return (Criteria) this;
        }

        public Criteria andGidLessThan(Long value) {
            addCriterion("gid <", value, "gid");
            return (Criteria) this;
        }

        public Criteria andGidLessThanOrEqualTo(Long value) {
            addCriterion("gid <=", value, "gid");
            return (Criteria) this;
        }

        public Criteria andGidIn(List<Long> values) {
            addCriterion("gid in", values, "gid");
            return (Criteria) this;
        }

        public Criteria andGidNotIn(List<Long> values) {
            addCriterion("gid not in", values, "gid");
            return (Criteria) this;
        }

        public Criteria andGidBetween(Long value1, Long value2) {
            addCriterion("gid between", value1, value2, "gid");
            return (Criteria) this;
        }

        public Criteria andGidNotBetween(Long value1, Long value2) {
            addCriterion("gid not between", value1, value2, "gid");
            return (Criteria) this;
        }

        public Criteria andCgnameIsNull() {
            addCriterion("cgname is null");
            return (Criteria) this;
        }

        public Criteria andCgnameIsNotNull() {
            addCriterion("cgname is not null");
            return (Criteria) this;
        }

        public Criteria andCgnameEqualTo(String value) {
            addCriterion("cgname =", value, "cgname");
            return (Criteria) this;
        }

        public Criteria andCgnameNotEqualTo(String value) {
            addCriterion("cgname <>", value, "cgname");
            return (Criteria) this;
        }

        public Criteria andCgnameGreaterThan(String value) {
            addCriterion("cgname >", value, "cgname");
            return (Criteria) this;
        }

        public Criteria andCgnameGreaterThanOrEqualTo(String value) {
            addCriterion("cgname >=", value, "cgname");
            return (Criteria) this;
        }

        public Criteria andCgnameLessThan(String value) {
            addCriterion("cgname <", value, "cgname");
            return (Criteria) this;
        }

        public Criteria andCgnameLessThanOrEqualTo(String value) {
            addCriterion("cgname <=", value, "cgname");
            return (Criteria) this;
        }

        public Criteria andCgnameLike(String value) {
            addCriterion("cgname like", value, "cgname");
            return (Criteria) this;
        }

        public Criteria andCgnameNotLike(String value) {
            addCriterion("cgname not like", value, "cgname");
            return (Criteria) this;
        }

        public Criteria andCgnameIn(List<String> values) {
            addCriterion("cgname in", values, "cgname");
            return (Criteria) this;
        }

        public Criteria andCgnameNotIn(List<String> values) {
            addCriterion("cgname not in", values, "cgname");
            return (Criteria) this;
        }

        public Criteria andCgnameBetween(String value1, String value2) {
            addCriterion("cgname between", value1, value2, "cgname");
            return (Criteria) this;
        }

        public Criteria andCgnameNotBetween(String value1, String value2) {
            addCriterion("cgname not between", value1, value2, "cgname");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(BigDecimal value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(BigDecimal value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(BigDecimal value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(BigDecimal value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<BigDecimal> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<BigDecimal> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andMpriceIsNull() {
            addCriterion("mprice is null");
            return (Criteria) this;
        }

        public Criteria andMpriceIsNotNull() {
            addCriterion("mprice is not null");
            return (Criteria) this;
        }

        public Criteria andMpriceEqualTo(BigDecimal value) {
            addCriterion("mprice =", value, "mprice");
            return (Criteria) this;
        }

        public Criteria andMpriceNotEqualTo(BigDecimal value) {
            addCriterion("mprice <>", value, "mprice");
            return (Criteria) this;
        }

        public Criteria andMpriceGreaterThan(BigDecimal value) {
            addCriterion("mprice >", value, "mprice");
            return (Criteria) this;
        }

        public Criteria andMpriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("mprice >=", value, "mprice");
            return (Criteria) this;
        }

        public Criteria andMpriceLessThan(BigDecimal value) {
            addCriterion("mprice <", value, "mprice");
            return (Criteria) this;
        }

        public Criteria andMpriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("mprice <=", value, "mprice");
            return (Criteria) this;
        }

        public Criteria andMpriceIn(List<BigDecimal> values) {
            addCriterion("mprice in", values, "mprice");
            return (Criteria) this;
        }

        public Criteria andMpriceNotIn(List<BigDecimal> values) {
            addCriterion("mprice not in", values, "mprice");
            return (Criteria) this;
        }

        public Criteria andMpriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("mprice between", value1, value2, "mprice");
            return (Criteria) this;
        }

        public Criteria andMpriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("mprice not between", value1, value2, "mprice");
            return (Criteria) this;
        }

        public Criteria andApriceIsNull() {
            addCriterion("aprice is null");
            return (Criteria) this;
        }

        public Criteria andApriceIsNotNull() {
            addCriterion("aprice is not null");
            return (Criteria) this;
        }

        public Criteria andApriceEqualTo(BigDecimal value) {
            addCriterion("aprice =", value, "aprice");
            return (Criteria) this;
        }

        public Criteria andApriceNotEqualTo(BigDecimal value) {
            addCriterion("aprice <>", value, "aprice");
            return (Criteria) this;
        }

        public Criteria andApriceGreaterThan(BigDecimal value) {
            addCriterion("aprice >", value, "aprice");
            return (Criteria) this;
        }

        public Criteria andApriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("aprice >=", value, "aprice");
            return (Criteria) this;
        }

        public Criteria andApriceLessThan(BigDecimal value) {
            addCriterion("aprice <", value, "aprice");
            return (Criteria) this;
        }

        public Criteria andApriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("aprice <=", value, "aprice");
            return (Criteria) this;
        }

        public Criteria andApriceIn(List<BigDecimal> values) {
            addCriterion("aprice in", values, "aprice");
            return (Criteria) this;
        }

        public Criteria andApriceNotIn(List<BigDecimal> values) {
            addCriterion("aprice not in", values, "aprice");
            return (Criteria) this;
        }

        public Criteria andApriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("aprice between", value1, value2, "aprice");
            return (Criteria) this;
        }

        public Criteria andApriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("aprice not between", value1, value2, "aprice");
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

        public Criteria andIsDefIsNull() {
            addCriterion("isDef is null");
            return (Criteria) this;
        }

        public Criteria andIsDefIsNotNull() {
            addCriterion("isDef is not null");
            return (Criteria) this;
        }

        public Criteria andIsDefEqualTo(Integer value) {
            addCriterion("isDef =", value, "isDef");
            return (Criteria) this;
        }

        public Criteria andIsDefNotEqualTo(Integer value) {
            addCriterion("isDef <>", value, "isDef");
            return (Criteria) this;
        }

        public Criteria andIsDefGreaterThan(Integer value) {
            addCriterion("isDef >", value, "isDef");
            return (Criteria) this;
        }

        public Criteria andIsDefGreaterThanOrEqualTo(Integer value) {
            addCriterion("isDef >=", value, "isDef");
            return (Criteria) this;
        }

        public Criteria andIsDefLessThan(Integer value) {
            addCriterion("isDef <", value, "isDef");
            return (Criteria) this;
        }

        public Criteria andIsDefLessThanOrEqualTo(Integer value) {
            addCriterion("isDef <=", value, "isDef");
            return (Criteria) this;
        }

        public Criteria andIsDefIn(List<Integer> values) {
            addCriterion("isDef in", values, "isDef");
            return (Criteria) this;
        }

        public Criteria andIsDefNotIn(List<Integer> values) {
            addCriterion("isDef not in", values, "isDef");
            return (Criteria) this;
        }

        public Criteria andIsDefBetween(Integer value1, Integer value2) {
            addCriterion("isDef between", value1, value2, "isDef");
            return (Criteria) this;
        }

        public Criteria andIsDefNotBetween(Integer value1, Integer value2) {
            addCriterion("isDef not between", value1, value2, "isDef");
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