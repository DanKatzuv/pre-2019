/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package robot.subsystems.drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import robot.Robot;
import robot.subsystems.drivetrain.commands.JoystickDrive;
import robot.subsystems.drivetrain.pure_pursuit.Point;

/**
 * Add your docs here.
 */


public class Drivetrain extends Subsystem {
    public final TalonSRX leftForward = new TalonSRX(Ports.leftForwardMotor);
    private final VictorSPX leftBack = new VictorSPX(Ports.leftBackMotor);
    public final TalonSRX rightForward = new TalonSRX(Ports.rightForwardMotor);
    private final VictorSPX rightBack = new VictorSPX(Ports.rightBackMotor);
    private final Encoder leftEncoder = new Encoder(Ports.leftEncoderChannelA, Ports.leftEncoderChannelB);
    private final Encoder rightEncoder = new Encoder(Ports.rightEncoderChannelA, Ports.rightEncoderChannelB);
    public Point currentLocation;
    public int rightForwardPos = rightForward.getSensorCollection().getPulseWidthPosition();
    public int leftForwardPos = leftForward.getSensorCollection().getPulseWidthPosition();

    public Drivetrain() {
        leftEncoder.setDistancePerPulse(Constants.PULSE_PER_DISTANCE);
        rightEncoder.setDistancePerPulse(Constants.PULSE_PER_DISTANCE);
        leftForward.setInverted(Constants.LEFT_REVERSED);
        rightForward.setInverted(Constants.RIGHT_REVERSED);
//configure the victors constants
        leftForward.config_kP(0, Constants.kP, Constants.TimeOutMS);
        leftForward.config_kD(0, Constants.kD, Constants.TimeOutMS);
        leftForward.config_kI(0, Constants.kI, Constants.TimeOutMS);
        leftForward.config_kF(0, Constants.kF, Constants.TimeOutMS);
        leftForward.setSensorPhase(Constants.LEFT_REVERSED);
        rightForward.config_kP(0, Constants.kP, Constants.TimeOutMS);
        rightForward.config_kD(0, Constants.kD, Constants.TimeOutMS);
        rightForward.config_kI(0, Constants.kI, Constants.TimeOutMS);
        rightForward.config_kF(0, Constants.kF, Constants.TimeOutMS);
        rightForward.setSensorPhase(Constants.RIGHT_REVERSED);

        //configure victors to follow  Talon
        rightBack.follow(rightForward);
        leftBack.follow(leftBack);

        //config sensors to Talons
        rightForward.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, Constants.TimeOutMS);
        leftForward.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, Constants.TimeOutMS);



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
        leftForward.set(ControlMode.PercentOutput, speed);
        leftBack.set(ControlMode.PercentOutput, speed);
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
        rightForward.set(ControlMode.PercentOutput, speed);
        rightBack.set(ControlMode.PercentOutput, speed);
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