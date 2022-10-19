package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.DigitalInput;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;

public class Elevator {

    private DigitalInput bottomLimitSensor = new DigitalInput(0);
    private DigitalInput topLimitSensor = new DigitalInput(1);
    private TalonFX motor = new TalonFX(0);
    private boolean isElevatorReset = false;
    private boolean elevatorDriveOverride = false;
    private double kP = 0.00005;
    private double topSensorPosition = 300000; // Change this once we find how many ticks the top sensor is from the bottom

    public Elevator() {
        motor.setSelectedSensorPosition(0);
    }

    /*
    Resets the elevator, unless the stator gives a current of above 40 amps.
    Once it is reset, it sets the sensor position to 0
    Once it is reset or the stator current is above 40, isElevatorReset become true and the function returns true
    isElevatorReset is used as a nessacery condition to manually control the robot
    (maybe it is possible to make an override button?)
    Idea on how to use: loop in teleopInit or whatever function until it returns true
    Clarification on the current limit if used as above: it should break the loop and allow manual control of the elevator
    */
    public boolean resetElevator() {
        if (motor.getStatorCurrent() < 40) { // Check if its getStatorCurrent or getSupplyCurrent with electrical before running
            if (!bottomLimitSensor.get() && motor.getSelectedSensorPosition() < 20000) {
                motor.set(TalonFXControlMode.PercentOutput, 0.5);
                isElevatorReset = false;
                return false;
            } else if (!bottomLimitSensor.get() && motor.getSelectedSensorPosition() > 20000) {
                motor.setSelectedSensorPosition(30000);
                motor.set(TalonFXControlMode.PercentOutput, -0.5);
                isElevatorReset = false;
                return false;
            } else {
                motor.set(TalonFXControlMode.PercentOutput, 0);
                motor.setSelectedSensorPosition(0);
                isElevatorReset = true;
                return true;
            }
        } else {
            motor.set(TalonFXControlMode.PercentOutput, 0);
            isElevatorReset = true;
            return true;
        }
    }

    // allows override to the motor, ignoring the isElevatorReset boolean
    public void setOverride(boolean override) {
        elevatorDriveOverride = override;
    }

    public void moveElevator(double percentOutput) {
        if (motor.getStatorCurrent() < 40) { //Check if its stator current or supply current with electrical like above
            if (isElevatorReset == true || elevatorDriveOverride == true){
                if ((!topLimitSensor.get() || percentOutput < 0) && (motor.getSelectedSensorPosition() > 0 || percentOutput > 0)) {
                    if (percentOutput > 0) {
                        motor.set(TalonFXControlMode.PercentOutput, MathUtil.clamp(percentOutput * (topSensorPosition - motor.getSelectedSensorPosition() * kP),-1, 1));
                    } else {
                        motor.set(TalonFXControlMode.PercentOutput, MathUtil.clamp(percentOutput * motor.getSelectedSensorPosition() * kP, -1, 1));
                    }
                }
            }
        }
    }
}
