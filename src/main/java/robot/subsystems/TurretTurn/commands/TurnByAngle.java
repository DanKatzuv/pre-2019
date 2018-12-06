package robot.subsystems.TurretTurn.commands;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;
import robot.subsystems.TurretTurn.Constants;
import robot.subsystems.TurretTurn.TurretTurn;

/**
 *
 */
public class TurnByAngle extends Command {
    private double desiredAngle;
    private double startAngle;
    private double arcLength;
    private boolean isRelative;
    private TurretTurn turretTurn = Robot.turretTurn;

    /**
     * @param desiredAngle the angle you want to turn in
     * @param isRelative   true if you want to turn desiredAngle, or false if you want to turn to the desiredAngle
     * @author Lior
     */
    public TurnByAngle(double desiredAngle, boolean isRelative) {
        this.isRelative = isRelative;
        this.desiredAngle = desiredAngle;
        requires(turretTurn);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        startAngle = Robot.turretTurn.getAngle();
        if (this.isRelative) {
            if (startAngle + this.desiredAngle >= Constants.MAX_DEGREE)
                this.desiredAngle += startAngle - 360;
            if (startAngle + this.desiredAngle <= Constants.MIN_DEGREE)
                this.desiredAngle += startAngle + 360;
            else
                this.desiredAngle += startAngle;
        }
        if (desiredAngle - startAngle <= 180)
            this.arcLength = (desiredAngle - startAngle);
        else
            this.arcLength = (desiredAngle - startAngle - 360);
        Robot.turretTurn.setDesiredAngle(this.arcLength);
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