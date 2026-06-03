// import java.util.*;
// class CreditCardValidation
// {
//     public static void main(String[] args) 
//     {
//         Scanner sc = new Scanner(System.in);
//         String s = sc.next();
//         int sum = 0;
//         for(int i=s.length()-2;i>=0;i-=2)
//         {
//             int d = (s.charAt(i)-'0')*2;
//             if(d > 9)
//             {
//                 d = d - 9;
//             }
//             sum = sum + d;
//         }
//         for(int i=s.length()-1;i>=0;i-=2)
//         {
//             sum = sum + s.charAt(i)-'0';
//         }
//         if(sum % 10 == 0)
//         {
//             System.out.print("Credit card number is valid");
//         }
//         else
//         {
//             System.out.print("Credit card number is not valid");
//         }
//     }
// }

import java.util.*;

class CreditCardValidation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<String> history = new ArrayList<>();

        while (true) {
            System.out.print("\nEnter Card Number (history/exit): ");
            String s = sc.next();

            if (s.equalsIgnoreCase("history")) {
                System.out.println("\n========== VALIDATION HISTORY ==========");

                if (history.isEmpty()) {
                    System.out.println("No cards were validated.");
                } else {
                    for (String record : history) {
                        System.out.println(record);
                    }
                }

                continue;
            }

            if (s.equalsIgnoreCase("exit")) {
                System.out.println("\n======================================");
                System.out.println(" Thank You for Using Dakshayani's");
                System.out.println("     Credit Card Validator");
                System.out.println("======================================");
                System.out.println("Session ended successfully.");
                System.out.println("See you again!");
                break;
            }

            if (!s.matches("\\d+")) {
                System.out.println("Card number must contain only digits.");
                continue;
            }

            if (s.length() < 13 || s.length() > 19) {
                System.out.println("Invalid card length.");
                history.add(s + " -> INVALID LENGTH");
                continue;
            }

            int sum = 0;

            for (int i = s.length() - 2; i >= 0; i -= 2) {
                int d = (s.charAt(i) - '0') * 2;

                if (d > 9) {
                    d -= 9;
                }

                sum += d;
            }

            for (int i = s.length() - 1; i >= 0; i -= 2) {
                sum += s.charAt(i) - '0';
            }

            String cardType = "UNKNOWN";

            if (s.startsWith("4")) {
                cardType = "VISA";
            } else if (s.startsWith("34") || s.startsWith("37")) {
                cardType = "AMERICAN EXPRESS";
            } else {
                int prefix = Integer.parseInt(s.substring(0, 2));

                if (prefix >= 51 && prefix <= 55) {
                    cardType = "MASTERCARD";
                }
            }

            if (sum % 10 == 0) {
                System.out.println("Credit card number is valid");
                System.out.println("Card Type : " + cardType);

                history.add(s + " -> VALID -> " + cardType);
            } else {
                System.out.println("Credit card number is not valid");

                history.add(s + " -> INVALID");
            }
        }
    }
}