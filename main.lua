#!/bin/lua
-- Texto
local caso = ""

-- Contadores
local ingresos = 0
local retirar = 0

-- Dinero
local saldo = 0
local incremento
local disminuir
local importetotal = 0
local retirado = 0

-- Decision
local confirmar = true

warn("@on")
while confirmar do
    print("-------------------------------")
    print("|       Cajero Automático     |")
    print("|                             |")
    print("-------------------------------")
    print("|      1. Consultar saldo     |")
    print("|      2. Depositar dinero    |")
    print("|      3. Retirar dinero      |")
    print("|      4. Salir               |")
    print("|                             |")
    print("-------------------------------\n")
    print("Elija una opción: ")
    caso = io.read()
    local casos = {
        ["1"] = function () print("Su saldo actual es", saldo) end,
        ["2"] = function ()
            print("Ingrese la cantidad a depositar: ")
            incremento = io.read()
            incremento = tonumber(incremento)
            if type(incremento) == "nil" then
                warn("Error [C2] (Revise el manual para más información)")
                return
            end

            local cah = string.format("%.2f", incremento)
            cah = tonumber(cah)
            if cah ~= incremento then
                warn("Error [C2.1] (Revise el manual para más información)")
            elseif incremento > 0 then
                saldo = incremento + saldo
                ingresos = 1 + ingresos
                importetotal = importetotal + incremento
            else
                warn("Error [C2.2] (Revise el manual para más información)")
            end



        end,
        ["3"] = function ()
            print("Ingrese la cantidad a retirar: ")
            disminuir = io.read()
            disminuir = tonumber(disminuir)
            if type(disminuir) == "nil" then
                warn("Error [C3] (Revise el manual para más información)")
                return
            end
            disminuir=math.abs(disminuir)
            local cath = string.format("%.2f", disminuir)
            cath = tonumber(cath)
            if cath ~= disminuir then
                warn("Error [C3.1] (Revise el manual para más información)")
            elseif disminuir > saldo then
                warn("Error [C3.2] (Revise el manual para más información)")
            else
                saldo = saldo - disminuir
                retirar = 1 + retirar
                retirado = retirado + disminuir
            end

        end,
        ["4"] = function () confirmar=false end

    }
    if casos[caso] then
        casos[caso]()
    else
        print("Error [D] (Revise el manual para más información)")
    end
end

print("Gracias por usar el cajero automático. ¡Hasta luego!")
print("------------ESTADÍSTICAS DE USO----------------")
print("         Número total de Ingresos:   ", ingresos)
print("         Importe total Ingresado:   $", importetotal)
print("         Número total de Retiradas:  ", retirar)
print("         Importe total Retirado:    $", retirado)
print("         Saldo Final en Cuenta:     $", saldo)
print("-----------------------------------------------")