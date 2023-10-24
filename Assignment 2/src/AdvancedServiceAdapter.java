public class AdvancedServiceAdapter implements AdvancedDentistryService {
    private DentistryService service;

    public AdvancedServiceAdapter(DentistryService service) {
        this.service = service;
    }

    @Override
    public void performService() {
    }

    @Override
    public void performAdvancedService() {
        System.out.println("Performing Advanced Diagnostics");
    }
}
