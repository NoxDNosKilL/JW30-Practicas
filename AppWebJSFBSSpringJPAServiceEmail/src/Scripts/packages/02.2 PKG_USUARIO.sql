create or replace PACKAGE PKG_USUARIO AS 

    PROCEDURE SP_LISTAR(
      P_CURSOR  OUT SYS_REFCURSOR,
      P_NOMBRE  IN  USUARIO.NOMBRE%TYPE,
      P_USUARIO IN  USUARIO.USUARIO%TYPE
    ); 
    
        PROCEDURE SP_VALIDAR_ACCESO(
      P_CURSOR  OUT SYS_REFCURSOR,
      P_USUARIO IN  USUARIO.USUARIO%TYPE,
      P_CLAVE   IN  USUARIO.CLAVE%TYPE
    ); 

END PKG_USUARIO;


create or replace PACKAGE BODY PKG_USUARIO AS

  PROCEDURE SP_LISTAR(
      P_CURSOR  OUT SYS_REFCURSOR,
      P_NOMBRE  IN  USUARIO.NOMBRE%TYPE,
      P_USUARIO IN  USUARIO.USUARIO%TYPE
    ) AS
  BEGIN
 OPEN 
        P_CURSOR
      FOR
          SELECT
              ID_USUARIO,
              USUARIO,
              NOMBRE
          FROM
              USUARIO
          WHERE
                  UPPER(NOMBRE)     LIKE   '%'||UPPER(P_NOMBRE)||'%'
              AND UPPER(USUARIO)    LIKE   '%'||UPPER(P_USUARIO)||'%'
              AND ESTADO            =     '1';
  END SP_LISTAR;

      PROCEDURE SP_VALIDAR_ACCESO(
      P_CURSOR  OUT SYS_REFCURSOR,
      P_USUARIO IN  USUARIO.USUARIO%TYPE,
      P_CLAVE   IN  USUARIO.CLAVE%TYPE
    )AS
    BEGIN
      OPEN 
        P_CURSOR
      FOR
          SELECT
              ID_USUARIO,
              USUARIO,
              NOMBRE
          FROM
              USUARIO
          WHERE
                  USUARIO   =   P_USUARIO
              AND CLAVE     =   P_CLAVE    
              AND ESTADO    =   '1';
    
    END SP_VALIDAR_ACCESO;
END PKG_USUARIO;