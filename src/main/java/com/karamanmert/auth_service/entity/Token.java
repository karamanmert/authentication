package com.karamanmert.auth_service.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author karamanmert
 */
@Entity(name = "token")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;

    @Column
    @Lob
    private String accessToken;

    @OneToOne(mappedBy = "token")
    private AuthUser user;
}
