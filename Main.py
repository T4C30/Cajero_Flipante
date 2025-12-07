

def iniciar():
    # texto
    caso:str
    
    # Contadores
    ingreso:int = 0
    retiro:int = 0
    
    # Dinero
    saldo:float = 0
    incremento:float
    disminucion:float = 0
    ingreso_total:float = 0
    retiro_total:float = 0
    
    while(True): # Inicio el programa para el usuario con un do-while al ser un bucle indeterminado, se espera que vuelva a la interfaz de inicio que sirve de guía
        print("-------------------------------")
        print("|       Cajero Automático     |")
        print("|                             |")
        print("-------------------------------")
        print("|      1. Consultar saldo     |")
        print("|      2. Depositar dinero    |")
        print("|      3. Retirar dinero      |")
        print("|      4. Salir               |")
        print("|                             |")
        print("-------------------------------")
        print()
        caso=input("Elija una opción: ")
        match (caso):
            case ("1"): # revisa el saldo
                print(f"Su saldo actual es: {saldo}€")
            case ("2"):
                try: # Intenta ejecutar el programa
                    incremento=float(input("Ingrese la cantidad a depositar: "))
                except: # Si el usuario pone cualquier valor que no entre en el dominio float
                    print("Error [C2] (Revise el manual para más información)")
                    continue

                if incremento != round(incremento, 2): # Si el usuario pone cualquier valor que sea mayor de 2 decimales
                    print("Error [C2.1] (Revise el manual para más información)")
                    continue

                if incremento > 0: # Ruta normal que permite la ejecución correcta del programa
                    saldo += incremento
                    ingreso += 1
                    ingreso_total+=incremento
                else: # Comete un error el usuario en los valores devolviendolo al inicio
                    print("Error [C2.2] (Revise el manual para más información)")
                      
            case ("3"):
                try: # Intenta ejecutar el programa
                    disminucion=float(input("Ingrese la cantidad a retirar: "))
                except: # Si el usuario pone cualquier valor que no entre en el dominio float
                    print("Error [C3] (Revise el manual para más información)")
                    continue
                
                disminucion = abs(disminucion)
                if disminucion != round(disminucion, 2): # Si el usuario pone cualquier valor que sea mayor de 2 decimales
                    print("Error [C3.1] (Revise el manual para más información)")
                    continue

                if disminucion < saldo: # Ruta normal que permite la ejecución correcta del programa
                    saldo -= disminucion
                    retiro += 1
                    retiro_total+=disminucion
                else: # Comete un error el usuario en los valores devolviendolo al inicio
                    print("Error [C3.2] (Revise el manual para más información)")


                 
            case ("4"): # Sale del bucle
                break   
            case (_): # Opción por defecto que volverá a repetir en bucle en caso de error
                print("Error [D] (Revise el manual para más información)")
  
    print("Gracios por usa el cajero automático. ¡Hasta luego!")
    print("------------ESTADÍSTICAS DE USO----------------")
    print(f"         Número total de Ingresos:   {ingreso}")
    print(f"         Importe total Ingresado:   {ingreso_total}€")
    print(f"         Número total de Retiradas:  {retiro}")
    print(f"         Importe total Retirado:    {retiro_total}€")
    print(f"         Saldo Final en Cuenta:     {saldo}€")
    print("-----------------------------------------------")


if __name__ == "__main__":
    iniciar()