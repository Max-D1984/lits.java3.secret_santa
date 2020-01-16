package pojo;

import Records.RulesRecords;

import java.util.Objects;

public class Rule {
    private int id;
    private int company_id;
    private String start_date;
    private String end_date;
    private String gift_date;
    private int gift_price;


    public Rule(int id, int company_id, String start_date, String end_date, String gift_date, int gift_price) {
        this.id = id;
        this.company_id = company_id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.gift_date = gift_date;
        this.gift_price = gift_price;
    }

    @Override
    public String toString() {
        return "RulesRecords{" +
                "id=" + id +
                ", company_id=" + company_id +
                ", start_date='" + start_date + '\'' +
                ", end_date='" + end_date + '\'' +
                ", gift_date='" + gift_date + '\'' +
                ", gift_price=" + gift_price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rule that = (Rule) o;
        return id == that.id &&
                company_id == that.company_id &&
                gift_price == that.gift_price &&
                Objects.equals(start_date, that.start_date) &&
                Objects.equals(end_date, that.end_date) &&
                Objects.equals(gift_date, that.gift_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, company_id, start_date, end_date, gift_date, gift_price);
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

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getGift_date() {
        return gift_date;
    }

    public void setGift_date(String gift_date) {
        this.gift_date = gift_date;
    }

    public int getGift_price() {
        return gift_price;
    }

    public void setGift_price(int gift_price) {
        this.gift_price = gift_price;
    }
}

