package model;

import java.util.Objects;

public class CompanyInformationResponse {
    String name;
    String targetUser;
    String statusChoose;
    String statusBuy;
    String remote;
    String admin;

    public CompanyInformationResponse () {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTargetUser() {
        return targetUser;
    }

    public void setTargetUser(String targetUser) {
        this.targetUser = targetUser;
    }

    public String getStatusChoose() {
        return statusChoose;
    }

    public void setStatusChoose(String statusChoose) {
        this.statusChoose = statusChoose;
    }

    public String getStatusBuy() {
        return statusBuy;
    }

    public void setStatusBuy(String statusBuy) {
        this.statusBuy = statusBuy;
    }

    public String getRemote() {
        return remote;
    }

    public void setRemote(String remote) {
        this.remote = remote;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "CompanyInformationResponse{" +
                "name='" + name + '\'' +
                ", targetUser='" + targetUser + '\'' +
                ", statusChoose='" + statusChoose + '\'' +
                ", statusBuy='" + statusBuy + '\'' +
                ", remote='" + remote + '\'' +
                ", admin='" + admin + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyInformationResponse that = (CompanyInformationResponse) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(targetUser, that.targetUser) &&
                Objects.equals(statusChoose, that.statusChoose) &&
                Objects.equals(statusBuy, that.statusBuy) &&
                Objects.equals(remote, that.remote) &&
                Objects.equals(admin, that.admin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, targetUser, statusChoose, statusBuy, remote, admin);
    }
}


