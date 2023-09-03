package com.claudiu.proiect.domain;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "personalulSectiei")
public class PersonalMedicalSectie{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;

    public PersonalMedicalSectie() {
    }

    public PersonalMedicalSectie(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public PersonalMedicalSectie(int id,String username, String password) {
        this.id=id;
        this.username = username;
        this.password = password;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonalMedicalSectie that = (PersonalMedicalSectie) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "PersonalMedicalSectie{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
