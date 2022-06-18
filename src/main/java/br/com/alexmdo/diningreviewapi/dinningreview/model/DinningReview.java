package br.com.alexmdo.diningreviewapi.dinningreview.model;

import br.com.alexmdo.diningreviewapi.restaurant.model.Restaurant;
import br.com.alexmdo.diningreviewapi.user.model.User;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "dinning_reviews")
public class DinningReview {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private int peanutScore = 1;
    private int eggScore = 1;
    private int dairyScore = 1;
    private String commentary;

    @ManyToOne
    @JoinColumn(name = "admin_review_action_id")
    private AdminReviewAction adminReviewAction;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DinningReview that = (DinningReview) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}