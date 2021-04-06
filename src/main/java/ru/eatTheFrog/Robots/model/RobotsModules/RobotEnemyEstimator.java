package ru.eatTheFrog.Robots.model.RobotsModules;

import ru.eatTheFrog.Robots.model.Entities.RobotAndInterfaces.IEnemy;
import ru.eatTheFrog.Robots.model.Entities.RobotAndInterfaces.IEnemyEstimateRobot;
import ru.eatTheFrog.Robots.model.Entities.RobotAndInterfaces.IRobot;
import ru.eatTheFrog.Robots.model.Entities.RobotAndInterfaces.RobotRaw;

public class RobotEnemyEstimator {
    IEnemyEstimateRobot robot;
    public double solutionKsuperiority = 1;
    public double solutionKvolumeEstimator = 1;
    public double solutionKMaxToAttack = 2;
    public RobotEnemyEstimator(RobotRaw robot) {
        this.robot = robot;
    }

    public int estimateEnemy(IEnemy enemy) {
        if (robot == enemy)
            return 0;
        var ourSuperiority = solutionKsuperiority * robot.getHealth() / (Math.abs(enemy.getAttack())+1)  - enemy.getHealth()/ (Math.abs(robot.getAttack())+1);

        if (ourSuperiority < 0) {
            return -1;
        } else {
            var enemyVolume = enemy.getThickness() * enemy.getLength();
            if (enemy.getHealth() / (Math.abs(robot.getAttack())+1)  > solutionKMaxToAttack)
                return 0;

            if (robot.getInclinationToFleshEating() < 0)
                return 0;

            if (enemy.getHealth() < enemyVolume * solutionKvolumeEstimator) {
                return 1;
            }
        }
        return 0;
    }
    static class EnemyEstimationPair {
        IEnemy enemy;
        double value;

        public EnemyEstimationPair(IEnemy enemy, double value) {
            this.enemy = enemy;
            this.value = value;
        }
        public IEnemy getRobot() {
            return this.enemy;
        }
        public double getValue() {
            return value;
        }
    }

}
