/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package robot.subsystems.intake;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Intake extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    private final VictorSP LeftIntake = new VictorSP(Ports.LeftIntake);
    private final VictorSP RightIntake = new VictorSP(Ports.RightIntake);
    private final VictorSP Folding = new VictorSP(Ports.Folding);


    public Intake(){
        LeftIntake.setInverted(Constants.LEFT_INVERTED);
        RightIntake.setInverted(Constants.RIGHT_INVERTED);
        Folding.setInverted(Constants.FOLDING_INVERTED);
    }

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    // functions for the speed of each motor
    public double getLeftSpeed() {
        return LeftIntake.getSpeed();
    }

    public void setLeftSpeed(double speed) {
        LeftIntake.set(speed);
    }

    public double getRightSpeed() {
        return RightIntake.getSpeed();
    }

    //set functions for each motor
    public void setRightSpeed(double speed) {
        RightIntake.set(speed);
    }

    public double getFoldingSpeed() {
        return Folding.getSpeed();
    }

    public void setFoldingSpeed(double speed) {
        Folding.set(speed);
    }

}