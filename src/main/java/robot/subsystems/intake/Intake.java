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

/**
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

    // functions for the speed of each motor
    public double getLeftSpeed() {
        return leftIntake.getSpeed();
    }

    public void setLeftSpeed(double speed) {
        leftIntake.set(speed);
    }

    public double getRightSpeed() {
        return rightIntake.getSpeed();
    }

    //set functions for each motor
    public void setRightSpeed(double speed) {
        rightIntake.set(speed);
    }

    public double getFoldingSpeed() {
        return folding.getSpeed();
    }

    public void setFoldingSpeed(double speed) {
        folding.set(speed);
    }

    //Get data from proximity sensor
    public double voltage() {
        return proximitySensor.getVoltage();
    }

    //Method to know if the cube inside
    public boolean CubeInside() {
        return voltage() <= Constants.MIN_PROXIMITY;
    }

    public void endAllMotors() {
        rightIntake.set(0);
        leftIntake.set(0);
        folding.set(0);
    }

    //INTAKE POSITION DEGREE
    public boolean intakeIN90DEG() {
        return (Constants.INTAKE_ARC / 4) >= getEncoderDist();
    }

    //ENCODER DISTANCE
    public int getEncoderDist() {
        return foldingEncoder.get();
    }

    public double currentIntakeDegree() {
        return getEncoderDist() / Constants.INTAKE_ARC;
    }

    public void reset() {
        foldingEncoder.reset();
        proximitySensor.resetAccumulator();
    }

}