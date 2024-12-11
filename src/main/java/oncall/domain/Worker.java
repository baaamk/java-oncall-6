package oncall.domain;

import oncall.exception.IllegalNameException;

public class Worker {
    private static final int NAME_LENGTH = 5;

    private final String name;

    public Worker(String name){
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if(name.length() > NAME_LENGTH){
            throw new IllegalNameException();
        }
    }

    public String getName() {
        return name;
    }
}
