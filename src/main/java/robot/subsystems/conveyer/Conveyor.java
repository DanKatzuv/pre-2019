/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package robot.subsystems.conveyer;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
import robot.subsystems.conveyer.Commands.ConveyorCommand;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Conveyor extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    private final VictorSPX conveyorRotation = new VictorSPX(Ports.ConRotation);
    private final VictorSPX conveyorForShooter = new VictorSPX(Ports.ConToSHOOTER);


    public Conveyor() {
        conveyorRotation.setInverted(false);

        conveyorForShooter.setInverted(false);
    }

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new ConveyorCommand());
    }

    /**
     * @auther orel
     * @param speed the speed for the motor
     */
    public void SetSpeedForRotation(double speed){
        conveyorRotation.set(ControlMode.PercentOutput, speed);
    }

    public void setSpeed(double speed) {
        conveyorForShooter.set(ControlMode.PercentOutput, speed);
    }


}