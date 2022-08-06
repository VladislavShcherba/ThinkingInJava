package p21Concurrency.e18Condition;

class Black extends Action {

    Black(int id, BlackWhiteNotifier notifier) {
        super(id, notifier);
    }

    @Override
    protected void waitAwaken() throws InterruptedException {
        getNotifier().getLock().lock();
        try {
            while(!isAwaken()) {
                getNotifier().getBlackAwakeCondition().await();
            }
        } finally {
            getNotifier().getLock().unlock();
        }
    }

    @Override
    protected void actionComplete() {
        getNotifier().getLock().lock();
        try {
            setAwaken(false);
            getNotifier().incrementNumberOfCompletedBlackActions();
            getNotifier().getActionCompleteCondition().signal();
        } finally {
            getNotifier().getLock().unlock();
        }
    }

    @Override
    public String toString() {
        return "BlackAction[" + getId() + "]";
    }
}