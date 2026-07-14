package in.pipeup.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "creator_profiles")
public class CreatorProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


     // One User -> One Creator Profile
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    //Creator Identity
    @Column(nullable = false)
    private String displayName;


    @Column(nullable = false, length = 15)
    private String phoneNumber;


    //Social Links
    private String youtubeChannelUrl;
    private String instagramProfileUrl;

    private String state;
    private String city;


    //Creator Category
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "creator_niches", joinColumns = @JoinColumn(name = "creator_profile_id"))
    @Column(name = "niche")
    private Set<String> niches = new HashSet<>();


    //Creator Journey
    @Column(length = 500)
    private String currentChallenge;

    @Column(length = 500)
    private String expectedSupport;


    //Pricing
    @Column(precision = 10, scale = 2)
    private BigDecimal startingPrice;


    //Profile
    private String profileImageUrl;


    //Onboarding
    @Column(nullable = false)
    private Boolean onboardingCompleted = false;

    private LocalDateTime onboardingCompletedAt;

    //Audit
    @Column(updatable = false)
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}