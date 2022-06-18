package br.com.alexmdo.diningreviewapi.dinningreview;

import br.com.alexmdo.diningreviewapi.dinningreview.model.DinningReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DinningReviewRepository extends JpaRepository<DinningReview, Long> {
}
