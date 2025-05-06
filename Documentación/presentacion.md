# Cajero

Software Cajero Automático

Índice

- Supuesto de reunión con el cliente
- Modelo Entidad-Relación
- Modelo Relacional
- Scripts SQL
  - Estructura BD
  - Usuarios y Permisos
  - PL/SQL
  - Caso de Uso

## Supuesto de reunión con el cliente

El cliente es una fabricante de cajero automáticos que necesita un software de gestión. Se venderá a entidades bancaria que desea el gestionar un cajero automático con el usuario y su cuenta.

El usuario se quiere conocer su nombre, IBAN-cuenta y fecha de acceso al cajero. Además guardara un registro de que cajero accedió con su id. El usuario se relaciona con la entidad bancaria y cuenta.

La cuenta tiene un IBAN y saldo. Se hará referencia guardando un id de usuario. La cuenta pertenece un usuario y gestiona una entidad. Se relaciona con la entidad perteneciente y usuario asignado.

El cajero automático tendrá un id, localización. Conectara con la cuenta por el usuario, que a su esta esta asociada a la entidad bancaria.

La entidad bancaria tendrá un nombre, dirección. La entidad tendrá varios usuarios, cajeros y cuentas.

## Modelo Entidad-Relación

[imagen]

## Modelo Relacional

[Imagen]

## Scripts SQL

## Estructura BD

## Usuarios y Permisos

## PL/SQL

## Caso de Uso

```sql
SELECT * FROM USER;
```
