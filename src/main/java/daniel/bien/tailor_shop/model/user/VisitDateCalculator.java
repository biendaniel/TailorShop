package daniel.bien.tailor_shop.model.user;

import daniel.bien.tailor_shop.model.order.ParametersToCreateVisits;
import daniel.bien.tailor_shop.repository.order.ParametersToCreateVisitsRepository;
import daniel.bien.tailor_shop.repository.order.VisitRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class VisitDateCalculator {

    private final ParametersToCreateVisitsRepository parametersToCreateVisitsRepository;
    private final VisitRepository visitRepository;

    public VisitDateCalculator(ParametersToCreateVisitsRepository parametersToCreateVisitsRepository, VisitRepository visitRepository) {
        this.parametersToCreateVisitsRepository = parametersToCreateVisitsRepository;
        this.visitRepository = visitRepository;
    }

    private long getEndTimeInMillis(ParametersToCreateVisits parameters, Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, parameters.getEndVisitHour());
        calendar.set(Calendar.MINUTE, parameters.getEndVisitMinute());
        return calendar.getTimeInMillis();
    }

    private long getStartTimeInMillis(ParametersToCreateVisits parameters, Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, parameters.getStartVisitHour());
        calendar.set(Calendar.MINUTE, parameters.getStartVisitMinute());
        return calendar.getTimeInMillis();
    }

    public List<Date> getVisitsOfDay(Calendar calendar, int dayOfWeek) {
        ParametersToCreateVisits parameters = getParameters(dayOfWeek);
        if (parameters.getStartVisitHour() == null) return new LinkedList<>();
        List<Date> visitDatesOfDay = new ArrayList<>();
        long startTime = getStartTimeInMillis(parameters, calendar);
        long endTime = getEndTimeInMillis(parameters, calendar);

        while (endTime >= startTime) {
            visitDatesOfDay.add(new Date(startTime));
            startTime += parameters.getVisitLength() * 60 * 1000;
        }
        return visitDatesOfDay;
    }

    public List<Date> getVisitsOfDay(int dayOfWeek) {
        Calendar calendar = setProperDayOfWeek(dayOfWeek);
        return  getVisitsOfDay(calendar, dayOfWeek);
    }


    private ParametersToCreateVisits getParameters(int dayOfWeek) {
        return parametersToCreateVisitsRepository.findById(dayOfWeek).orElseThrow();
    }

    public Calendar setProperDayOfWeek(Integer dayOfWeek) {
        Calendar calendar = Calendar.getInstance();

        while (calendar.get(Calendar.DAY_OF_WEEK) != dayOfWeek) {
            calendar.add(Calendar.DATE, 1);
        }
        return calendar;
    }


    public List<Date> createDatesForWeek() {
        List<Date> dates = new LinkedList<>();
        for (int i = 1; i <= 7; i++) {
            dates.addAll(getVisitsOfDay(i));
        }
        return dates;
    }

    private Date getLastAddedVisitDate() {
        Integer lastId = visitRepository.getLastVisitId();
        try {
            return visitRepository.findById(lastId).get().getDate();
        } catch (Exception e) {
            e.getMessage();
        }
        return new Date();
    }

    private Calendar findNextMonday() {
        Date lastVisitDate = getLastAddedVisitDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(lastVisitDate);
        calendar.set(Calendar.HOUR_OF_DAY, 5);

        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            calendar.add(Calendar.DATE, 1);
        }
        return calendar;
    }

    public List<Date> addAllWeekVisits() {
        List<Date> dates = new LinkedList<>();
        Calendar calendar = findNextMonday();
        do {
            dates.addAll(getVisitsOfDay(calendar, Calendar.DAY_OF_WEEK));
            calendar.add(Calendar.DATE, 1);
        } while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY);
        return dates;
    }
}
