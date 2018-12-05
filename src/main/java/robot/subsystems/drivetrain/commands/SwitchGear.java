package robot.subsystems.drivetrain.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.InstantCommand;

import static robot.Robot.drivetrain;

/**
 * Switch the gear of the Drivetrain, from high to low and vice versa.
 */
public class SwitchGear extends InstantCommand {

    public SwitchGear() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(drivetrain);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        drivetrain.changeGear(drivetrain.getGear().equals(DoubleSolenoid.Value.kForward) ? DoubleSolenoid.Value.kReverse : DoubleSolenoid.Value.kForward);
    }


}