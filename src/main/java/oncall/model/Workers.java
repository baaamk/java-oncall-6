package oncall.model;

import oncall.exception.DuplicatedNicknameException;
import oncall.exception.WorkersLessThanMinimumException;
import oncall.exception.WorkersOverMaximum;
import oncall.util.InputParser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Workers {
    private final List<Worker> workers;

    private Workers(List<Worker> workers) {
        this.workers = workers;
    }

    public static Workers from(List<String> namesOfWorkers) {
        validate(namesOfWorkers);
        List<Worker> workers = new ArrayList<>();
        for (String workerName: namesOfWorkers) {
            Worker worker = new Worker(workerName);
            workers.add(worker);
        }
        return new Workers(workers);
    }

    public int size() {
        return workers.size();
    }

    public Worker get(int index) {
        return workers.get(index);
    }

    private static void validate(List<String> namesOfWorkers) {
        validateNumOfWorkers(namesOfWorkers.size());
        validateDuplicatedNicknames(namesOfWorkers);
    }

    private static void validateNumOfWorkers(int numOfWorkers) {
        if (numOfWorkers < 5) {
            throw new WorkersLessThanMinimumException();
        }
        if (numOfWorkers > 35) {
            throw new WorkersOverMaximum();
        }
    }

    private static void validateDuplicatedNicknames(List<String> namesOfWorkers) {
        Set<String> uniqueNicknames = new HashSet<>();
        for (String nickname : namesOfWorkers) {
            if (!uniqueNicknames.add(nickname)) {
                throw new DuplicatedNicknameException();
            }
        }
    }
}
