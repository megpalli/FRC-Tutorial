package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class JoystickDriveCommand extends Command {
	//See BasicAutonomousCommand.java for comments focused on methods inherited from the Command class

	Joystick joystick = Robot.oi.getJoystick();

	@Override
	public void execute(){
		//From the joystick, take the y value and use it to determine how quickly the robot will drive
		//The value returned by this method is within the range [-1, 1]
		//On most joysticks, the returned y value will be negative when the stick is pushed away from the user and the x will be positive when the stick is pushed to the right
		double y = joystick.getY();

		//The twist value will be used to calculate how much the robot should turn
		//The value returned by this method is within the range [-1, 1]
		//On most joysticks, the returned z value will be positive when the stick is twisted clockwise (to the right)
		double twist = joystick.getTwist();

		//A simple calculation for the resulting motor speeds

		double leftMotorSpeed = y + twist;
		double rightMotorSpeed = y - twist;

		//On a tank drive train, having one of the robot's sides move faster than the other will result in a turn
		//Subtracting a value from the right side while adding it to the left results in a right turn
		//When twist is negative, a value is subtracted from the left and added to the right, resulting in a left turn
		//This is a rather simple method of calculating motor values. Often, teams will introduce a non-linear transformation to the y-value and the twist value
		//  to provide the drivers with increased accuracy at low speeds

		Robot.driveSubsystem.set(leftMotorSpeed, rightMotorSpeed);
	}

	@Override
	protected boolean isFinished() {
		//This method will run continuously in teleop mode, so this will be left alone
		return false;
	}
}
