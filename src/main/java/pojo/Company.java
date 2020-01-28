package pojo;

import java.util.Objects;

public class Company {

    private long id;
    private String companyName;
    private String companyDescription;


    public Company(int id, String companyName, String companyDescription) {
        this.id = id;
        this.companyName = companyName;
        this.companyDescription = companyDescription;
    }

    public long getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyDescription() {
        return companyDescription;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company that = (Company) o;
        return id == that.id &&
                Objects.equals(companyName, that.companyName) &&
                Objects.equals(companyDescription, that.companyDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, companyName, companyDescription);
    }

    @Override
    public String toString() {
        return "Company_records{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", companyDescription='" + companyDescription + '\'' +
                '}';
    }
}
