package robot.subsystems.TurretTurn.commands;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;
import robot.subsystems.TurretTurn.Turret;

/**
 *
 */
public class TurnByAngle extends Command {
    private double desiredAngle;
    private double startAngle;
    private double absoluteAngle;
    private boolean isRelative;
    private Turret turret = Robot.turret;

    /**
     * @param desiredAngle the angle you want to turn in
     * @param isRelative   true if you want to turn desiredAngle, or false if you want to turn to the desiredAngle
     * @author Lior
     */
    public TurnByAngle(double desiredAngle, boolean isRelative) {
        requires(turret);
        
        this.isRelative = isRelative;
        this.desiredAngle = desiredAngle;

        if (this.isRelative)
            this.desiredAngle += startAngle;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        startAngle = Robot.turret.getAngle();

        if (desiredAngle - startAngle <= 180)
            this.absoluteAngle = (desiredAngle - startAngle);
        else
            this.absoluteAngle = (desiredAngle - startAngle - 360);
        Robot.turret.setDesiredAngle(this.absoluteAngle);
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