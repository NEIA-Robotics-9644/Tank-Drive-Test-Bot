package frc.robot.subsystems;

import java.util.Collections;
import java.util.List;

import org.photonvision.PhotonCamera;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.Units;
import edu.wpi.first.math.geometry.Translation2d;

import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PoseEstimator extends SubsystemBase{
    
    private final PhotonCamera photonCamera;
    private final DriveSubsystem drivetrainSubsystem;

    private static final List<Pose3d> targetPoses = Collections.unmodifiableList(List.of(
        new Pose3d(3.0, 1.165, 0.287 + 0.165, new Rotation3d(0, 0, Math.PI)),
        new Pose3d(3.0, 0, 0.287 + 0.165, new Rotation3d(0, 0, Math.PI))
    ));

    private static final Vector<N7> stateStdDevs = vecBuilder.fill(0.05, 0.05, )
}
