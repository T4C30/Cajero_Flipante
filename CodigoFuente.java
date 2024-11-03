
import java.util.Scanner;

public class CodigoFuente {
    public static void main(String[] args) {
        // Escaner
        Scanner teclado = new Scanner(System.in);
        
        // texto
        String caso;

        // Contadores
        int ingresos = 0;
        int retirar = 0;

        // Dinero
        float saldo = 0;
        float incremento;
        float disminuir;
        float importetotal = 0;
        float retirado = 0;
        
        // Decisión
        boolean confirmar = true;
        

        do {  // Inicio el programa para el usuario con un do-while al ser un bucle indeterminado, se espera que vuelva a la interfaz de inicio que sirve de guía         
        System.out.println("-------------------------------");
        System.out.println("|       Cajero Automático     |");
        System.out.println("|                             |");
        System.out.println("-------------------------------");
        System.out.println("|      1. Consultar saldo     |");
        System.out.println("|      2. Depositar dinero    |");
        System.out.println("|      3. Retirar dinero      |");
        System.out.println("|      4. Salir               |");
        System.out.println("|                             |");
        System.out.println("-------------------------------\n");
        System.out.print("Elija una opción: ");

        caso = teclado.nextLine();
            switch (caso) {
                case "1": //revisa el saldo y rompe al acaba 
                    System.out.println("Su saldo actual es: $" + saldo);
                    break;
                case "2":                    
                try { // Intenta ejecutar el programa
                    System.out.println("Ingrese la cantidad a depositar: ");
                    incremento = Float.valueOf(teclado.nextLine());
                    }
                    catch(Exception e) { // Si el usuario pone cualquier valor que no entre en el dominio float
                        System.out.println("Error [C2] (Revise el manual para más información)");
                        break;
                    }
                    float cah = (float) (Math.round(incremento * 100.0) / 100.0);
                        if (cah != incremento) { // Si el usuario pone cualquier valor que sea mayor de 2 decimales
                            System.out.println("Error [C2.1] (Revise el manual para más información)");
                            break;
                        }
                        if (incremento>0) { // Ruta normal que permite la ejecución correcta del programa
                            saldo = incremento + saldo;
                            ingresos++;
                            importetotal= importetotal + incremento;
                        } else { // Comete un error el usuario en los valores devolviendolo al inicio
                            System.out.println("Error [C2.2] (Revise el manual para más información)");
                        }

                    break;

                case "3":
                try { // Intenta ejecutar el programa
                    System.out.println("Ingrese la cantidad a retirar: ");
                    disminuir = Float.valueOf(teclado.nextLine());
                    }
                    catch(Exception e) { // Si el usuario pone cualquier valor que no entre en el dominio float
                        System.out.println("Error [C3] (Revise el manual para más información)");
                        break;
                    }
                    disminuir = Math.abs(disminuir);
                    float cath = (float) (Math.round(disminuir * 100.0) / 100.0);
                        if (disminuir != cath){  // Si el usuario pone cualquier valor que sea mayor de 2 decimales
                            System.out.println("Error [C3.1] (Revise el manual para más información)");
                            break;
                        }
                        if (disminuir>saldo) { // Comete un error el usuario en los valores devolviendolo al inicio
                            System.out.println("Error [C3.2] (Revise el manual para más información)");
                        } else { // Ruta normal que permite la ejecución correcta del programa
                            retirar++;
                            saldo = saldo - disminuir;
                            saldo = (float) (Math.round(saldo * 100.0) / 100.0);
                            retirado = retirado + disminuir;
                        }
                    break;
                
                case "4": // Sale del bucle a través del false
                confirmar = false;    
                    break;
                default: // Opción por defecto que volverá a repetir en bucle en caso de error
                    System.out.println("Error [D] (Revise el manual para más información)");
                    break;
            }
        }while (confirmar);

        teclado.close();
        // Estadísticas finales
        System.out.println("Gracios por usa el cajero automático. ¡Hasta luego!");
        System.out.println("------------ESTADÍSTICAS DE USO----------------");
        System.out.println("         Número total de Ingresos:   " + ingresos);
        System.out.println("         Importe total Ingresado:   $" + importetotal);
        System.out.println("         Número total de Retiradas:  " + retirar);
        System.out.println("         Importe total Retirado:    $" + retirado);
        System.out.println("         Saldo Final en Cuenta:     $" + saldo);
        System.out.println("-----------------------------------------------");

    }
}
