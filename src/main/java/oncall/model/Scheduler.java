package oncall.model;

import java.util.*;

/**
 * 비상 근무표, 근무자 배정 로직 담당
 */
public class Scheduler {
    private final List<Worker> workSchedule;

    private Scheduler(List<Worker> workSchedule) {
        this.workSchedule = workSchedule;
    }

    public static Scheduler of(Calendar calendar, Workers weekdayWorkers, Workers holidayWorkers) {
        List<Worker> workSchedule = new ArrayList<>();
        workSchedule.add(null);
        Deque<Worker> weekdayWorkersNotAssigned = new LinkedList<>();
        Deque<Worker> holidayWorkersNotAssigned = new LinkedList<>();
        for (int i = 1; i <= calendar.getLastDateOfMonth(); i++) {
            DayOfWeekKOR dayOfWeek = calendar.getDayOfMonth(i);
            Worker worker = null;
            boolean isHoliday = dayOfWeek.isHoliday() || calendar.isHoliday(i);
            if (!isHoliday) {
                worker = assignWorker(weekdayWorkersNotAssigned, weekdayWorkers);
            }
            if (isHoliday) {
                worker = assignWorker(holidayWorkersNotAssigned, holidayWorkers);
            }
            if (i > 1) {
                if (worker.isSame(workSchedule.get(i - 1))) {
                    Worker workerOnDutyInARow = worker;
                    if (!isHoliday) {
                        worker = assignWorker(weekdayWorkersNotAssigned, weekdayWorkers);
                        weekdayWorkersNotAssigned.push(workerOnDutyInARow);
                    }
                    if (isHoliday) {
                        worker = assignWorker(holidayWorkersNotAssigned, holidayWorkers);
                        holidayWorkersNotAssigned.push(workerOnDutyInARow);
                    }
                }
            }
            workSchedule.add(worker);
        }
        return new Scheduler(workSchedule);
    }

    public Worker getWorkerOnDuty(int date) {
        return workSchedule.get(date);
    }

    private static void fillWorkers(Deque<Worker> workersNotAssigned, Workers workers) {
        for (int i = 0; i < workers.size(); i++) {
            workersNotAssigned.add(workers.get(i));
        }
    }

    private static Worker assignWorker(Deque<Worker> workersNotAssigned, Workers workers) {
        if (workersNotAssigned.isEmpty()) {
            fillWorkers(workersNotAssigned, workers);
        }
        return workersNotAssigned.pop();
    }
}
