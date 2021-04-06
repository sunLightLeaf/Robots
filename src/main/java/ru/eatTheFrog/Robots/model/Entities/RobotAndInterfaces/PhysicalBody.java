package ru.eatTheFrog.Robots.model.Entities.RobotAndInterfaces;
import static ru.eatTheFrog.Robots.model.static_modules.Algebra.MathFunctions.relu2;
import ru.eatTheFrog.Robots.model.static_modules.IReadonlyVector2D;
import ru.eatTheFrog.Robots.model.static_modules.MutableVector2D;

public abstract class PhysicalBody {
    public MutableVector2D position = new MutableVector2D(100, 100);
    public MutableVector2D velocity = MutableVector2D.zero();
    public double direction = 0;
    public double rotationSpeed = 0;
    public double slipping = 0;
    private double mass;
    public double getRotationSpeed() {
        return this.rotationSpeed;
    }
    public abstract void applyPhysics();
    public double getMass() {
        return this.mass;
    }
    public double getSlipping() {
        return this.slipping <= 100 ? this.slipping : 90;
    }
    public void applyRotateAcceleration(double acceleration) {
        this.rotationSpeed += acceleration;
    }
    public void applyShift(IReadonlyVector2D shift) {
        this.position.plus(shift);
    }
    public void setPosition(IReadonlyVector2D vector2D) {
        this.position.x = vector2D.getX();
        this.position.y = vector2D.getY();
    }
}
