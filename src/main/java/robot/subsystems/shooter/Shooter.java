/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {
    private final VictorSPX victorMotor = new VictorSPX(Ports.victorMotor);
    private final TalonSRX talonMotor = new TalonSRX(Ports.talonMotor);
    private final Encoder encoder = new Encoder(Ports.encoderChannelA, Ports.encoderChannelB);
    public Shooter(){
        encoder.setDistancePerPulse(Constants.DISTANCE_PER_PULSE);

        //what the motor does when not given voltage (Brake - decelerate the motor, Coast - not stop the motor)
        victorMotor.setNeutralMode(NeutralMode.Coast);
        talonMotor.setNeutralMode(NeutralMode.Coast);

        //Turns the units of the talon to correspond to 1440 encoder units.
        talonMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, Constants.kPIDLoopIdx, 0);

        talonMotor.configNominalOutputForward(0, Constants.kTimeoutMs); //nominal motor speed
        talonMotor.configNominalOutputReverse(0, Constants.kTimeoutMs);
        talonMotor.configPeakOutputForward(1, Constants.kTimeoutMs); //the motor cannot exceed this value
        talonMotor.configPeakOutputReverse(-1, Constants.kTimeoutMs);

        /* set closed loop gains in slot0 */
        talonMotor.config_kP(Constants.kPIDLoopIdx, Constants.kP, Constants.kTimeoutMs);
        talonMotor.config_kI(Constants.kPIDLoopIdx, Constants.kI, Constants.kTimeoutMs);
        talonMotor.config_kD(Constants.kPIDLoopIdx, Constants.kD, Constants.kTimeoutMs);
        talonMotor.config_kF(Constants.kPIDLoopIdx, Constants.kF, Constants.kTimeoutMs);

        victorMotor.follow(talonMotor);
    }
    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    /**
     * Set the fly wheel speed to a number between -1 and 1.
     * @param speed number from -1 to 1
     */
    public void setMotorSpeed(double speed){
        talonMotor.set(ControlMode.PercentOutput, speed);

    }

    /**
     * set the motor rotations per minute
     * @param rpm rotations of the wheel per minute
     */
    public void setMotorRPM(double rpm){
        talonMotor.set(ControlMode.Velocity, 1440 * rpm / 600); //TODO: make sure the calculations are correct
    }

    /**
     * set the velocity of the wheel speed
     * @param velocity wheel tangent speed in m/s
     */
    public void setMotorVelocity(double velocity){
        //convert the velocity to rps/10        m/s  *  2 *   PI    *           r     *  units/rotation / millisecond/second
        talonMotor.set(ControlMode.Velocity, velocity * 2 * Math.PI * Constants.WHEEL_RADIUS * 1440 / 10);
    }

    /**
     * Returns the encoder rate
     * @return velocity of the wheel based on the encoder.
     */
    public double getVelocity(){
        return encoder.getRate();
    }

}