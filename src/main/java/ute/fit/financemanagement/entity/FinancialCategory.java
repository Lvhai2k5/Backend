package ute.fit.financemanagement.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ute.fit.financemanagement.enums.CategoryStatus;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FinancialCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long financialCategoryId;

    @Column(nullable = false)
    private Long familyId;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, precision = 15, scale = 3)
    private BigDecimal initialBalance;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoryStatus categoryStatus;

    @Column(nullable = false, precision = 15, scale = 3)
    private BigDecimal amountOfWarning;

    @Column(nullable = false, precision = 15, scale = 3)
    private BigDecimal amountOfEmergency;

    @Column(nullable = false, precision = 15, scale = 3)
    private BigDecimal amountOfApproval;

    public void lockCategory() {
        this.categoryStatus = CategoryStatus.LOCKED;
    }

    public void unlockCategory() {
        this.categoryStatus = CategoryStatus.ACTIVE;
    }

    public void modifyThreshold(BigDecimal amountOfWarning, BigDecimal amountOfEmergency,
                                BigDecimal amountOfApproval) {
        this.amountOfWarning = amountOfWarning;
        this.amountOfEmergency = amountOfEmergency;
        this.amountOfApproval = amountOfApproval;
    }
}
