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
        addSequential(new LockArms(true), Constants.WAIT_FOR_SELONOIDS);
        addSequential(new FoldArms(90), Constants.FOLDING_TIMEOUT); //TODO: Check if timeout is necessary
        addSequential(new WaitCommand(Constants.WAIT_BALLS_TIMEOUT));
        addSequential(new FoldArms(-90), Constants.FOLDING_TIMEOUT);
        addSequential(new LockArms(false), Constants.WAIT_FOR_SELONOIDS);

    }
}