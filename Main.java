public class Main {

    public static void main(String[] args){
        Simulator simulator = new Simulator();
        for (int i = 0; i < 1500; i++)
        {
            simulator.update(i);
        }
        System.out.println("Longest Time Waited To Land: " + simulator.getLongestTimeWaitedToLand());
        System.out.println("Average Time Waited To Land: " + simulator.getAverageTimeWaitedToLand());
        System.out.println("Longest Time Waited To Fly: " + simulator.getLongestTimeWaitedToFly());
        System.out.println("Average Time Waited To Fly: " + simulator.getAverageTimeWaitedToFly());
        System.out.println("Planes Crashed: " + simulator.getPlanesCrashed());
        System.out.println("Planes Landed: " + simulator.getPlanesLanded());
        System.out.println("Planes TookOff: " + simulator.getPlanesTookOff());
    }
}
