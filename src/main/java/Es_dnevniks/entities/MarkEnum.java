package Es_dnevniks.entities;

public enum MarkEnum {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5);

    private final int value;

    private MarkEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}


