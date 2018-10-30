        /********************************************************************************************************************
        * Hangman.java
        * Authors: Downrightmike, Johnnypoblano, Vicksy
        * CIS 131
        * 
        * 
        ********************************************************************************************************************/
        import java.util.Scanner;

        public class Hangman {
            
            private static Scanner keyboard = new Scanner(System.in);
        
            public static void main(String[] args) { 
                menu();

            }// End of main

            public static void menu(){
                System.out.println("****************************************************************");
                System.out.println("       ██╗  ██╗ ██████╗ ███╗   ██╗███████╗███████╗████████╗     ");
                System.out.println("       ██║  ██║██╔═══██╗████╗  ██║██╔════╝██╔════╝╚══██╔══╝     ");
                System.out.println("       ███████║██║   ██║██╔██╗ ██║█████╗  ███████╗   ██║        ");
                System.out.println("       ██╔══██║██║   ██║██║╚██╗██║██╔══╝  ╚════██║   ██║        ");
                System.out.println("       ██║  ██║╚██████╔╝██║ ╚████║███████╗███████║   ██║        ");
                System.out.println("       ╚═╝  ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚══════╝╚══════╝   ╚═╝        ");
                System.out.println("                                                                ");
                System.out.println("██╗  ██╗ █████╗ ███╗   ██╗ ██████╗ ███╗   ███╗ █████╗ ███╗   ██╗");
                System.out.println("██║  ██║██╔══██╗████╗  ██║██╔════╝ ████╗ ████║██╔══██╗████╗  ██║");
                System.out.println("███████║███████║██╔██╗ ██║██║  ███╗██╔████╔██║███████║██╔██╗ ██║");
                System.out.println("██╔══██║██╔══██║██║╚██╗██║██║   ██║██║╚██╔╝██║██╔══██║██║╚██╗██║");
                System.out.println("██║  ██║██║  ██║██║ ╚████║╚██████╔╝██║ ╚═╝ ██║██║  ██║██║ ╚████║");
                System.out.println("╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═══╝ ╚═════╝ ╚═╝     ╚═╝╚═╝  ╚═╝╚═╝  ╚═══╝");
                System.out.println("****************************************************************");
                System.out.println("");
                System.out.println("Please select an option:");
                System.out.println("       1. Short game");
                System.out.println("       2. Medium game");
                System.out.println("       3. Long game");
                
            }// End of menu method
        }// End of Hangman class
