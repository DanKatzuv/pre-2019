package robot.subsystems.intake.CommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import robot.subsystems.intake.Commands.CubeTakeCommand;
import robot.subsystems.intake.Commands.FoldingCommand;

/**
 *
 */
public class IntakeCommandGroup extends CommandGroup {

    public IntakeCommandGroup() {
        addSequential(new CubeTakeCommand(0.5));
        addSequential(new FoldingCommand(90), 5);
    }
}