package Model;

public enum CardType {
    VALUE,
    GO_TO_JAIL,
    OUT_OF_JAIL,
    VALUE_PER_PLAYER,
    MOVE;

    public static CardType fromInteger(int x) {
        return switch (x) {
            case 0 -> VALUE;
            case 1 -> GO_TO_JAIL;
            case 2 -> OUT_OF_JAIL;
            case 3 -> VALUE_PER_PLAYER;
            case 4 -> MOVE;
            default -> null;
        };
    }
}
