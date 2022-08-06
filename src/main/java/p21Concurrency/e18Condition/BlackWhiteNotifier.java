package p21Concurrency.e18Condition;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static p21Concurrency.e18Condition.Action.Color.BLACK;
import static p21Concurrency.e18Condition.Action.Color.WHITE;

class BlackWhiteNotifier implements Runnable {

    private Lock lock = new ReentrantLock();
    private Condition blackAwakeCondition = lock.newCondition();
    private Condition whiteAwakeCondition = lock.newCondition();
    private Condition actionCompleteCondition = lock.newCondition();

    private List<Action> blackActions;
    private List<Action> whiteActions;

    private int numberOfCompletedBlackActions;
    private int numberOfCompletedWhiteActions;

    BlackWhiteNotifier(int numberOfBlackActions, int numberOfWhiteActions) {
        numberOfCompletedBlackActions = numberOfBlackActions;
        numberOfCompletedWhiteActions = numberOfWhiteActions;

        blackActions = new ArrayList<>(numberOfBlackActions);
        whiteActions = new ArrayList<>(numberOfWhiteActions);
        for(int i = 0; i < numberOfBlackActions; i++) {
            blackActions.add(Action.newAction(i, this, BLACK));
        }
        for(int i = 0; i < numberOfWhiteActions; i++) {
            whiteActions.add(Action.newAction(i, this, WHITE));
        }
    }

    void executeActions(ExecutorService executorService) {
        blackActions.forEach(executorService::execute);
        whiteActions.forEach(executorService::execute);
    }

    Lock getLock() {
        return lock;
    }

    Condition getBlackAwakeCondition() {
        return blackAwakeCondition;
    }

    Condition getWhiteAwakeCondition() {
        return whiteAwakeCondition;
    }

    Condition getActionCompleteCondition() {
        return actionCompleteCondition;
    }

    void incrementNumberOfCompletedBlackActions() {
        numberOfCompletedBlackActions++;
    }

    void incrementNumberOfCompletedWhiteActions() {
        numberOfCompletedWhiteActions++;
    }

    private void waitAllActionsCompleted() throws InterruptedException {
        lock.lock();
        try {
            while (numberOfCompletedBlackActions < blackActions.size() ||
                    numberOfCompletedWhiteActions < whiteActions.size()) {
                actionCompleteCondition.await();
            }
        } finally {
            lock.unlock();
        }
    }

    private void startBlackActions() {
        lock.lock();
        try {
            numberOfCompletedBlackActions = 0;
            blackActions.forEach(a->a.setAwaken(true));
            blackAwakeCondition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    private void startWhiteActions() {
        lock.lock();
        try {
            numberOfCompletedWhiteActions = 0;
            whiteActions.forEach(a->a.setAwaken(true));
            whiteAwakeCondition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                waitAllActionsCompleted();
                if (Action.Color.random() == BLACK) {
                    System.out.println("STARTING BLACK ACTIONS!");
                    startBlackActions();
                } else {
                    System.out.println("STARTING WHITE ACTIONS!");
                    startWhiteActions();
                }
            }
        } catch (InterruptedException e) {
            System.out.println(getClass().getSimpleName() + " interrupted!");
        }
    }
}
