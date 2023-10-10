import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        DentistryService BasicService = new BasicService();

        System.out.println("Choose an additional service to Basic Diagnostics: \n1 for Teeth Cleaning \n2 for Filling \n3 for Both");

        int choice = scanner.nextInt();

        DentistryService selectedService = null;

        switch (choice) {
            case 1:
                selectedService = new TeethCleaningDecorator(BasicService);
                break;
            case 2:
                selectedService = new FillingDecorator(BasicService);
                break;
            case 3:
                selectedService = new FillingDecorator(new TeethCleaningDecorator(BasicService));
                break;
            default:
                System.out.println("No additional service selected");
                selectedService = BasicService;
                break;
        }

        selectedService.performService();

        System.out.println("\n\nCongratulations! Your teeth are as good as new!");
    }
}
