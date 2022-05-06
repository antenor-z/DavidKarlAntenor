package Model.Deck.Card;

public enum CardType {
    VALUE,
    GO_TO_JAIL,
    OUT_OF_JAIL;

    public static CardType fromInteger(int x) {
        return switch (x) {
            case 0 -> VALUE;
            case 1 -> GO_TO_JAIL;
            case 2 -> OUT_OF_JAIL;
            default -> null;
        };
    }
}
