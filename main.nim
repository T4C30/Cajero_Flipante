from strutils import parseFloat
from math import round

var 
    caso: string
    # Contadores
    ingresos: uint8
    retirar: uint8
    # Dinero
    saldo: float32 = 0
    incremento: float32
    disminuir: float32
    importetotal:float32 = 0
    retirado: float32 = 0


while true:
    echo "-------------------------------"
    echo "|       Cajero Automático     |"
    echo "|                             |"
    echo "-------------------------------"
    echo "|      1. Consultar saldo     |"
    echo "|      2. Depositar dinero    |"
    echo "|      3. Retirar dinero      |"
    echo "|      4. Salir               |"
    echo "|                             |"
    echo "-------------------------------\n"
    echo "Elija una opción: "
    caso = readLine(stdin)

    case caso:
        of "1":
            echo "Su saldo principal es: ", saldo, "€"

        of "2":
            try:
                echo "Ingrese la cantidad a depositar: "
                incremento = readLine(stdin).parseFloat()
            except:
                echo "Error [C2] (Revise el manual para más información)"
                continue
            
            let cah:float32 = round(incremento,2)
            if cah != incremento:
                echo "Error [C2.1] (Revise el manual para más información)"
                continue
            if incremento>0:
                saldo += incremento
                ingresos += 1
                importetotal += incremento
            else:
                echo "Error [C2.2] (Revise el manual para más información)"

        of "3":
            try:
                echo "Ingrese la cantidad a retirar: "
                disminuir = readLine(stdin).parseFloat()
            except:
                echo "Error [C3] (Revise el manual para más información)"
                continue

            disminuir = abs(disminuir)
            let cath:float32 = round(disminuir,2)
            if cath != disminuir:
                echo "Error [C3.1] (Revise el manual para más información)"
                continue
            if disminuir<saldo:
                saldo -= disminuir
                saldo = round(saldo,2)
                retirar += 1
                retirado += disminuir
            else:
                echo "Error [C3.2] (Revise el manual para más información)"
            
        of "4":
            break
        else:
            echo "Error [D] (Revise el manual para más información)"

echo "Gracias por usar el cajero automático. ¡Hasta luego!"
echo "------------ESTADÍSTICAS DE USO----------------"
echo "         Número total de Ingresos:   ", ingresos
echo "         Importe total Ingresado:   ", importetotal, "€"
echo "         Número total de Retiradas:  ", retirar
echo "         Importe total Retirado:    ",retirado, "€"
echo "         Saldo Final en Cuenta:     ", saldo, "€"
echo "-----------------------------------------------"