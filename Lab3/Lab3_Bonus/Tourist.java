public class Tourist {
    private String name;
    private TravelPlan plan;

    public Tourist(String name, TravelPlan plan) {
        this.name = name;
        this.plan = plan;
    }
    public  void showScheduleTourist(){
        System.out.println(name + " are programul: ");
        plan.showSchedule();
    }
}
