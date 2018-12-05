/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package robot.subsystems.drivetrain;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import robot.Robot;
import robot.subsystems.drivetrain.commands.JoystickDrive;
import robot.subsystems.drivetrain.pure_pursuit.Point;

/**
 * Add your docs here.
 */


public class Drivetrain extends Subsystem {
    private final VictorSP left1 = new VictorSP(Ports.leftMotor1);
    private final VictorSP left2 = new VictorSP(Ports.leftMotor2);
    private final VictorSP left3 = new VictorSP(Ports.leftMotor3);
    private final VictorSP right1 = new VictorSP(Ports.rightMotor1);
    private final VictorSP right2 = new VictorSP(Ports.rightMotor2);
    private final VictorSP right3 = new VictorSP(Ports.rightMotor3);

    private final Encoder leftEncoder = new Encoder(Ports.leftEncoderChannelA, Ports.leftEncoderChannelB);
    private final Encoder rightEncoder = new Encoder(Ports.rightEncoderChannelA, Ports.rightEncoderChannelB);

    private final DoubleSolenoid leftGearShifter = new DoubleSolenoid(Ports.leftGearShifterReverseChannel, Ports.leftGearShifterForwardChannel);
    private final DoubleSolenoid rightGearShifter = new DoubleSolenoid(Ports.rightGearShifterReverseChannel, Ports.rightGearShifterForwardChannel);

    public Point currentLocation;

    public Drivetrain() {
        leftEncoder.setDistancePerPulse(Constants.PULSE_PER_DISTANCE);
        rightEncoder.setDistancePerPulse(Constants.PULSE_PER_DISTANCE);

        left1.setInverted(Constants.LEFT1_REVERSED);
        left2.setInverted(Constants.LEFT2_REVERSED);
        left3.setInverted(Constants.LEFT3_REVERSED);
        right1.setInverted(Constants.RIGHT1_REVERSED);
        right2.setInverted(Constants.RIGHT2_REVERSED);
        right3.setInverted(Constants.RIGHT3_REVERSED);
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new JoystickDrive());
    }

    /**
     * Set the speed for both sides.
     *
     * @param leftSpeed  Speed for the left side
     * @param rightSpeed Speed for the right side
     */
    public void setSpeed(double leftSpeed, double rightSpeed) {
        setLeftSpeed(leftSpeed);
        setRightSpeed(rightSpeed);
    }

    public double getLeftSpeed() {
        return leftEncoder.getRate();
    }

    /**
     * Set the speed for the left side.
     *
     * @param speed speed for the motors of the left side
     */
    private void setLeftSpeed(double speed) {
        left1.set(speed);
        left2.set(speed);
        left3.set(speed);
    }

    public double getRightSpeed() {
        return rightEncoder.getRate();
    }

    /**
     * Set the speed for the right side.
     *
     * @param speed speed for the motors of the right side
     */
    private void setRightSpeed(double speed) {
        right1.set(speed);
        right2.set(speed);
        right3.set(speed);
    }

    /**
     * @return The distance driven on the right side of the robot since the last reset
     */
    public double getRightDistance() {
        return rightEncoder.getDistance();
    }

    /**
     * @return The distance driven on the left side of the robot since the last reset
     */
    public double getLeftDistance() {
        return leftEncoder.getDistance();
    }

    public void resetEncoders() {
        leftEncoder.reset();
        rightEncoder.reset();
    }

    /**
     * Change the gear of the Drivetrain.
     *
     * @param value value to change
     */
    public void changeGear(DoubleSolenoid.Value value) {
        leftGearShifter.set(value);
        rightGearShifter.set(value);
    }

    /**
     * Return the current gear of the Drivetrain.
     *
     * @return current gear of the Drivetrain
     */
    public DoubleSolenoid.Value getGear() {
        return leftGearShifter.get();
    }

    /**
     * returns the robot NAVX yaw angle
     *
     * @return navx yaw angle
     */
    public double getAngle() {
        return Robot.navx.getAngle();
    }

    /**
     * returns the robot NAVX pitch angle
     *
     * @return navx pitch angle
     */
    public double getPitch() {
        return Robot.navx.getPitch();
    }

    /**
     * returns the robot NAVX roll angle
     *
     * @return navx roll angle
     */
    public double getRoll() {
        return Robot.navx.getRoll();
    }

}