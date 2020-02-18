package model;

public class TargetUserIdAndCompanyId {
    String user_id;
    String company_id;

    public TargetUserIdAndCompanyId(String user_id, String company_id) {
        this.user_id = user_id;
        this.company_id = company_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }
}
