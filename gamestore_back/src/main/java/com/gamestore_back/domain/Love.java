package com.gamestore_back.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = {"owner", "items"})
@Table(
        name = "tbl_love",
        indexes = {@Index(name = "idx_love_email", columnList = "player_owner")}
)
public class Love {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lno; // 찜 ID

    @OneToOne
    @JoinColumn(name = "player_owner") // 사용자와 연결
    private Player owner;

    @Column(nullable = false, unique = true)
    private String ownerEmail;

    @OneToMany(mappedBy = "love", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LoveItem> items = new ArrayList<>(); // 찜 항목 리스트
}
