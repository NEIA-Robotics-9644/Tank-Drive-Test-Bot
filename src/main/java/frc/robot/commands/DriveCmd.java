// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveSubsystem;

import java.util.function.Supplier;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class DriveCmd extends Command {

  private final DriveSubsystem driveSubsystem;
  Supplier<Double> forward;
  Supplier<Double> rotation;

  public DriveCmd(DriveSubsystem subsystem, Supplier<Double> forward, Supplier<Double> rotation) {
    driveSubsystem = subsystem;
    this.forward = forward;
    this.rotation = rotation;
    addRequirements(driveSubsystem);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Get the inputs
    double forward = -this.forward.get();
    double rotation = -this.rotation.get();

    // Apply deadband
    forward = MathUtil.applyDeadband(forward, 0.03);
    rotation = MathUtil.applyDeadband(rotation, 0.03);

    // Square the inputs (while preserving the sign) to increase fine control
    forward = Math.copySign(forward * forward, forward);
    rotation = Math.copySign(rotation * rotation, rotation);

    // Calculate the drive output
    double leftOutput = forward + rotation;
    double rightOutput = forward - rotation;

    // Normalize the drive output so that no wheel exceeds 1.0
    double maxMagnitude = Math.max(Math.abs(leftOutput), Math.abs(rightOutput));
    if (maxMagnitude > 1.0) {
      leftOutput /= maxMagnitude;
      rightOutput /= maxMagnitude;
    }

    // Convert to meters per second
    leftOutput *= DriveSubsystem.kMaxSpeedMetersPerSecond;
    rightOutput *= DriveSubsystem.kMaxSpeedMetersPerSecond;

    driveSubsystem.drive(leftOutput, rightOutput);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
