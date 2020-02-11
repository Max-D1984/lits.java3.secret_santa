package model;

import java.util.Objects;

public class ISantaForListResponse {

    String userName;
    String company;
    String rule;
    String chosenPresent;
    String wishList;
    Boolean status;

    public ISantaForListResponse() {

    }

    public String getUserName() {
        return userName;
    }

    public String getCompany() {
        return company;
    }

    public String getRule() {
        return rule;
    }

    public String getChosenPresent() {
        return chosenPresent;
    }

    public String getWishList() {
        return wishList;
    }

    public Boolean getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "ISantaForListResponse{" +
                "userName='" + userName + '\'' +
                ", company='" + company + '\'' +
                ", rule='" + rule + '\'' +
                ", chosenPresent='" + chosenPresent + '\'' +
                ", wishList='" + wishList + '\'' +
                ", status=" + status +
                '}';
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ISantaForListResponse that = (ISantaForListResponse) o;
        return Objects.equals(userName, that.userName) &&
                Objects.equals(company, that.company) &&
                Objects.equals(rule, that.rule) &&
                Objects.equals(chosenPresent, that.chosenPresent) &&
                Objects.equals(wishList, that.wishList) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, company, rule, chosenPresent, wishList, status);
    }

    public void setChosenPresent(String chosenPresent) {
        this.chosenPresent = chosenPresent;
    }

    public void setWishList(String wishList) {
        this.wishList = wishList;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public ISantaForListResponse(String userName, String company, String rule, String chosenPresent, String wishList, Boolean status) {
        this.userName = userName;
        this.company = company;
        this.rule = rule;
        this.chosenPresent = chosenPresent;
        this.wishList = wishList;
        this.status = status;
    }
}
