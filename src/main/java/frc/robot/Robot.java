// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.HttpCamera;
import edu.wpi.first.net.WebServer;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  public static final RobotContainer m_robotContainer = new RobotContainer();

  public static boolean kUseLimelight = true;

  public Robot() {
        LimelightHelpers.setLEDMode_ForceOff(Constants.VisionConstants.limelightName);
        HttpCamera frontCam = new HttpCamera("FrontCam", "http://10.48.59.11:5800");
        CameraServer.addCamera(frontCam);
        HttpCamera backCam = new HttpCamera("BackCam", "http://10.48.59.12:5800");
        CameraServer.addCamera(backCam);
        WebServer.start(5800, Filesystem.getDeployDirectory().getPath());
  }

  public static RobotContainer getInstance(){
    return m_robotContainer;
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run(); 
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void disabledExit() {}

  @Override
  public void autonomousInit() {
    kUseLimelight = true;  
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void autonomousExit() {
    kUseLimelight = true;
    // Robot.getInstance().m_vision.tempDisable = false;
  
  }

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
      LimelightHelpers.setLEDMode_ForceOff(Constants.VisionConstants.limelightName);

    }
  }

  @Override
  public void teleopPeriodic() {}

  @Override
  public void teleopExit() {}

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}

  @Override
  public void testExit() {}

  @Override
  public void simulationPeriodic() {}
}
