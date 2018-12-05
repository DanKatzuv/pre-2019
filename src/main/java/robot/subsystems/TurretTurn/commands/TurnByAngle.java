package robot.subsystems.TurretTurn.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
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
    private double angle;
    private double arcLength;
    private boolean isRelative;
    private TurretTurn turretTurn = Robot.turretTurn;

    /**
     *
     * @param desiredAngle the angle you want to turn in
     * @param isRelative true if you want to turn desiredAngle, or false if you want to turn to the desiredAngle
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
        startAngle = TurretTurn.getAngle();
        if (this.isRelative){
            this.desiredAngle += startAngle;
        }
        this.arcLength = (Constants.SHOOTER_BASE_PERIMITER * Math.PI)*((desiredAngle - startAngle)/360);

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