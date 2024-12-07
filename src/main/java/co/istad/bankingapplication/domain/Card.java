package co.istad.bankingapplication.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "number", nullable = false, unique = true, length = 120)
    private String number;

    @Column(name = "holder", length = 120)
    private String holder;

    @Column(name = "cvv", nullable = false, unique = true, length = 4)
    private String cvv;

    @Column(name = "issued_at", nullable = false)
    private LocalDate issuedAt;

    @Column(name = "expires_at", nullable = false)
    private LocalDate expiresAt;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

    // Relationship
    @OneToOne(mappedBy = "card")
    private Account account;

    @ManyToOne
    private CardType cardType;
}