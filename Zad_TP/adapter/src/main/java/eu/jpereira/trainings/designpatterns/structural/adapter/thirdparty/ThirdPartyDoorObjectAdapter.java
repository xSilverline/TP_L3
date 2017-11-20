package eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty;

import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.CodeMismatchException;
import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.IncorrectDoorCodeException;
import eu.jpereira.trainings.designpatterns.structural.adapter.model.Door;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotChangeCodeForUnlockedDoor;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotChangeStateOfLockedDoor;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotUnlockDoorException;

public class ThirdPartyDoorObjectAdapter implements Door {

    private ThirdPartyDoor TPD = new ThirdPartyDoor();
    @Override
    public void open(String code) throws IncorrectDoorCodeException
    {
        if(code == TPD.getCode())
            try {
                TPD.unlock(code);
                TPD.setState(ThirdPartyDoor.DoorState.OPEN);
            } catch (CannotUnlockDoorException e) {
                e.printStackTrace();
            } catch (CannotChangeStateOfLockedDoor cannotChangeStateOfLockedDoor) {
                cannotChangeStateOfLockedDoor.printStackTrace();
            }
        else
            throw new IncorrectDoorCodeException();

    }

    @Override
    public void close() {
        try {
            TPD.setState(ThirdPartyDoor.DoorState.CLOSED);
        } catch (CannotChangeStateOfLockedDoor cannotChangeStateOfLockedDoor) {
            cannotChangeStateOfLockedDoor.printStackTrace();
        }
        TPD.lock();

    }

    @Override
    public boolean isOpen() {
        if (TPD.getLockStatus() == ThirdPartyDoor.LockStatus.UNLOCKED) {
            if (TPD.getState() == ThirdPartyDoor.DoorState.OPEN)
                return true;
            else
                return false;
        }
        return false;
    }

    @Override
    public void changeCode(String oldCode, String newCode, String newCodeConfirmation) throws IncorrectDoorCodeException, CodeMismatchException {
        if(TPD.getLockStatus() == ThirdPartyDoor.LockStatus.LOCKED) {
            if (oldCode == TPD.getCode()) {
                if (newCode == newCodeConfirmation)
                    try {
                        TPD.setNewLockCode(newCode);
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
    public boolean testCode(String code) {
        if(code == TPD.getCode())
            return true;
        else
            return false;
    }


}
