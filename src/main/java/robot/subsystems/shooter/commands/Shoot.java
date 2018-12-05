package robot.subsystems.shooter.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;

/**
 * An implementation of the wpilib Command. sets the shooter wheel velocity to a given rate (in m/s)
 * can also be set to run for a certain amount of time, in the end of which the wheel turns off.
 */
public class Shoot extends Command {
    private double velocity;
    private double timeout;
    private Timer timer = new Timer();

    /**
     * Constructor of the Shoot command.
     * @param velocity tangent speed of the wheel in meters/seconds.
     * @param timeout timeout of the wheels to spin in seconds. If set to 0 the wheels will not stop.
     */
    public Shoot(double velocity, double timeout) {
        requires(Robot.shooter);
        this.velocity = velocity;
        this.timeout = timeout;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        timer.reset();
        timer.start();
        Robot.shooter.setMotorVelocity(velocity); //TODO: check if this line has to be in the execute.
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timeout == 0 || timer.get() >= timeout;
    }

    // Called once after isFinished returns true
    protected void end() {
        if(timeout != 0)
            Robot.shooter.setMotorVelocity(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
        this.cancel();
    }
}