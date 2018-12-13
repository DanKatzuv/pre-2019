package robot.subsystems.general_Commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import robot.Robot;
import robot.subsystems.TurretTurn.commands.TurnByAngle;
import robot.subsystems.conveyer.Commands.ConveyorCommand;
import robot.subsystems.shooter.commands.Shoot;

/**
 *
 */
public class Complete_shoot  extends CommandGroup {

    public Complete_shoot (double desired_angle,boolean is_relative, double NTspeed, double timeout) {
        addParallel(new ConveyorCommand());//  move ball to turret TODO: check if making this parallel will cause problems
        addSequential(new TurnByAngle(desired_angle,is_relative));//  aim to target
        addSequential(new Shoot(NTspeed,timeout));//then shoot
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.

    }
}