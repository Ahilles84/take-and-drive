package by.it.academy.takeanddrive.repositories;

import by.it.academy.takeanddrive.entities.RentalAgreement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RentalAgreementRepository extends JpaRepository<RentalAgreement, UUID> {
}
