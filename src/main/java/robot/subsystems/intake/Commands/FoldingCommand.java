package robot.subsystems.intake.Commands;

import edu.wpi.first.wpilibj.command.Command;
import robot.subsystems.intake.Constants;

import static robot.Robot.intake;

/**@author orel
 *
 */
public class FoldingCommand extends Command {

    private double speed;

    public FoldingCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        intake.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        speed = ((Constants.INTAKE_ARC / 4) - intake.getEncoderDist()) * Constants.kP;
        intake.setFoldingSpeed(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {

        return intake.intakeIN90DEG();
    }

    // Called once after isFinished returns true
    protected void end() {
        intake.endAllMotors();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}