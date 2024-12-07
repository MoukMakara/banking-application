package co.istad.bankingapplication.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String uuid;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    private String password;

    @Column(name = "phone_number", nullable = false, unique = true, length = 10)
    private String phoneNumber;

    @Column(nullable = false, length = 4)
    private String pin;

    @Column(nullable = false, length = 7)
    private String gender;

    @Column(name = "monthly_income_range", precision = 19, scale = 2)
    private BigDecimal monthlyIncomeRange;

    @Column(name = "student_card_id", nullable = false, unique = true, length = 20)
    private String studentCardId;

    @Column(name = "national_card_id", nullable = false, unique = true, length = 20)
    private String nationalCardId;

    private String village;
    private String street;

    @Column(name = "sangkat_or_commune")
    private String sangkatOrCommune;

    @Column(name = "profile_image")
    private String profileImage;

    @Column(name = "main_source_of_income")
    private String mainSourceOfIncome;

    private String position;

    @Column(name = "employee_type")
    private String employeeType;

    @Column(name = "khan_or_district")
    private String khanOrDistrict;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "city_or_province")
    private String cityOrProvince;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

    @Column(name = "is_blocked", nullable = false)
    private Boolean isBlocked = false;

    // Relationship
    @OneToMany(mappedBy = "user")
    private List<Account> accounts;
}