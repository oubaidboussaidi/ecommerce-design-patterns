package com.exemple.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "email", nullable = false, unique = true)
    private String login;

    @Column(name = "password", nullable = false)
    private String motdepasse;

    @Column(name = "role", nullable = false)
    private String role;

    public User() {}

    public User(String login, String motdepasse, String role) {
        this.login = login;
        this.motdepasse = motdepasse;
        this.role = role;
    }
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


}
