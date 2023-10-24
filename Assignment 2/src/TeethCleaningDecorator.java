public class TeethCleaningDecorator implements ServiceDecorator {
    private DentistryService service;
    public TeethCleaningDecorator(DentistryService service) {
        this.service = service;
    }
    @Override
    public void performService() {
        service.performService();
        System.out.println("Performing additional service: Teeth Cleaning");
    }
    @Override
    public String getDescription() {
        return "Teeth Cleaning";
    }
}
