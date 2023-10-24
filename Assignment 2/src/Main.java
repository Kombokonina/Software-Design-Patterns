import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose type of diagnostics: \n1 for Basic \n2 for Advanced");
        int diagnosticType = scanner.nextInt();

        DentistryService basicService = null;

        switch (diagnosticType) {
            case 1:
                basicService = new BasicService();
                break;
            case 2:
                basicService = new AdvancedServiceAdapter(new BasicService());
                break;
            default:
                System.out.println("Invalid choice. Defaulting to Basic Diagnostics.");
                basicService = new BasicService();
                break;
        }

        System.out.println("\nChoose an additional service to Basic Diagnostics: \n1 for Teeth Cleaning \n2 for Filling \n3 for Both");
        int choice = scanner.nextInt();

        ServiceDecorator selectedService = null;

        switch (choice) {
            case 1:
                selectedService = new TeethCleaningDecorator(basicService);
                break;
            case 2:
                selectedService = new FillingDecorator(basicService);
                break;
            case 3:
                selectedService = new FillingDecorator(new TeethCleaningDecorator(basicService));
                break;
            default:
                if (diagnosticType == 1) {
                    basicService.performService();
                }
                break;
        }

        if (selectedService != null && (choice == 1 || choice == 2)) {
            System.out.println("\nAdditional service: " + selectedService.getDescription());
            System.out.println("Are you sure you want to choose " + selectedService.getDescription() + "? (yes/no)");
            String confirm = scanner.next();
            if (!confirm.toLowerCase().equals("yes")) {
                selectedService = null;
                System.out.println("Additional service not selected.");
            }
        } else if (selectedService != null && choice == 3) {
            System.out.println("\nAdditional service: Teeth Cleaning AND Filling");
            System.out.println("Are you sure you want to choose Both? (yes/no)");
            String confirm = scanner.next();
            if (!confirm.toLowerCase().equals("yes")) {
                selectedService = null;
                System.out.println("Additional service not selected.");
            }
        } else {
            System.out.println("No additional service selected");
        }

        if (basicService instanceof AdvancedDentistryService) {
            ((AdvancedDentistryService) basicService).performAdvancedService();
        }

        if (selectedService != null) {
            selectedService.performService();
        }

        System.out.println("\nCongratulations! Your teeth are as good as new! Your bill: $100k");
    }
}
