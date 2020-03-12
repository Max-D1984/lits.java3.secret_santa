package pojo;

import java.util.Arrays;
import java.util.Objects;

public class Company {

    private int id;
    private String companyName;
    private String companyDescription;
    private byte[] image;

    public Company(int id, String companyName, String companyDescription, byte[] image) {
        this.id = id;
        this.companyName = companyName;
        this.companyDescription = companyDescription;
        this.image = image;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", companyDescription='" + companyDescription + '\'' +
                ", image=" + Arrays.toString(image) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return id == company.id &&
                Objects.equals(companyName, company.companyName) &&
                Objects.equals(companyDescription, company.companyDescription) &&
                Arrays.equals(image, company.image);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, companyName, companyDescription);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyDescription() {
        return companyDescription;
    }

    public void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }



}
