package robot.subsystems.intake.Commands;

import edu.wpi.first.wpilibj.command.Command;
import robot.subsystems.intake.Constants;

import static robot.Robot.intake;

/**@author Orel
 *
 */
public class FoldArms extends Command {

    private final int angle;

    public FoldArms(int angle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(intake);
        this.angle = angle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        intake.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        //the speed in proportional calculations
        double speed = ((Constants.INTAKE_AXIS_CIRCUMFERENCE / 360) * angle - intake.getPotentiometerAngle()) * Constants.kP;
        intake.setFoldingSpeed(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(intake.getPotentiometerAngle() - angle) < 10;
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