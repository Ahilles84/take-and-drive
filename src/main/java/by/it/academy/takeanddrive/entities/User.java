package by.it.academy.takeanddrive.entities;

import by.it.academy.takeanddrive.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Data
@Entity
@Builder
@Table(name = "USERS")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Integer id;
    @Column(name = "FIRSTNAME", nullable = false, length = 50)
    private String firstName;
    @Column(name = "LASTNAME", nullable = false, length = 50)
    private String lastName;
    @Column(name = "AGE", nullable = false)
    private Integer age;
    @Column(name = "LOGIN", nullable = false, length = 100)
    private String login;
    @Column(name = "PASSWORD", nullable = false, length = 100)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE")
    @ColumnDefault("USER")
    private Role role;
}
