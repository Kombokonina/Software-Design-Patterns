import java.util.Scanner;

public class Main {
    public static final int BASIC_DIAGNOSTICS = 1;
    public static final int ADVANCED_DIAGNOSTICS = 2;
    public static final int TEETH_CLEANING = 1;
    public static final int FILLING = 2;
    public static final int BOTH = 3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int diagnosticType = getDiagnosticType(scanner);

        DentistryService basicService = selectDiagnostics(diagnosticType);

        int choice = getAdditionalServiceChoice(scanner);

        ServiceDecorator selectedService = selectAdditionalService(basicService, choice);

        confirmAdditionalService(selectedService, choice, scanner);

        performDiagnostics(basicService, selectedService);

        System.out.println("\nCongratulations! Your teeth are as good as new! Your bill: $100k");
    }

    private static int getDiagnosticType(Scanner scanner) {
        System.out.println("Choose type of diagnostics: \n" +
                BASIC_DIAGNOSTICS + " for Basic \n" +
                ADVANCED_DIAGNOSTICS + " for Advanced");
        return scanner.nextInt();
    }

    private static DentistryService selectDiagnostics(int diagnosticType) {
        DentistryService basicService;
        switch (diagnosticType) {
            case BASIC_DIAGNOSTICS:
                basicService = new BasicService();
                break;
            case ADVANCED_DIAGNOSTICS:
                basicService = new AdvancedServiceAdapter(new BasicService());
                break;
            default:
                System.out.println("Invalid choice. Defaulting to Basic Diagnostics.");
                basicService = new BasicService();
                break;
        }
        return basicService;
    }

    private static int getAdditionalServiceChoice(Scanner scanner) {
        System.out.println("""
                \nChoose an additional service to Basic Diagnostics:\s
                1 for Teeth Cleaning\s
                2 for Filling\s
                3 for Both""");
        return scanner.nextInt();
    }

    private static ServiceDecorator selectAdditionalService(DentistryService basicService, int choice) {
        ServiceDecorator selectedService = null;
        switch (choice) {
            case TEETH_CLEANING:
                selectedService = new TeethCleaningDecorator(basicService);
                break;
            case FILLING:
                selectedService = new FillingDecorator(basicService);
                break;
            case BOTH:
                selectedService = new FillingDecorator(new TeethCleaningDecorator(basicService));
                break;
            default:
                if (basicService instanceof BasicService) {
                    basicService.performService();
                }
                break;
        }
        return selectedService;
    }

    private static void confirmAdditionalService(ServiceDecorator selectedService, int choice, Scanner scanner) {
        if (selectedService != null && (choice == TEETH_CLEANING || choice == FILLING)) {

            System.out.println("\nAdditional service: " + selectedService.getDescription());
            System.out.println("Are you sure you want to choose " + selectedService.getDescription() + "? (yes/no)");
            String confirm = scanner.next();

            if (!confirm.equalsIgnoreCase("yes")) {
                System.out.println("Additional service not selected.");
            }
        } else if (selectedService != null && choice == BOTH) {

            System.out.println("\nAdditional service: Teeth Cleaning AND Filling");
            System.out.println("Are you sure you want to choose Both? (yes/no)");
            String confirm = scanner.next();

            if (!confirm.equalsIgnoreCase("yes")) {
                System.out.println("Additional service not selected.");
            }
        } else {
            System.out.println("No additional service selected");
        }
    }

    private static void performDiagnostics(DentistryService basicService, ServiceDecorator selectedService) {
        if (basicService instanceof AdvancedDentistryService) {
            ((AdvancedDentistryService) basicService).performAdvancedService();
        }

        if (selectedService != null) {
            selectedService.performService();
        }
    }
}
