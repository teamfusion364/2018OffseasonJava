package frc.team364.robot.commands.auto.misc;

import edu.wpi.first.wpilibj.command.Command;


public class WaitCommand extends Command {

    /**
     * WaitCommand()
     * waits for a designated period of time
     * @param time time in seconds
     */
    public WaitCommand(double time) {
        setTimeout(time);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
    }

    @Override
    protected boolean isFinished() {
        return isTimedOut();
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
        super.interrupted();
    }
}
