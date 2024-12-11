package oncall.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import oncall.exception.IllegalInputException;

public class Roster {
    private final List<Worker> workers;
    private static final int WORKERS_MIN_SIZE = 5;
    private static final int WORKERS_MAX_SIZE = 35;


    public Roster(List<Worker> workers) {
        validate(workers);
        this.workers = workers;
    }

    private void validate(List<Worker> workers) {
        if (workers.size() < WORKERS_MIN_SIZE || workers.size() > WORKERS_MAX_SIZE) {
            throw new IllegalInputException();
        }
        checkDuplicate(workers);
    }

    private void checkDuplicate(List<Worker> workers) {
        Set<String> names = new HashSet<>();
        for (Worker w : workers) {
            names.add(w.getName());
        }
        if (workers.size() != names.size()) {
            throw new IllegalInputException();
        }
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public String getWorkerNameByIndex(int i) {
        return workers.get(i).getName();
    }

    public void swapRoster(int i) {
        Collections.swap(workers, i, i + 1);
    }

    public int getRosterSize() {
        return workers.size();
    }
}
