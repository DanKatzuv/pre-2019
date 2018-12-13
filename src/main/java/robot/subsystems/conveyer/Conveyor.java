/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package robot.subsystems.conveyer;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import robot.subsystems.conveyer.Commands.ConveyorCommand;

/**
 * The Conveyor subsystem which feeds balls to the Shooter.
 */
public class Conveyor extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    private final VictorSP sorter = new VictorSP(Ports.sorter);
    private final VictorSP shooterWheel = new VictorSP(Ports.shooterWheel);


    public Conveyor() {
        sorter.setInverted(Constants.SORTER_INVERTED);
        shooterWheel.setInverted(Constants.SHOOTER_ROTATED);
    }

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new ConveyorCommand());
    }

    /**
     * This method sets the speed for the sorter motor, input voltage -1 <= speed <= 1
     * @param speed the speed for the sorter motor
     * @author orel
     */
    public void setSorterSpeed(double speed) {
        sorter.set(speed);
    }

    /**
     * This method sets the speed for the shooterWheel motor, input voltage -1 <= speed <= 1
     * @param speed the speed for the shooter wheel motor
     * @author orel
     */
    public void setShooterWheelSpeed(double speed) {
        shooterWheel.set(speed);
    }


}