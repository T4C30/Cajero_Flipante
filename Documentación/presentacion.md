# Proyecto: Software de Gestión de Cajeros Automáticos
## Implementación con Oracle y PL/SQL

---

## 1. Resumen del Proyecto

**Cliente:** Fabricante de cajeros automáticos.
**Necesidad:** Software de gestión para entidades bancarias.
**Funcionalidad Principal:** Gestionar interacciones de usuarios con cajeros y sus cuentas.

---

## 2. Diseño de la Base de Datos (Modelo Relacional)

**Entidades Principales:**
* `EntidadBancaria`
* `Usuario`
* `Cuenta`
* `CajeroAutomatico`
* `AccesoCajero`
* `AUDIT_CUENTA` (Auditoría)

---

## 3. Implementación: Usuarios y Permisos

**Ejemplo Creación de Usuarios:**
```sql
-- Usuario Administrador (DBA_USER)
CREATE USER DBA_USER IDENTIFIED BY Pa$$w0rdDBA;
GRANT CONNECT, RESOURCE, DBA TO DBA_USER;

-- Usuario Aplicación (APP_USER)
CREATE USER APP_USER IDENTIFIED BY Pa$$w0rdAPP;
GRANT CONNECT, RESOURCE, CREATE SESSION TO APP_USER;
ALTER USER APP_USER QUOTA UNLIMITED ON USERS;
```
*(Ejecutar como SYS o SYSTEM)*

---

## 4. Implementación: Estructura BD (DDL)

**Ejemplo `CREATE TABLE` (Cuenta):**
```sql
CREATE TABLE Cuenta (
  IBAN VARCHAR2(34 CHAR) NOT NULL,
  Saldo NUMBER(15, 2) DEFAULT 0.00 NOT NULL,
  ID_Usuario NUMBER(10) NOT NULL,
  ID_Entidad NUMBER(10) NOT NULL,
  CONSTRAINT PK_Cuenta PRIMARY KEY (IBAN),
  CONSTRAINT FK_Cuenta_Usuario FOREIGN KEY (ID_Usuario)
    REFERENCES Usuario(ID_Usuario),
  CONSTRAINT FK_Cuenta_Entidad FOREIGN KEY (ID_Entidad)
    REFERENCES EntidadBancaria(ID_Entidad),
  CONSTRAINT CK_Saldo_No_Negativo CHECK (Saldo >= 0)
);
```
*(Secuencias e Índices también definidos)*

---

## 5. Implementación: PL/SQL - Paquetes (CRUD)

**Paquete: `PKG_GESTION_USUARIOS` (Ejemplo `PRC_CREAR_USUARIO`)**
```sql
-- Especificación
PROCEDURE PRC_CREAR_USUARIO (
  p_NombreUsuario   IN Usuario.NombreUsuario%TYPE,
  p_ID_Entidad      IN Usuario.ID_Entidad%TYPE,
  p_ID_Usuario      OUT Usuario.ID_Usuario%TYPE
);

-- Cuerpo (fragmento)
INSERT INTO Usuario (NombreUsuario, ID_Entidad)
VALUES (p_NombreUsuario, p_ID_Entidad)
RETURNING ID_Usuario INTO p_ID_Usuario;
COMMIT;
```

---

## 6. PL/SQL: Paquetes (Consulta Elaborada con Cursor)

**Paquete: `PKG_OPERACIONES_TRANSACCIONALES`**
**Función: `FNC_INFORME_ACCESOS_CAJERO`**
```sql
FUNCTION FNC_INFORME_ACCESOS_CAJERO (
  p_ID_Entidad  IN EntidadBancaria.ID_Entidad%TYPE DEFAULT NULL,
  -- ... otros parámetros de filtro ...
  p_Tipo_Operacion IN AccesoCajero.TipoOperacion%TYPE DEFAULT NULL
) RETURN SYS_REFCURSOR;
-- Cuerpo (fragmento de la consulta dinámica)
v_sql_query := 'SELECT ac.ID_Acceso, u.NombreUsuario, ...
               FROM AccesoCajero ac JOIN Usuario u ON ...
               WHERE (u.ID_Entidad = :1 OR :2 IS NULL) AND ...';
OPEN v_cursor FOR v_sql_query USING p_ID_Entidad, p_ID_Entidad, ...;
RETURN v_cursor;
```
*Devuelve un cursor (`SYS_REFCURSOR`) con accesos filtrados.*

---

## 7. PL/SQL: Paquetes (Recorrido Estructuras y DML)

**Paquete: `PKG_OPERACIONES_TRANSACCIONALES`**
**Procedimiento: `PRC_REALIZAR_TRANSFERENCIA`**
```sql
PROCEDURE PRC_REALIZAR_TRANSFERENCIA (
  p_IBAN_Origen     IN Cuenta.IBAN%TYPE,
  p_IBAN_Destino    IN Cuenta.IBAN%TYPE,
  p_Monto           IN NUMBER, ...
);
-- Cuerpo (Lógica principal)
-- 1. Validar cuentas y saldo origen.
-- 2. UPDATE Cuenta SET Saldo = Saldo - p_Monto WHERE IBAN = p_IBAN_Origen;
-- 3. UPDATE Cuenta SET Saldo = Saldo + p_Monto WHERE IBAN = p_IBAN_Destino;
-- 4. INSERT INTO AccesoCajero (..., TipoOperacion => 'TRANSFER_DEBIT');
-- 5. INSERT INTO AccesoCajero (..., TipoOperacion => 'TRANSFER_CREDIT');
-- 6. COMMIT / ROLLBACK;
```

---

## 8. Implementación: PL/SQL - Triggers (Auditoría)

**Trigger: `TRG_AUDIT_CUENTA`**
*Se dispara `AFTER INSERT OR UPDATE OR DELETE ON Cuenta`.*
```sql
-- Cuerpo (fragmento)
IF INSERTING THEN
  v_accion := 'INSERT';
  INSERT INTO AUDIT_CUENTA (IBAN_Cuenta, Saldo_Nuevo, ..., Accion)
  VALUES (:NEW.IBAN, :NEW.Saldo, ..., v_accion);
ELSIF UPDATING THEN
  v_accion := 'UPDATE';
  INSERT INTO AUDIT_CUENTA (IBAN_Cuenta, Saldo_Anterior, Saldo_Nuevo, ..., Accion)
  VALUES (:OLD.IBAN, :OLD.Saldo, :NEW.Saldo, ..., v_accion);
-- ...
END IF;
```
*Registra cambios en la tabla `AUDIT_CUENTA`.*

---

## 9. Implementación: PL/SQL - Triggers (Validación)

**Trigger: `TRG_VALIDA_ACCESO_CUENTA_USUARIO`**
*Se dispara `BEFORE INSERT OR UPDATE ON AccesoCajero`.*
```sql
-- Cuerpo (fragmento)
IF :NEW.IBAN_Cuenta IS NOT NULL THEN
  SELECT c.ID_Usuario INTO v_usuario_propietario
  FROM Cuenta c WHERE c.IBAN = :NEW.IBAN_Cuenta;

  IF v_usuario_propietario != :NEW.ID_Usuario THEN
    DBMS_OUTPUT.PUT_LINE('ALERTA DE VALIDACIÓN: ...'); -- Implementación adicional
    RAISE_APPLICATION_ERROR(-20021, 'Error: Usuario no propietario.');
  END IF;
END IF;
```
*Controla que el usuario que accede sea el propietario de la cuenta.*

---

## 10. Otorgamiento de Permisos a `APP_USER`

**Ejemplos de Permisos:**
```sql
-- Permisos DML sobre tablas
GRANT SELECT, INSERT, UPDATE, DELETE ON Cuenta TO APP_USER;

-- Permisos sobre secuencias
GRANT SELECT ON SEQ_ACCESO_CAJERO TO APP_USER;

-- Permisos de ejecución sobre paquetes
GRANT EXECUTE ON PKG_GESTION_CUENTAS TO APP_USER;
GRANT EXECUTE ON PKG_OPERACIONES_TRANSACCIONALES TO APP_USER;
```
*(Ejecutar como `DBA_USER`)*

---

## 11. Conclusión

* **Base de Datos Diseñada e Implementada:**
    * Usuarios, Tablas, Secuencias, Índices.
    * Lógica de Negocio en Paquetes PL/SQL (CRUD, Operaciones Complejas, Consultas).
    * Triggers para Auditoría y Validación.

---

# ¡Gracias!
## Preguntas
