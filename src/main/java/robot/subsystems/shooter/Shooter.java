/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {
    private final VictorSPX victorMotor = new VictorSPX(Ports.victorMotor);
    private final TalonSRX talonMotor = new TalonSRX(Ports.talonMotor);

    public Shooter(){
        talonMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, Constants.kPIDLoopIdx, 0); //Turns the units of the talon to correspond to 1440 encoder units.

        talonMotor.configNominalOutputForward(0, Constants.kTimeoutMs); //nominal motor speed
        talonMotor.configNominalOutputReverse(0, Constants.kTimeoutMs);
        talonMotor.configPeakOutputForward(1, Constants.kTimeoutMs); //the motor cannot exceed this value
        talonMotor.configPeakOutputReverse(-1, Constants.kTimeoutMs);

        /* set closed loop gains in slot0 */
        talonMotor.config_kP(Constants.kPIDLoopIdx, Constants.kP, Constants.kTimeoutMs);
        talonMotor.config_kI(Constants.kPIDLoopIdx, Constants.kI, Constants.kTimeoutMs);
        talonMotor.config_kD(Constants.kPIDLoopIdx, Constants.kD, Constants.kTimeoutMs);
        talonMotor.config_kF(Constants.kPIDLoopIdx, Constants.kF, Constants.kTimeoutMs);
    }
    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
}