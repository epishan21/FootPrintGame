package Model.gamefield;

// Абстрактный билдер поля
abstract public class Builder {

    protected GameField _field;

    protected void setField(GameField field) {
        _field = field;
    }

    public GameField getField() {
        return _field;
    }

    public void run() {
        createField();
        seedAnyCell();
        _field.linkCells();
        seedRobot();
        seedKey();
    }

    abstract protected void seedAnyCell();

    abstract protected void seedRobot();

    abstract protected void createField();

    abstract protected void seedKey();
}
