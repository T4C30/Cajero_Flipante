#include <cmath>
#include <cstdio>
#include <cstdlib>
#include <exception>
#include <iostream>
#include <ostream>
#include <string>
using namespace std;

int main(){
    // Eleccion
    short caso;

    // Contadores
    unsigned short ingreso = 0; 
    unsigned short retiro = 0;   
    
    // Dinero
    float saldo= 0.0f; 
    float incremento = 0.0f; 
    float disminuir = 0.0f; 
    float retirado = 0.0f; 
    float importetotal = 0.0f;  
    

    do {
        cout <<"-------------------------------"<<endl;
        cout <<"|       Cajero Automático     |"<<endl;
        cout <<"|                             |"<<endl;
        cout <<"-------------------------------"<<endl;
        cout <<"|      1. Consultar saldo     |"<<endl;
        cout <<"|      2. Depositar dinero    |"<<endl;
        cout <<"|      3. Retirar dinero      |"<<endl;
        cout <<"|      4. Salir               |"<<endl;
        cout <<"|                             |"<<endl;
        cout <<"-------------------------------\n"<<endl;
        cout <<"Elija una opción: ";

        cin >> caso;
        switch (caso) {
            case 1: //revisa el saldo y rompe al acaba 
                cout << "Su saldo actual es: " << saldo << "€" << endl;
                break;
            case 2:                    
                try { // Intenta ejecutar el programa
                    cout <<"Ingrese la cantidad a depositar: "<< endl;
                    cin >> incremento;
                    }catch(exception e) { // Si el usuario pone cualquier valor que no entre en el dominio float
                        cout << "Error [C2] (Revise el manual para más información)"<< endl;
                        break;
                    }
                        if ((round(incremento * 100.0) / 100.0) != incremento) { // Si el usuario pone cualquier valor que sea mayor de 2 decimales
                            cout << "Error [C2.1] (Revise el manual para más información)"<<endl;
                            break;
                        }
                        if (incremento>0) { // Ruta normal que permite la ejecución correcta del programa
                            saldo = incremento + saldo;
                            ingreso++;
                            importetotal= importetotal + incremento;
                        } else { // Comete un error el usuario en los valores devolviendolo al inicio
                            cout << "Error [C2.2] (Revise el manual para más información)" << endl;
                        }
                break;
            case 3:
                try { // Intenta ejecutar el programa
                    cout<<"Ingrese la cantidad a retirar: ";
                    cin >> disminuir;
                    }
                    catch(exception e) { // Si el usuario pone cualquier valor que no entre en el dominio float
                        cout <<"Error [C3] (Revise el manual para más información)"<<endl;
                        break;
                    }
                    disminuir = abs(disminuir);
                        if (disminuir != round(disminuir * 100.0) / 100.0){  // Si el usuario pone cualquier valor que sea mayor de 2 decimales
                            cout<<"Error [C3.1] (Revise el manual para más información)"<< endl;
                            break;
                        }
                        if (disminuir>saldo) { // Comete un error el usuario en los valores devolviendolo al inicio
                            cout<<"Error [C3.2] (Revise el manual para más información)"<<endl;
                        } else { // Ruta normal que permite la ejecución correcta del programa
                            retiro++;
                            saldo = saldo - disminuir;
                            saldo = (float) (round(saldo * 100.0) / 100.0);
                            retirado = retirado + disminuir;
                        }
                    break;
            
            
            case 4: // Sale del bucle a través del false
                // Estadísticas finales
                cout << "Gracias por usa el cajero automático. ¡Hasta luego!" << endl;
                cout << "------------ESTADÍSTICAS DE USO----------------" << endl;
                cout <<"         Número total de Ingresos:   " << ingreso << endl;
                cout <<"         Importe total Ingresado:   " << importetotal << "€" << endl;
                cout <<"         Número total de Retiradas:  " << retiro << endl;
                cout << "         Importe total Retirado:    " << retirado<< "€"<< endl;
                cout <<"         Saldo Final en Cuenta:     " << saldo << "€" << endl;
                cout << "-----------------------------------------------";
                exit(0);
            default: // Opción por defecto que volverá a repetir en bucle en caso de error
                cout << "Error [D] (Revise el manual para más información)"<<endl;
                break;
        }
    }while (true);
}