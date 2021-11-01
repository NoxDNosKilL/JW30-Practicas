CREATE OR REPLACE
PACKAGE BODY PKG_EMPLEADO AS

  PROCEDURE SP_LISTAR(
        P_CURSOR            OUT SYS_REFCURSOR,
        P_APELLIDO_PATERNO  IN  EMPLEADO.APELLIDO_PATERNO%TYPE,
        P_APELLIDO_MATERNO  IN  EMPLEADO.APELLIDO_MATERNO%TYPE,
        P_NOMBRE            IN  EMPLEADO.NOMBRE%TYPE
    ) AS
  BEGIN
   OPEN 
        P_CURSOR
      FOR
          SELECT
              ID_EMPLEADO,
              APELLIDO_PATERNO,
              APELLIDO_MATERNO,
              NOMBRE,
              NRO_DOCUMENTO
          FROM
              EMPLEADO
          WHERE
                  UPPER(APELLIDO_PATERNO)    LIKE   '%'||UPPER(P_APELLIDO_PATERNO)||'%'
              AND UPPER(APELLIDO_MATERNO)    LIKE   '%'||UPPER(P_APELLIDO_MATERNO)||'%'
              AND UPPER(NOMBRE)              LIKE   '%'||UPPER(P_NOMBRE)||'%'
              AND ESTADO                      =     '1';
  END SP_LISTAR;

END PKG_EMPLEADO;