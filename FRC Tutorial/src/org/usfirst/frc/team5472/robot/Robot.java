
package org.usfirst.frc.team5472.robot;

import org.usfirst.frc.team5472.robot.commands.BasicAutonomousCommand;
import org.usfirst.frc.team5472.robot.commands.NullAutonomousCommand;
import org.usfirst.frc.team5472.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

/**
 * This class serves as the base of the code. It contains all the methods that are called at key places in the robot's life cycle
 * It contains methods that are called:
 *   When the robot turns on or the code starts up (from being restarted or from crashing): robotInit()
 *   When the robot is disabled (manually or by emergency): disabledInit()
 *   Continuously while the robot is disabled: disabledPeriodic()
 *   When the robot is enabled in teleoperated mode: teleopInit()
 *   Continuously while the robot is enabled and in teleoperated mode: teleopPeriodic()
 *   When the robot is enabled in autonomous mode: autonomousInit()
 *   Continuously while the robot is enabled and in autonomous mode: autonomousPeriodic()
 *
 * It also provides a method for defining the robot's behavior when enabled in test mode
 *   The LiveWindow.run() method updates values sent to the live window. This allows speedcontroller speed values and other data to be sent to the driver station
 *   It is roughly equivalent in function to SmartDashboard, as they both use the NetworkTables framework as a base
 */
public class Robot extends IterativeRobot {

	public static final DriveSubsystem driveSubsystem = new DriveSubsystem();
	public static OI oi;

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();


	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = chooser.getSelected();
		autonomousCommand.start(); //This will either start the NullAutonomousCommand (does nothing) or the BasicAutonomousCommand (Drives forward for 3 seconds)
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void robotInit() {
		oi = new OI();
		chooser.addDefault("Drive Straight", new BasicAutonomousCommand());
		chooser.addObject("No Auto", new NullAutonomousCommand());
	}

	@Override
	public void teleopInit() {
		//Failing to cancel an autonomous command will result in the command cutting into teleoperated time in a competition
		//The robot will remain in autonomous mode and prevent the use of joysticks until the autonomous command ends of its on volition, that is, if it ever does
		//Although the robot is acting autonomously, it will still receive point values as if it were being operated by drivers.
		autonomousCommand.cancel();
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
