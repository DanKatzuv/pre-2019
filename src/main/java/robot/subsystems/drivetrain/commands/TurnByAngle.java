package robot.subsystems.drivetrain.commands;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;
import robot.subsystems.drivetrain.Constants;
import robot.subsystems.drivetrain.Drivetrain;

/**
 *
 */
public class TurnByAngle extends Command {
    private static final double MIN_DEGREES_ERROR = 5;
    private static final double TURN_CONTROL_FACTOR = 1.5;
    private double desiredAngle;
    private double startAngle;
    private double degreesError;
    private double angle;
    private boolean isRelative;
    private double arcLength; //length of the path
    private Drivetrain drive = Robot.drivetrain;

    public TurnByAngle(double angle, boolean isRelative) {
        this.isRelative = isRelative;
        this.angle = angle;
        requires(drive);
    }

    protected void initialize() {
        startAngle = drive.getAngle();

        if (isRelative) {
            this.desiredAngle = angle + drive.getAngle();
        } else {
            this.desiredAngle = angle;
        }
        this.arcLength = (Constants.ROBOT_WIDTH * Math.PI) * (desiredAngle - startAngle) / 360;

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
