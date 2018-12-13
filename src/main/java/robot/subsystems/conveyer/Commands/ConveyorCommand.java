package robot.subsystems.conveyer.Commands;

import edu.wpi.first.wpilibj.command.Command;
import robot.subsystems.conveyer.Constants;

import static robot.Robot.conveyor;

/**
 *
 */
public class ConveyorCommand extends Command {

    public ConveyorCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(conveyor);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        conveyor.setSorterSpeed(Constants.SORTER_SPEED);
        conveyor.setShooterWheelSpeed(Constants.SHOOTER_WHEEL_SPEED);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        conveyor.setSorterSpeed(0); //TODO: Check if can be deleted if default value is 0
        conveyor.setShooterWheelSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}