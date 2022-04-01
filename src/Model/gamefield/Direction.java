package Model.gamefield;

// Направление
public class Direction {
    // ----------------------- Свойства --------------------------

    // определяем направление в часах (0 до 12)
    private final double _hours;

    private Direction(double hours) {
        _hours = hours;
    }

    // ----------------------- Порождение --------------------------
    public static Direction north() {
        return null;
    }

    public static Direction northEast() {
        return null;
    }

    public static Direction southEast() {
        return null;
    }

    public static Direction south() {
        return null;
    }

    public static Direction southWest() {
        return null;
    }

    public static Direction northWest() {
        return null;
    }

    public static Direction[] directions = { north(), northEast(), southEast(), south(), southWest(), northWest() };

    // ------------------ Новые направления ---------------------
    public Direction opposite() {
        return null;
    }

    // ------------------ Сравнение направлений ---------------------


    public int hashCode() {
       return 0;
    }

    @Override
    public boolean equals(Object other) {
        return false;
    }

    // --------------------------------------------------

}