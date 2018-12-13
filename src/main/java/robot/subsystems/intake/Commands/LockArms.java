package robot.subsystems.intake.Commands;

import edu.wpi.first.wpilibj.command.Command;

import static robot.Robot.intake;

/**
 *
 */
public class LockArms extends Command {

    private boolean lock;//the arms will lock if true if false they will release

    public LockArms(boolean lock) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(intake);
        this.lock = lock;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (lock) {
            intake.setClose();
        } else {
            intake.setOpen();
        }

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