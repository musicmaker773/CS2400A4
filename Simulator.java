import java.util.Random;

public class Simulator {
    private Random random;
    private int totalTimeWaitedToLand;
    private int timeWaitedToLand;
    private int longestTimeWaitedToLand;
    private int totalTimeWaitedToFly;
    private int timeWaitedToFly;
    private int longestTimeWaitedToFly;
    private int planesCrashed;
    private int planesLanded;
    private int planesTookOff;


    private QueueInterface<Airplane> takeOffq;
    private LinkedPriorityQueue<Airplane> priorityq;

    public Simulator()
    {
        takeOffq = new LinkedQueue<>();
        priorityq = new LinkedPriorityQueue<>();
        random = new Random();
        totalTimeWaitedToLand = 0;
        timeWaitedToLand = 0;
        longestTimeWaitedToLand = 0;
        totalTimeWaitedToFly = 0;
        timeWaitedToFly = 0;
        longestTimeWaitedToFly = 0;
        planesCrashed = 0;
        planesLanded = 0;
        planesTookOff = 0;
    }
    public void update(int currentTime) {
        int choose = random.nextInt(6) + 1;
        Airplane entry = new Airplane(currentTime);
        if (choose == 1) {
            takeOffq.enqueue(entry);
        }
        if (choose == 2) {
            priorityq.enqueue(entry);
        }

        if(!priorityq.isEmpty()) {
            priorityq.updateValues();
        }

        if (!priorityq.isEmpty() && (priorityq.getFront().getFuel() > 3)) {


            if ((!takeOffq.isEmpty() && (takeOffq.getFront().getWaitTimeToFly() == true)) ) {
                timeWaitedToFly = currentTime - takeOffq.getFront().getTimeArrived();
                if (longestTimeWaitedToFly < timeWaitedToFly) {
                    longestTimeWaitedToFly = timeWaitedToFly;
                }
                totalTimeWaitedToFly = timeWaitedToFly + totalTimeWaitedToFly;
                takeOffq.dequeue();
                planesTookOff++;
                return;
            }
        }
        else if (priorityq.isEmpty()) {
            if (!takeOffq.isEmpty() && (takeOffq.getFront().getWaitTimeToFly() == true)) {
                timeWaitedToFly = currentTime - takeOffq.getFront().getTimeArrived();
                if (longestTimeWaitedToFly < timeWaitedToFly) {
                    longestTimeWaitedToFly = timeWaitedToFly;
                }
                totalTimeWaitedToFly = timeWaitedToFly + totalTimeWaitedToFly;
                takeOffq.dequeue();
                planesTookOff++;
                return;
            }
        }
        if (!priorityq.isEmpty() && (priorityq.getFront().crashStatus() == false) && (priorityq.getFront().getLandingTime())) {
            timeWaitedToLand = currentTime - priorityq.getFront().getTimeArrived();
            if (longestTimeWaitedToLand < timeWaitedToLand) {
                longestTimeWaitedToLand = timeWaitedToLand;
            }
            totalTimeWaitedToLand = timeWaitedToLand + totalTimeWaitedToLand;
            priorityq.dequeue();
            planesLanded++;
            return;
        }

        if (!priorityq.isEmpty() && (priorityq.getFront().crashStatus() == true)) {
            priorityq.dequeue();
            planesCrashed++;
        }
    }
    public int getLongestTimeWaitedToLand() {
        return longestTimeWaitedToLand;
    }
    public int getAverageTimeWaitedToLand() {
        if (planesLanded == 0) {
            return 0;
        }
        return totalTimeWaitedToLand / planesLanded;
    }
    public int getLongestTimeWaitedToFly() {
        return longestTimeWaitedToFly;
    }
    public int getAverageTimeWaitedToFly() {
        if (planesTookOff == 0) {
            return 0;
        }
        return totalTimeWaitedToFly / planesTookOff;
    }
    public int getPlanesCrashed() {
        return planesCrashed;
    }

    public int getPlanesLanded() {
        return planesLanded;
    }

    public int getPlanesTookOff() { return planesTookOff; }
}
