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
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "alias", unique = true, length = 100)
    private String alias;

    @Column(name = "act_name", nullable = false, length = 100)
    private String actName;

    @Column(name = "act_no", nullable = false, unique = true, length = 9)
    private String actNo;

    @Column(name = "balance", nullable = false, precision = 19, scale = 2)
    private BigDecimal balance;

    @Column(name = "transfer_limit", nullable = false, precision = 19, scale = 2)
    private BigDecimal transferLimit;

    @Column(name = "is_hidden")
    private Boolean isHidden;

    // Relationship

    @ManyToOne
    private AccountType accountType;

    @OneToOne
    private Card card;

    @ManyToOne
    @JoinTable(name = "user_accounts",
        joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private User user;

    @OneToMany(mappedBy = "owner")
    private List<Transaction> transactions;
}