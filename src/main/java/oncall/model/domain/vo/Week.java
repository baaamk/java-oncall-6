package oncall.model.domain.vo;

public enum Week {
    MONDAY("월"),
    TUESDAY("화"),
    WEDNESDAY("수"),
    THURSDAY("목"),
    FRIDAY("금"),
    SATURDAY("토"),
    SUNDAY("일");

    private final String name;

    Week(String name) {
        this.name = name;
    }

    public Week next() {
        Week[] values = values();
        return values[(this.ordinal() + 1) % values.length];
    }

    public String getName() {
        return name;
    }
}
