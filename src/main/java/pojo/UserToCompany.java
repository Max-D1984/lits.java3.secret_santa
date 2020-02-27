package pojo;

import javax.persistence.*;
import java.util.Objects;

/*
Перелік JPA-анотацій (Java Persistence API) для реалізації ORM(Object-Relational Mapping) технології:

@Entity - вказує, що наш клас є певною сутністю

@Table - вказує на конкретну таблицю з БД для відображення відповідної сутності(в параметрі для поля "name"
вказуєм ту назву таблиці, яка вказана в БД

@Id - вказує, що це поле є первинним ключем (primary key), тобто це поле буде використовуватись
для ідентифікації кожного унікального запису в БД

@Column - вказує на звязок між полем нашого pojo-класу і відповідним стовбцем таблиц в БД. Якщо назва поля і
стовбця співпадають - анотацію можна не вказувати (рекомендую всерівно вказувати, щоб не було конфліктів).
В параметрі для поля "name" вказуєм ту назву, яка вказана для відповідного стовбця в БД.

@GeneratedValue - відповідає за автоінкрементацію (конкретно в нашому випадку:
                  збільшення значення поля id на 1 із кожним новим записом в БД)

* Потрібно створити НЕприватний конструктор без параметрів

 */

@Entity
@Table(name = "user_to_company")
public class UserToCompany {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id")
    private int user_id;

    @Column(name = "company_id")
    private int company_id;

    @Column(name = "role")
    private String role;

    //НЕприватний конструктор без параметрів
    public UserToCompany() {
    }

    public UserToCompany(int id, int user_id, int company_id, String role) {
        this.id = id;
        this.user_id = user_id;
        this.company_id = company_id;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserToCompany{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", company_id=" + company_id +
                ", role='" + role + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserToCompany that = (UserToCompany) o;
        return id == that.id &&
                user_id == that.user_id &&
                company_id == that.company_id &&
                Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user_id, company_id, role);
    }


}
