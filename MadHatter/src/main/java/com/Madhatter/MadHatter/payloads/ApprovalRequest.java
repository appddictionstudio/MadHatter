package com.Madhatter.MadHatter.payloads;

public class ApprovalRequest {

    private String name;
    private String approvingAuthority;
    private String decision;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the approvingAuthority
     */
    public String getApprovingAuthority() {
        return approvingAuthority;
    }

    /**
     * @param approvingAuthority the approvingAuthority to set
     */
    public void setApprovingAuthority(String approvingAuthority) {
        this.approvingAuthority = approvingAuthority;
    }

    /**
     * @return the decision
     */
    public String getDecision() {
        return decision;
    }

    /**
     * @param decision the decision to set
     */
    public void setDecision(String decision) {
        this.decision = decision;
    }
}