package me.t4c30;

import me.t4c30.vista.PantallaPrincipal;

public class Main {
    public static void main(String[] args) {
        PantallaPrincipal pantallaPrincipal = new PantallaPrincipal();
        
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
        

        do {         

        caso = "4";
            switch (caso) {
                case "1": //revisa el saldo y rompe al acaba 
                    System.out.println("Su saldo actual es: $" + saldo);
                    break;
                case "2":                    
                try { // Intenta ejecutar el programa
                    System.out.println("Ingrese la cantidad a depositar: ");
                    incremento = Float.valueOf(0);
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
                    disminuir = Float.valueOf(0);
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

    }
}