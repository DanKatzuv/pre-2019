/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package robot.subsystems.intake;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.PIDSourceType;
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
    private final AnalogPotentiometer foldingEncoder = new AnalogPotentiometer(Ports.FOLDING_POTENTIOMETER) {
        @Override
        public double get() {
            return 0;
        }

        @Override
        public PIDSourceType getPIDSourceType() {
            return null;
        }

        @Override
        public void setPIDSourceType(PIDSourceType pidSource) {

        }

        @Override
        public double pidGet() {
            return 0;
        }
    };


    public Intake(){
        leftIntake.setInverted(Constants.LEFT_INVERTED);
        rightIntake.setInverted(Constants.RIGHT_INVERTED);
        folding.setInverted(Constants.FOLDING_INVERTED);
        proximitySensor.resetAccumulator();
    }

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    public void setLeftSpeed(double speed) {
        leftIntake.set(speed);
    }

    /**
     * @param speed the speed of the motors
     */
    public void setRightSpeed(double speed) {
        rightIntake.set(speed);
    }

    public void setFoldingSpeed(double speed) {
        folding.set(speed);
    }

    /**
     *
     * @return the voltage from the proximity sensor
     */
    private double voltage() {
        return proximitySensor.getVoltage();
    }

    /**
     *
     * @return if the cube inside
     */
    public boolean isCubeInside() {
        return voltage() <= Constants.MIN_PROXIMITY;
    }

    /**
     * method that stop the motors
     */
    public void stopAllMotors() {
        rightIntake.set(0);
        leftIntake.set(0);
        folding.set(0);
    }

    /**
     *
     * @return the distance the encoder moved
     */
    public double getEncoderDist() {
        return foldingEncoder.get();
    }

    /**
     *
     * @return the current angle of the intake
     */
    public double currentIntakeDegree() {
        return getEncoderDist() / Constants.INTAKE_AXIS_CIRCUMFERENCE;
    }

    /**
     * reset the sensors
     */
    public void reset() {
        proximitySensor.resetAccumulator();
    }

}