package daniel.bien.tailor_shop.repository.order;

import daniel.bien.tailor_shop.model.order.Visit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface VisitRepository extends CrudRepository<Visit, Integer> {

    @Query(value = "SELECT * FROM visit where visit_date >= ?1 AND visit_date <= ?2   ", nativeQuery = true)
    List<Visit> getVisitsToDay(Date todayDate, Date latestDayInWeek);

    @Query(value = "SELECT MAX(id) FROM VISIT;", nativeQuery = true)
    Integer getLastVisitId();

    @Query(value = "SELECT * FROM VISIT WHERE visit_date > ?1 ORDER BY visit_date", nativeQuery = true)
    List<Visit> findAllAfterDate(Date currentDate);

    @Query(value = "SELECT * FROM VISIT WHERE visit_date > curdate() AND customer_id = ?1 ORDER BY visit_date", nativeQuery = true)
    List<Visit> findIncomingVisitByCustomerId(Integer id);

}
