// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import org.photonvision.PhotonCamera;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {

  public static final double kWheelDiameterMeters = Units.inchesToMeters(6.0);

  public static final double kMaxSpeedMetersPerSecond = Units.feetToMeters(15.0);  // TODO: Set this to the max speed of the robot

  private double maxSpeedPercent = 1.0;

  private final DriveWheelsIO driveWheelsIO;

  PhotonCamera camera;

  public DriveSubsystem(DriveWheelsIO driveWheelsIO) {
    this.driveWheelsIO = driveWheelsIO;

    camera = new PhotonCamera("VisionCam");
  }


  public void drive(double leftSpeedPercent, double rightSpeedPercent) {

    driveWheelsIO.drive(leftSpeedPercent * maxSpeedPercent, rightSpeedPercent * maxSpeedPercent);

    SmartDashboard.putNumber("Left Speed Percent", leftSpeedPercent * maxSpeedPercent);
    SmartDashboard.putNumber("Right Speed Percent", leftSpeedPercent * maxSpeedPercent);
  }

  public void changeMaxSpeedPercent() {

  }

  @Override
  public void periodic() {
    // Get the camera
    
    // Get the latest result
    var result = camera.getLatestResult();
    // Check for targets
    if (result.hasTargets()) {
      System.out.println("Found a target");
      // Get the best target
      var target = result.getBestTarget();
      // Get target info
      var yaw = target.getYaw();
      var pitch = target.getPitch();
      var camToTarget = target.getBestCameraToTarget();
      SmartDashboard.putNumber("Target yaw", yaw);
      SmartDashboard.putNumber("Target pitch", pitch);
      SmartDashboard.putString("Target transform", camToTarget.toString());

      SmartDashboard.putBoolean("Targeting april tag", true);
    } else {
      SmartDashboard.putBoolean("Targeting april tag", false);
    }
  }

}