package de.adler.springboot_postgres.database.entity;

import javax.persistence.*;

@Entity
@Table(
        uniqueConstraints =
        @UniqueConstraint(columnNames = {"userid", "role"})
)
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_role_id")
    private Long userroleid;

    @Column(name = "userid")
    private Long userId;

    @Column(name = "role")
    private String role;

    UserRole() {
    }

    public UserRole(Long userId, String role) {
        this.userId = userId;
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserroleid() {
        return userroleid;
    }

    public void setUserroleid(Long userroleid) {
        this.userroleid = userroleid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRole userRole = (UserRole) o;

        return (userroleid != null ? userroleid.equals(userRole.userroleid) : userRole.userroleid == null) && (userId != null ? userId.equals(userRole.userId) : userRole.userId == null) && (role != null ? role.equals(userRole.role) : userRole.role == null);
    }

    @Override
    public int hashCode() {
        int result = userroleid != null ? userroleid.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
}