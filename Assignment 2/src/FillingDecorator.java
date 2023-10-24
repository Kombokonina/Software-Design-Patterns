public class FillingDecorator implements ServiceDecorator {
    private DentistryService service;
    public FillingDecorator(DentistryService service) {
        this.service = service;
    }
    @Override
    public void performService() {
        service.performService();
        System.out.println("Performing additional service: Filling");
    }
    @Override
    public String getDescription() {
        return "Filling";
    }
}
