package by.it.academy.takeanddrive.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Builder
@Table(name = "CARS")
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    @Id
    @Column(name = "CAR_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "MODEL")
    private String model;
    @Column(name = "NUMBER")
    private String registrationNumber;
    @Column(name = "STATUS")
    @ColumnDefault("false")
    private boolean status;
    @Column(name = "RENTAL_PRICE")
    private BigDecimal rentalPrice;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    public boolean isBusy() {
        return status;
    }
}
