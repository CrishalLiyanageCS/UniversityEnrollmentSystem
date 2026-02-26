package universityenrollmentsystem;

public class ResearchAssistant extends Person{
    private String projectTitle;
    private int hoursPerWeek;
    String supervisorName;

    public ResearchAssistant(String name,String surname){
        super(name,surname);
    }

    //setter methods
    public void setProjectTitle(String projectTitle){
        this.projectTitle = projectTitle;
    }
    public void setHoursPerWeek(int hoursPerWeek){
        this.hoursPerWeek = hoursPerWeek;
    }
    public void setSupervisorName(String supervisorName){
        this.supervisorName = supervisorName;
    }

    //getter methods
    public String getProjectTitle(){
        return projectTitle;
    }
    public int getHoursPerWeek(){
        return hoursPerWeek;
    }
    public String getSupervisorName(){
        return supervisorName;
    }

    @Override
    public String toString() {
        return super.toString()+ "[Research Assistance -"
                +"Project Title : "+projectTitle
                +",Hours Per Week : "+hoursPerWeek
                +",Supervisor Name :"+supervisorName +"]";
    }
}
