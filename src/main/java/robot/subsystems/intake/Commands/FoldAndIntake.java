package robot.subsystems.intake.Commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import robot.subsystems.intake.Constants;

/**
 *
 */
public class FoldAndIntake extends CommandGroup {

    public FoldAndIntake() {
        addSequential(new IntakeCube(0.5));
        addSequential(new FoldArms(90), Constants.FOLDING_TIMEOUT); //TODO: Check if timeout is necessary
        addSequential(new WaitCommand(Constants.balls_timeout));
        addSequential(new FoldArms(-90), Constants.FOLDING_TIMEOUT);

    }
}