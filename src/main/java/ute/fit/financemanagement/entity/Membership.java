package ute.fit.financemanagement.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ute.fit.financemanagement.enums.FamilyMemberRole;
import ute.fit.financemanagement.enums.FamilyPermission;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"personId", "familyId"}))
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long membershipId;

    @Column(nullable = false)
    private Long personId;

    @Column(nullable = false)
    private Long familyId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FamilyMemberRole familyMemberRole;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(joinColumns = @JoinColumn(name = "membershipId"))
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private List<FamilyPermission> permissions = new ArrayList<>();

    public void changeFamilyMemberRole(FamilyMemberRole newRole) {
        this.familyMemberRole = newRole;
    }

    public void adjustPermission(List<FamilyPermission> newPermissions) {
        this.permissions.clear();
        this.permissions.addAll(newPermissions);
    }

    public boolean isSuspended() {
        return familyMemberRole == FamilyMemberRole.MEMBER && permissions.isEmpty();
    }
}
