package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;
import org.usfirst.frc.team5472.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class BasicAutonomousCommand extends Command {
	//This command tells the robot to drive forward for a given amount of time during autonomous
	//It makes use of WPILIB's Timer class to perform delays

	//This variable exists to provide a return value for the isFinished method.
	//It will be assigned the value 'true' when the robot has driven for n seconds
	private boolean finished = false;
	
	//Allows the use of various motor-controlling methods
	DriveSubsystem ds = Robot.driveSubsystem; //Do not create a second instance of a Subsystem
	
	@Override
	public void execute(){
		ds.set(0.4, 0.4); //40% on both the left and right sides
		Timer.delay(3.000); //Have the robot drive for 3 seconds using a 3 second delay
		ds.set(0.0, 0.0); //Stop the robot by turning the motors off
		finished = true; //Allow the command to end
	}

	@Override
	public void initialize(){}

	@Override
	protected boolean isFinished() {
		//Its a bad idea to simply write 'return true;' here; it would result in the command doing nothing
		//Likewise, 'return false;' will result in the command not ending until it is interrupted, also unfavorable
		return finished;
	}
	
	@Override
	public void interrupted(){
		ds.set(0.0, 0.0); //In the event that this command gets interrupted, ensure that the robot does not drive forward indefinitely
	}
}
