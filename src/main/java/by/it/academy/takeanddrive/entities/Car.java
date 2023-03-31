package by.it.academy.takeanddrive.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Table(name = "CARS")
@DynamicInsert
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    @Id
    @Column(name = "CAR_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "MODEL")
    private String model;
    @Column(name = "NUMBER")
    private String registrationNumber;
    @Column(name = "STATUS")
    @ColumnDefault("false")
    private boolean status;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    public Car(String model, String registrationNumber, User user) {
        this.model = model;
        this.registrationNumber = registrationNumber;
        this.user = user;
    }

    public Car(String model, String registrationNumber) {
        this.model = model;
        this.registrationNumber = registrationNumber;
    }

    public boolean isBusy() {
        return status;
    }
}
