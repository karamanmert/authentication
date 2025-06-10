package com.karamanmert.auth_service.entity;

import com.karamanmert.auth_service.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

/**
 * @author karamanmert
 * @date 11.11.2024
 */

@Entity
@Table(name = "auth_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AuthUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email", unique = true) // also known as USERNAME
    private String email;

    @Column(name = "password")
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private UserRole role;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "token_id", referencedColumnName = "id")
    private Token token;
}
