/*
 Keanu:
 * This is the default intake command and will run during teleop since
 * it is the default command of clawSystem. Hopefully you're picking up
 * on how this works now.
 */

package frc.team364.robot.commands.teleop;

import edu.wpi.first.wpilibj.command.Command;
import frc.team364.robot.Robot;

public class TeleopClawCommand extends Command {

    // Variables for claw toggle
    private int pincherState;
    private boolean pincherLatch;

    public TeleopClawCommand() {
        requires(Robot.clawSystem);
        pincherState = 0;
        pincherLatch = false;
    }

    @Override
    protected void execute() {
       
        // This is a toggle algorithm. If the button is pressed, it will
        // set the mechanism to a certain state on the first loop when the
        // button is pressed. Each loop afterwards will turn the solenoid off
        // and wait until the button is depressed.

        if(Robot.oi.pinchButton.get()) {
            if(!pincherLatch) {
                if(pincherState == 0) {
                    Robot.clawSystem.openPincher();
                    pincherLatch = true;
                    pincherState = 1;
                } else {
                    Robot.clawSystem.closePincher();
                    pincherLatch = true;
                    pincherState = 0;
                }

            }
        } else {
            Robot.clawSystem.pincherOff();
            pincherLatch = false;
        }

    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Robot.clawSystem.pincherOff();
    }

}
