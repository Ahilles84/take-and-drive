package by.it.academy.takeanddrive.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@Entity
@Table(name = "RENTAL_AGREEMENTS")
@NoArgsConstructor
@AllArgsConstructor
public class RentalAgreement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "AGREEMENT_ID")
    private UUID agreementId;
    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;
    @OneToOne
    @JoinColumn(name = "CAR_ID")
    private Car car;
    @Column(name = "RENTAL_START")
    private LocalDate rentalStart;
    @Column(name = "RENTAL_END")
    private LocalDate rentalEnd;
    @Column(name = "RENTAL_COST")
    private BigDecimal rentalCost;
}
