

def iniciar():
    # texto
    caso:str
    
    # Contadores
    ingreso:int = 0
    retirar:int = 0
    
    # Dinero
    saldo:float = 0
    incremento:float
    disminucion:float = 0
    importetotal:float = 0
    retirado:float = 0
    
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
        caso=input("Elija una opción: ")
        match (caso):
            case ("1"):
                print(f"Su saldo actual es: {saldo}€")
            case ("2"):
                pass
                 
            case ("3"):
                pass
                 
            case ("4"):
                break   
            case (_):
                print("Error [D] (Revise el manual para más información)")
                 
        # end match
        



if __name__ == "__main__":
    iniciar()