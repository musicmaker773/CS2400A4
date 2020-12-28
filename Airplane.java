import java.util.Random;

public class Airplane implements Comparable<Airplane>, Updatable<Airplane> {
    Random random = new Random();
    private int fuel;
    private int landingTime;
    private int timeArrived;
    private int waitTimeToFly;

    public Airplane(int timeArrived)
    {
        fuel = random.nextInt(5) + 11;
        this.timeArrived = timeArrived;
        setLandingTime();
        waitTimeToFly = 3;
    }
    public int getFuel() {
        return fuel;
    }
    public boolean getLandingTime() {
        landingTime--;
        return landingTime <= 0;
    }
    public int getTimeArrived() {
        return timeArrived;
    }
    public boolean getWaitTimeToFly() {

        waitTimeToFly--;
        return waitTimeToFly <= 0;
    }
    public void setLandingTime() {
        landingTime = 2;
    }

    @Override
    public int compareTo(Airplane other) {
        int result = 0;
        if (fuel < other.getFuel()) {
            result = -1;
        }
        if (fuel > other.getFuel()) {
            result = 1;
        }
        if (fuel == other.getFuel()) {
            result = 0;
        }
        return result;

    /*    int result = 0;
        if (price < other.getPrice()) {
            result = -1;
        }
        else if (price > other.getPrice()) {
            result = 1;
        }
        else  {
            result = 0;
        }
        return result;
    */
    }
    public boolean crashStatus()
    {
        return fuel <= 0;
    }

    @Override
    public void update() {
        fuel--;
    }
}
