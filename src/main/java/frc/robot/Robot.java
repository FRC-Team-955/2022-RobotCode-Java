// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;

import edu.wpi.first.wpilibj.Timer;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj.XboxController;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */

  CANSparkMax motor = new CANSparkMax(0, MotorType.kBrushless);
  RelativeEncoder encoder = motor.getEncoder();
  int state;
  Timer timer = new Timer();
  XboxController Xbox = new XboxController(0);
  Elevator elevator = new Elevator(0); //check port before running
  Shooter shooter = new Shooter(8, 7);
  Intake intake = new Intake();


  @Override
  public void robotInit() {}

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {
    // lowerIntake();
    // state = 0;
    // encoder.setPosition(0);
  }

  @Override
  public void autonomousPeriodic() {
    //two ball auto
    /*
    if(state == 0) {
      motor.set(0.5);
      if(encoder.getPosition() > 70) {
        motor.set(0);
        state++;
      }
    } else if(state == 1) {
      spinIntake();
      if(detectBalls() == 2) state++;
    } else if(state == 2) {
      if(align()) state++;
    } else if(state == 3) {
      shoot();
      if(detectBalls() == 0) {
        timer.reset();
        state++;
      }
    } else if(state == 4) {
      shoot();
      if(timer.get() > 0.5) {
        state++;
      }
    }
    */
  }

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
    intake.open();
  
    //elevator test code
    /*
    if(state == 0) {
      if(elevator.resetElevator()) state++;
      if(Xbox.getBButtonPressed()) {
        elevator.moveElevator(0);
        state++;
      }
    }
    if(Xbox.getAButton()) {
      elevator.setOverride(!elevator.getOverride());
    }
    elevator.moveElevator(Xbox.getLeftY());
    */
    //Shooter test code
    shooter.setShooterRPMs(1000, 1000);
    if(Xbox.getAButtonPressed()) {
      shooter.switchShooterSolenoid();
    }
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
