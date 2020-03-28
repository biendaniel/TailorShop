package daniel.bien.tailor_shop.model.order;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "parameters_to_create_visits")
public class ParametersToCreateVisits {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "number_day_of_week")
    private Integer numberDayOfWeek; //first day is Sunday -> numberDayOfWeek = 1

    @Column(name = "start_visit_hour")
    private Integer startVisitHour;

    @Column(name = "start_visit_minute")
    private Integer startVisitMinute;

    @Column(name = "end_visit_hour")
    private Integer endVisitHour;

    @Column(name = "end_visit_minute")
    private Integer endVisitMinute;

    @Column(name = "visit_length")
    private Integer visitLength;
}
