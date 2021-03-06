package pojo;

import java.sql.Date;
import java.util.Objects;

public class Rule {
    private int id;
    private int company_id;
    private String description;
    private Date end_date;
    private int gift_price;

    public Rule(int id, int company_id, String description, Date end_date, int gift_price) {
        this.id = id;
        this.company_id = company_id;
        this.description = description;
        this.end_date = end_date;
        this.gift_price = gift_price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rule rule = (Rule) o;
        return id == rule.id &&
                company_id == rule.company_id &&
                gift_price == rule.gift_price &&
                Objects.equals(description, rule.description) &&
                Objects.equals(end_date, rule.end_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, company_id, description, end_date, gift_price);
    }

    @Override
    public String toString() {
        return "Rule{" +
                "id=" + id +
                ", company_id=" + company_id +
                ", description='" + description + '\'' +
                ", end_date=" + end_date +
                ", gift_price=" + gift_price +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public int getGift_price() {
        return gift_price;
    }

    public void setGift_price(int gift_price) {
        this.gift_price = gift_price;
    }

}




