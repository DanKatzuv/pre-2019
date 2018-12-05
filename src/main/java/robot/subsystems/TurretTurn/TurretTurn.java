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
import edu.wpi.first.wpilibj.interfaces.Potentiometer;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class TurretTurn extends Subsystem {
    private final TalonSRX frontMotor = new TalonSRX(Ports.FrontMotor);
    private final TalonSRX backMotor = new TalonSRX(Ports.BackMotor);
    private static final AnalogPotentiometer potentiometer = new AnalogPotentiometer(Ports.Potentiometer, Constants.VOLT_DEGREE_PROPORTION);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
}