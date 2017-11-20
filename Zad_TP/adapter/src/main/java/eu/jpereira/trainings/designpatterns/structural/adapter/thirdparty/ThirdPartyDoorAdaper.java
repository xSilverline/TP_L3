package eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty;

import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.CodeMismatchException;
import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.IncorrectDoorCodeException;
import eu.jpereira.trainings.designpatterns.structural.adapter.model.Door;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotChangeCodeForUnlockedDoor;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotChangeStateOfLockedDoor;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotUnlockDoorException;

public class ThirdPartyDoorAdaper extends ThirdPartyDoor implements Door
{
    @Override
    public void open(String code) throws IncorrectDoorCodeException
    {

        if(code == getCode())
            try {
                unlock(code);
                setState(DoorState.OPEN);
            } catch (CannotUnlockDoorException e) {
                e.printStackTrace();
            } catch (CannotChangeStateOfLockedDoor cannotChangeStateOfLockedDoor) {
                cannotChangeStateOfLockedDoor.printStackTrace();
            }
            else
                throw new IncorrectDoorCodeException();

    }

    @Override
    public void close()
    {
        try {
            setState(DoorState.CLOSED);
        } catch (CannotChangeStateOfLockedDoor cannotChangeStateOfLockedDoor) {
            cannotChangeStateOfLockedDoor.printStackTrace();
        }
        lock();

    }

    @Override
    public boolean isOpen() {
        if (getLockStatus() == LockStatus.UNLOCKED) {
            if (getState() == DoorState.OPEN)
                return true;
            else
                return false;
        }
        return false;
    }

    @Override
    public void changeCode(String oldCode, String newCode, String newCodeConfirmation) throws IncorrectDoorCodeException, CodeMismatchException
    {
        if(getLockStatus() == LockStatus.LOCKED) {
            if (oldCode == getCode()) {
                if (newCode == newCodeConfirmation)
                    try {
                        setNewLockCode(newCode);
                    } catch (CannotChangeCodeForUnlockedDoor cannotChangeCodeForUnlockedDoor) {
                        cannotChangeCodeForUnlockedDoor.printStackTrace();
                    }
                else
                    throw new CodeMismatchException();
            }
            else
            throw new IncorrectDoorCodeException();
        }

    }

    @Override
    public boolean testCode(String code)
    {
        if(code == getCode())
            return true;
        else
            return false;
    }
}
