package robot.subsystems.conveyer.Commands;

import edu.wpi.first.wpilibj.command.Command;
import robot.subsystems.conveyer.Conveyer;

/**
 *
 */
public class TriserionCommand extends Command {

    public TriserionCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(new Conveyer());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}