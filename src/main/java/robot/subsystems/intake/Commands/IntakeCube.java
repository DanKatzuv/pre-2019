package robot.subsystems.intake.Commands;

import edu.wpi.first.wpilibj.command.Command;

import static robot.Robot.intake;

/**@author Orel
 *
 */
public class IntakeCube extends Command {

    private final double speed;

    public IntakeCube(double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(intake);
        this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        intake.setLeftSpeed(speed);
        intake.setRightSpeed(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {

        return intake.isCubeInside();

    }

    // Called once after isFinished returns true
    protected void end() {
        intake.stopAllMotors();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}