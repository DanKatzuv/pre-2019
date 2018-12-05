package robot.subsystems.shooter.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;

/**
 *
 */
public class Shoot extends Command {
    private double velocity;
    private double timeout;
    private Timer timer = new Timer();
    public Shoot(double velocity, double timeout) {
        requires(Robot.shooter);
        this.velocity = velocity;
        this.timeout = timeout;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        timer.reset();
        timer.start();
        Robot.shooter.setMotorVelocity(velocity);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timeout == 0 || timer.get() > timeout;
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
    }
}