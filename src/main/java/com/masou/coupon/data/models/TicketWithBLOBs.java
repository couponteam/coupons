package com.masou.coupon.data.models;

public class TicketWithBLOBs extends Ticket {
    private String applicableScope;

    private String workingCondition;

    private String validityComment;

    public String getApplicableScope() {
        return applicableScope;
    }

    public void setApplicableScope(String applicableScope) {
        this.applicableScope = applicableScope == null ? null : applicableScope.trim();
    }

    public String getWorkingCondition() {
        return workingCondition;
    }

    public void setWorkingCondition(String workingCondition) {
        this.workingCondition = workingCondition == null ? null : workingCondition.trim();
    }

    public String getValidityComment() {
        return validityComment;
    }

    public void setValidityComment(String validityComment) {
        this.validityComment = validityComment;
    }
}