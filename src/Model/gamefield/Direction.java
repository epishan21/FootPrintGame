package Model.gamefield;

// Направление
public class Direction {
    // ----------------------- Свойства --------------------------

    // определяем направление в часах (0 до 12)
    private final double _hours;

    private Direction(double hours) {

        // Приводим заданные часы к допустимому диапазону
        hours = hours % 12;
        if (hours < 0) { hours += 12; }

        _hours = hours;
    }

    // ----------------------- Порождение --------------------------
    public static Direction north() { return new Direction(0); }

    public static Direction northEast() {
        return new Direction(1.5);
    }

    public static Direction southEast() {
        return new Direction(4.5);
    }

    public static Direction south() { return new Direction(6); }

    public static Direction southWest() {
        return new Direction(7.5);
    }

    public static Direction northWest() {
        return new Direction(10.5);
    }

    public static Direction[] directions = { north(), northEast(), southEast(), south(), southWest(), northWest() };

    // ------------------ Новые направления ---------------------
    public Direction opposite() {
        return new Direction(this._hours + 6);
    }


    // ------------------ Сравнение направлений ---------------------
    public int hashCode() {
        double hash = 5;
        hash = 53 * hash + this._hours;
        int b = (int) hash;
        return b;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (!(other instanceof Direction)) {
            // если объект не совместим c Direction, возвращаем false
            return false;
        }

        Direction direct = (Direction) other;
        return _hours == direct._hours;
    }

}