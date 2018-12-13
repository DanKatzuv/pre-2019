/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package robot.subsystems.TurretTurn;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.command.Subsystem;
import robot.subsystems.TurretTurn.commands.TurnByAngle;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Turret extends Subsystem {
    public final TalonSRX Motor = new TalonSRX(Ports.Motor);
    private final AnalogPotentiometer potentiometer = new AnalogPotentiometer(Ports.Potentiometer, Constants.VOLT_DEGREE_PROPORTION);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new TurnByAngle(0, true));
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    /**
     * the method uses a talon controlMode to set the amount of distance it needs to turn to get to the desired position
     *
     * @param absoluteAngleToTurn the angle the motors need to turn, the degree we want to turn in
     * @author lior
     */
    public void setDesiredAngle(double absoluteAngleToTurn) {
        if (absoluteAngleToTurn >= Constants.MAX_DEGREE)
            absoluteAngleToTurn = Constants.MAX_DEGREE - (Constants.MAX_DEGREE - absoluteAngleToTurn) % 360;
        if (absoluteAngleToTurn <= Constants.MIN_DEGREE)
            absoluteAngleToTurn = Constants.MIN_DEGREE + 360 - (Constants.MIN_DEGREE - absoluteAngleToTurn) % 360;
        Motor.set(ControlMode.MotionMagic, (Constants.SHOOTER_BASE_PERIMITER * Math.PI) * absoluteAngleToTurn / 360);
    }

    /**
     * the method uses the input scale calculated with the maximum voltage possible and the maximum degreees to calculate the current angle
     *
     * @author Lior
     */
    public double getAngle() {
        return potentiometer.get();
    }


}