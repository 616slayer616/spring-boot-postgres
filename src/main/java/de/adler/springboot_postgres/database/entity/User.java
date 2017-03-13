package de.adler.springboot_postgres.database.entity;

import javax.persistence.*;


@Entity
public class User {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private Long userId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "enabled")
    private boolean enabled;

    User() {

    }

    public User(User user) {
        this.userId = user.userId;
        this.username = user.username;
        this.email = user.email;
        this.password = user.password;
        this.enabled = user.enabled;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Long getUserid() {
        return userId;
    }

    public void setUserid(Long userid) {
        this.userId = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}