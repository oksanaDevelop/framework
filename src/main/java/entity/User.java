package entity;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

    public User() {
    }

    public User(String name, String companyId) {
        this.name = name;
        this.companyId = companyId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private Long id;

    private String name;

    @Column(name = "companyid")
    private String companyId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", companyId='" + companyId + '\'' +
                '}';
    }
}
