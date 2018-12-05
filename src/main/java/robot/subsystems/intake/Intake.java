/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package robot.subsystems.intake;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**@author Orel
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Intake extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    private final VictorSP leftIntake = new VictorSP(Ports.LeftIntake);
    private final VictorSP rightIntake = new VictorSP(Ports.RightIntake);
    private final AnalogInput proximitySensor = new AnalogInput(Ports.proximity);
    private final VictorSP folding = new VictorSP(Ports.Folding);
    private final Encoder foldingEncoder = new Encoder(Ports.FoldingEncoderA, Ports.FoldongEncoderB);


    public Intake(){
        leftIntake.setInverted(Constants.LEFT_INVERTED);
        rightIntake.setInverted(Constants.RIGHT_INVERTED);
        folding.setInverted(Constants.FOLDING_INVERTED);
        proximitySensor.resetAccumulator();
        foldingEncoder.reset();
    }

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    public void setLeftSpeed(double speed) {
        leftIntake.set(speed);
    }

    //set functions for each motor
    public void setRightSpeed(double speed) {
        rightIntake.set(speed);
    }

    public void setFoldingSpeed(double speed) {
        folding.set(speed);
    }

    //Get data from proximity sensor
    private double voltage() {
        return proximitySensor.getVoltage();
    }

    //Method to know if the cube inside
    public boolean isCubeInside() {
        return voltage() <= Constants.MIN_PROXIMITY;
    }

    public void stopAllMotors() {
        rightIntake.set(0);
        leftIntake.set(0);
        folding.set(0);
    }

    public int getEncoderDist() {
        return foldingEncoder.get();
    }

    public double currentIntakeDegree() {
        return getEncoderDist() / Constants.INTAKE_AXIS_CIRCUMFERENCE;
    }

    public void reset() {
        foldingEncoder.reset();
        proximitySensor.resetAccumulator();
    }

}